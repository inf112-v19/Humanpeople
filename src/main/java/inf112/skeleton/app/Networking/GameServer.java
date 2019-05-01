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
import java.util.Scanner;
import java.util.logging.Logger;

public class GameServer {

    private static final Logger LOGGER = Logger.getLogger(GameServer.class.getName());
    private int portNumber = 25135;
    Server server;
    GameMap gameMap;
    Player player;
    int howManyClients;
    private int howManyConnected;
    RoboRally game;
    private boolean isGameStarted = false;
    private int listsReceived = 0;
    boolean [] nClientConnected;
    boolean [] haveNClientSentListOfMoves;
    ArrayList<Integer> idIndexer;
    private Connection[] connections;
    private ArrayList<ArrayList<ProgramCard>> movesToPlayAtServer;


    public GameServer(final RoboRally game) {
        this.game = game;
        movesToPlayAtServer = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("how many clients");
        howManyClients = in.nextInt();
        nClientConnected = new boolean[howManyClients];
        haveNClientSentListOfMoves = new boolean[howManyClients];
        idIndexer = new ArrayList<>();

        final PlayScreen playScreen = new PlayScreen(this.game, howManyClients + 1);
        playScreen.initializeUI(0);
        //Server has 0 as id
        playScreen.setMyID(0);
        gameMap = playScreen.getGameMap();

        player = gameMap.getPlayers().get(0);

        final ArrayList<ProgramCard> listOfServerMoves = new ArrayList<>();

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
                    answer.IPAdress = "localhost";
                    answer.accepted = true;
                    connection.sendTCP(answer);
                    howManyConnected++;
                }

                if (howManyConnected == howManyClients && !isGameStarted) {

                    Packets.PacketStartGame startGame = new Packets.PacketStartGame();
                    startGame.howManyPlayers = howManyClients + 1;
                    startGame.yourID = connection.getID();
                    if(!nClientConnected[startGame.yourID-1]) {
                        nClientConnected[startGame.yourID-1] = true;
                        connection.sendTCP(startGame);

                    }
                    if(isEveryoneConnected()) {
                        Gdx.app.postRunnable(new Runnable() {
                            public void run() {
                                game.setScreen(playScreen);
                            }
                        });
                        isGameStarted = true;
                        connections = server.getConnections();
                    }
                }
                if(isGameStarted&& !haveNClientSentListOfMoves[connection.getID()-1]) {
                    Packets.PacketServerRequiersMoves newMoves = new Packets.PacketServerRequiersMoves();
                    connection.sendTCP(newMoves);

                }

                if (object instanceof Packets.PacketListOfMoves) {
                    //TODO Update positions and send back updated positions
                    System.out.println("SERVER RECEIVED LIST OF MOVES FROM CLIENT " + connection.getID());
                    haveNClientSentListOfMoves[connection.getID()-1] = true;

                    final Packets.PacketListOfMovesFromServer listOfMovesFromServer = new Packets.PacketListOfMovesFromServer();


                    ArrayList<ProgramCard> movesFromClient = ((Packets.PacketListOfMoves) object).movesToSend;
                    int id = ((Packets.PacketListOfMoves) object).id;

                    if (movesFromClient.size() == 5) {

                        listOfMovesFromServer.allMoves.addAll(movesFromClient);

                        listOfMovesFromServer.id = id;
//                        Gdx.app.postRunnable(new Runnable() {
//                            public void run() {


                        for (int i = 0; i < connections.length; i++) {
                            System.out.println("SENDING CLIENT MOVES BACK TO CLIENT" + connections[i].getID());
                            connections[i].sendTCP(listOfMovesFromServer);
                        }

                            ArrayList<ProgramCard> pg = new ArrayList<>();
                            pg.addAll(listOfMovesFromServer.allMoves);
                            movesToPlayAtServer.add(pg);
                            idIndexer.add(id);
//                            pg.clear();
//                            gameMap.getHandsFromServer(pg, listOfMovesFromServer.id);
                            listOfMovesFromServer.allMoves.clear();
                            listsReceived++;
//                            }
//                        });
                    }
                }


                if (listsReceived == howManyClients) {
                    System.out.println("SERVER HAS RECEIVED ALL LISTS");
                    final Packets.PacketListOfMovesFromServer listOfMovesFromServer = new Packets.PacketListOfMovesFromServer();

                    System.out.println("SERVER HANDZ: " + player.getPlayerDeck().handSize());
                    if (player.getHandChosen() && player.getPlayerDeck().handSize() == 5) {
                        for (int i = 0; i < 5; i++) {
                            listOfMovesFromServer.allMoves.add(player.getPlayerDeck().getCardFromHand());
                        }

                        listOfServerMoves.addAll(listOfMovesFromServer.allMoves);
                        movesToPlayAtServer.add(listOfServerMoves);
                        idIndexer.add(0);
//                        listOfServerMoves.clear();

                        listOfMovesFromServer.id = 0;
                        for (int i = 0; i < connections.length; i++) {
                            System.out.println("SERVER SENDING OWN MOVES TO CLIENT" + connections[i].getID());
                            connections[i].sendTCP(listOfMovesFromServer);
                        }
                        listOfMovesFromServer.allMoves.clear();
                        System.out.println("SERVER ADDING OWN MOVES TO GAMEMAP");

                        for(int i = 0; i < movesToPlayAtServer.size(); i++) {
                            ArrayList<ProgramCard> cardsToAddToGamemap = movesToPlayAtServer.get(i);
                            gameMap.getHandsFromServer(cardsToAddToGamemap, idIndexer.get(i));
                        }
                        setBooleanArrayToFalse(haveNClientSentListOfMoves);
                        movesToPlayAtServer.clear();
                        listsReceived = 0;
                    }
                }
            }


        });
    }
    private boolean haveEveryoneReceived() {
        for (int i = 0; i < haveNClientSentListOfMoves.length; i++) {
            if(!haveNClientSentListOfMoves[i])
                return false;
        }
        return true;
    }

    private boolean isEveryoneConnected() {
        for (int i = 0; i <nClientConnected.length ; i++) {
            if(!nClientConnected[i])
                return  false;
        }
        return true;
    }
    private void setBooleanArrayToFalse(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = false;
        }
    }

}




