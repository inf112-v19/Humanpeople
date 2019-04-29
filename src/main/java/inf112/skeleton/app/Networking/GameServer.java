package inf112.skeleton.app.Networking;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Screen.PlayScreen;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class GameServer {

    private static final Logger LOGGER = Logger.getLogger( GameServer.class.getName() );
    private int portNumber = 25135;
    Server server;
    ServerListener networkListener;
    GameMap gameMap;
    ArrayList<Player> players;
    Player player;
    int howManyClients;
    private int howManyConnected;
    RoboRally game;


    public GameServer(final RoboRally game) {
        this.game = game;

        Scanner in = new Scanner(System.in);
        System.out.println("how many clients");
        howManyClients = in.nextInt();

        final PlayScreen playScreen = new PlayScreen(this.game, howManyClients + 1);
        gameMap = playScreen.getGameMap();

//        players = new ArrayList<>();
        player = new Player(0);
//        players.add(player);
//        gameMap.initializePlayer(player);

        final ArrayList<ProgramCard> listOfAllMoves = new ArrayList<>();

        server = new Server(55555, 55555);
//        networkListener = new ServerListener();


        try {
            server.bind(portNumber, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        NetworkUtils networkUtils = new NetworkUtils();
        networkUtils.registerPackets(server.getKryo());
        server.start();

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {

                if (object instanceof Packets.PacketRequest) {
                    Packets.PacketRequestAnswer answer = new Packets.PacketRequestAnswer();
                    answer.IPAdress = "localhost";
                    answer.accepted = true;
                    connection.sendTCP(answer);
                    howManyConnected++;
                }

                if(howManyConnected == howManyClients) {
                    Packets.PacketStartGame startGame = new Packets.PacketStartGame();
                    startGame.howManyPlayers = howManyClients+1;
                    startGame.yourID = connection.getID();
                    connection.sendTCP(startGame);
                    Gdx.app.postRunnable(new Runnable() {
                        public void run() {
                        game.setScreen(playScreen);
                        }
                    });
                }

                if (object instanceof Packets.PacketListOfMoves) {
                    //TODO Update positions and send back updated positions
                    ArrayList<ProgramCard> movesFromClient = ((Packets.PacketListOfMoves) object).movesToSend;
                    Packets.PacketListOfMovesFromServer listOfMovesFromServer = new Packets.PacketListOfMovesFromServer();
                    if (player.getHandChosen()) {
                        for (int i = 0; i < player.getPlayerDeck().handSize(); i++) {
                            listOfMovesFromServer.allMoves.add(player.getPlayerDeck().getCardFromHand());
                            listOfMovesFromServer.allMoves.clear();
                            listOfMovesFromServer.id = 0;
                        }
                    }

                    if (!movesFromClient.isEmpty()) {

                        if (((Packets.PacketListOfMoves) object).id == 1) {
                            listOfMovesFromServer.allMoves.addAll(movesFromClient);
                            listOfMovesFromServer.id = 1;

                        } else if (((Packets.PacketListOfMoves) object).id == 2) {
                            listOfMovesFromServer.allMoves.addAll(movesFromClient);
                            listOfMovesFromServer.id = 2;
                        } else {
                            listOfMovesFromServer.allMoves.addAll(movesFromClient);
                            listOfMovesFromServer.id = 3;
                        }

                    connection.sendTCP(listOfMovesFromServer);
                    listOfMovesFromServer.allMoves.clear();
                    }

                }
            }

        });
    }

    public static void main(String[]args) {
        GameServer server = new GameServer(new RoboRally());

    }
}




