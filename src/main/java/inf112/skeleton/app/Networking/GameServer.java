package inf112.skeleton.app.Networking;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Screen.PlayScreen;

import java.io.IOException;
import java.util.ArrayList;

public class GameServer {

    private int portNumber;
    private Server server;
    private GameMap gameMap;
    private Player player;
    private RoboRally game;

    private int howManyClients;
    private int howManyConnected;
    private boolean isGameStarted;
    private int listsReceived;


    private boolean[] nClientConnected;
    private boolean[] haveNClientSentListOfMoves;
    private ArrayList<Integer> idIndexer;
    private ArrayList<ArrayList<ProgramCard>> movesToPlayAtServer;
    private final PlayScreen playScreen;
    private ArrayList<ProgramCard> listOfServerMoves;

    private final int SERVER_ID = 0;


    public GameServer(final RoboRally game, int port, int nPlayers) {
        this.portNumber = port;
        howManyClients = nPlayers - 1;

        this.game = game;
        playScreen = new PlayScreen(this.game, howManyClients + 1, true, "assets/map3.tmx");
        playScreen.setMyID(SERVER_ID);
        playScreen.initializeUI(SERVER_ID);
        gameMap = playScreen.getGameMap();
        player = gameMap.getPlayers().get(SERVER_ID);

        nClientConnected = new boolean[howManyClients];
        haveNClientSentListOfMoves = new boolean[howManyClients];
        idIndexer = new ArrayList<>();
        movesToPlayAtServer = new ArrayList<>();
        listOfServerMoves = new ArrayList<>();

        server = new Server(55555, 55555);

        try {
            server.bind(portNumber, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final NetworkUtils networkUtils = new NetworkUtils();
        networkUtils.registerPackets(server.getKryo());
        server.start();

        server.addListener(new Listener() {
            public void received(final Connection connection, Object object) {

                if (object instanceof Packets.PacketRequest) {
                    Packets.PacketRequestAnswer answer = new Packets.PacketRequestAnswer();
                    answer.accepted = true;
                    connection.sendTCP(answer);
                    howManyConnected++;
                }

                //When all the players have connected it lets the clients know that they should start the game
                //Also starting its own game
                if (howManyConnected == howManyClients && !isGameStarted) {
                    Packets.PacketStartGame startGame = new Packets.PacketStartGame();
                    startGame.howManyPlayers = howManyClients + 1;
                    startGame.yourID = connection.getID();

                    if (!nClientConnected[startGame.yourID - 1]) {
                        nClientConnected[startGame.yourID - 1] = true;
                        connection.sendTCP(startGame);
                    }

                    if ((isEveryoneConnected()) && !isGameStarted) {
                        startMyGame();

                    }
                }

                if (isGameStarted) {
                    if (!player.isActive()) {
                        sendToAllServerIsPoweredDown();
                    }

                    if (object instanceof Packets.PacketIAmPoweredDown) {
                        int id = ((Packets.PacketIAmPoweredDown) object).ID;
                        sendToAllClientIsPoweredDown(id);
                        listsReceived++;
                    }
                }

                if (object instanceof Packets.PacketIamDead) {
                    System.out.println("IAMDEAD");
                    howManyConnected--;
                }

                //Pinging clients that hasnt sent moves
                if (isGameStarted && !haveNClientSentListOfMoves[connection.getID() - 1]) {
                    Packets.PacketServerRequiersMoves newMoves = new Packets.PacketServerRequiersMoves();
                    connection.sendTCP(newMoves);
                }

                //Receiving Packet with moves from a client. Adds them to own arrayList to be used later
                //and sends out the lists to all clients
                if (object instanceof Packets.PacketListOfMoves) {
                    haveNClientSentListOfMoves[connection.getID() - 1] = true;

                    ArrayList<ProgramCard> movesFromClient = ((Packets.PacketListOfMoves) object).movesToSend;
                    int id = ((Packets.PacketListOfMoves) object).id;

                    if (movesFromClient.size() == 5) {
                        broadCastClientMoves(movesFromClient, id);
                        addClientMovesToServerList(movesFromClient, id);
                        listsReceived++;
                    }
                }

                if (listsReceived == howManyConnected) {
                    if (player.getHandChosen()) {

                        //adding servers hands to packet to be sent out to all clients
                        if (player.isActive() && player.isAlive()) {
                            broadCastServerMoves();
                        }

                        //Adding all moves to servers game-map
                        for (int i = 0; i < movesToPlayAtServer.size(); i++) {
                            ArrayList<ProgramCard> cardsToAddToGamemap = movesToPlayAtServer.get(i);
                            gameMap.getHandsFromServer(cardsToAddToGamemap, idIndexer.get(i));
                        }
                        startNextRound();
                    }
                }
            }

        });

    }

    private boolean isEveryoneConnected() {
        for (int i = 0; i < nClientConnected.length; i++) {
            if (!nClientConnected[i])
                return false;
        }
        return true;
    }

    private void setBooleanArrayToFalse(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = false;
        }
    }

    private void startMyGame() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game.setScreen(playScreen);
            }
        });
        isGameStarted = true;
    }

    private void sendToAllServerIsPoweredDown() {
        Packets.PacketIAmPoweredDown iAmPoweredDown = new Packets.PacketIAmPoweredDown();
        iAmPoweredDown.ID = SERVER_ID;
        server.sendToAllTCP(iAmPoweredDown);

    }

    private void sendToAllClientIsPoweredDown(int id) {
        Packets.PacketIAmPoweredDown poweredDown = new Packets.PacketIAmPoweredDown();
        gameMap.getPlayers().get(id).powerDown();
        server.sendToAllTCP(poweredDown);
    }

    private void broadCastClientMoves(ArrayList<ProgramCard> movesFromClient, int id) {
        final Packets.PacketListOfMovesFromServer listOfMovesFromServer = new Packets.PacketListOfMovesFromServer();
        listOfMovesFromServer.allMoves.addAll(movesFromClient);
        listOfMovesFromServer.id = id;
        server.sendToAllTCP(listOfMovesFromServer);
        listOfMovesFromServer.allMoves.clear();


    }

    private void addClientMovesToServerList(ArrayList<ProgramCard> movesFromClient, int id) {
        ArrayList<ProgramCard> pg = new ArrayList<>();
        pg.addAll(movesFromClient);
        movesToPlayAtServer.add(pg);
        idIndexer.add(id);


    }

    private void broadCastServerMoves() {
        final Packets.PacketListOfMovesFromServer listOfMovesFromServer = new Packets.PacketListOfMovesFromServer();

        for (int i = 0; i < 5; i++) {
            listOfMovesFromServer.allMoves.add(player.getPlayerDeck().getCardFromHand());

        }
        listOfServerMoves.addAll(listOfMovesFromServer.allMoves);
        movesToPlayAtServer.add(listOfServerMoves);
        idIndexer.add(SERVER_ID);
        listOfMovesFromServer.id = SERVER_ID;
        server.sendToAllTCP(listOfMovesFromServer);
        listOfMovesFromServer.allMoves.clear();

    }

    private void startNextRound() {
        listOfServerMoves.clear();
        Packets.PacketStartRound startRound = new Packets.PacketStartRound();
        server.sendToAllTCP(startRound);
        gameMap.setStartRound(true);
        setBooleanArrayToFalse(haveNClientSentListOfMoves);
        movesToPlayAtServer.clear();
        idIndexer.clear();
        listsReceived = 0;
    }


}



