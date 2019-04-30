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
    GameMap gameMap;
    Player player;
    int howManyClients;
    private int howManyConnected;
    RoboRally game;
    private boolean isGameStarted = false;

    public GameServer(final RoboRally game) {
        this.game = game;

        Scanner in = new Scanner(System.in);
        System.out.println("how many clients");
        howManyClients = in.nextInt();

        final PlayScreen playScreen = new PlayScreen(this.game, howManyClients + 1);
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

                if(howManyConnected == howManyClients && !isGameStarted) {
                    isGameStarted = true;
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


                    Packets.PacketServerRequiersMoves newMoves = new Packets.PacketServerRequiersMoves();
                    connection.sendTCP(newMoves);



                if (object instanceof Packets.PacketListOfMoves) {
                    //TODO Update positions and send back updated positions

                    final Packets.PacketListOfMovesFromServer listOfMovesFromServer = new Packets.PacketListOfMovesFromServer();
                    if (player.getHandChosen() && player.getPlayerDeck().handSize() == 5) {
                        for (int i = 0; i < 5; i++) {
                            listOfMovesFromServer.allMoves.add(player.getPlayerDeck().getCardFromHand());
                        }

                        listOfServerMoves.addAll(listOfMovesFromServer.allMoves);
                        System.out.println("size after adding servermoves: " + listOfMovesFromServer.allMoves.size());
                        listOfMovesFromServer.id = 0;
                        connection.sendTCP(listOfMovesFromServer);
                        listOfMovesFromServer.allMoves.clear();
                    }

                    ArrayList<ProgramCard> movesFromClient = ((Packets.PacketListOfMoves) object).movesToSend;
                    if (movesFromClient.size() == 5) {
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

                        Gdx.app.postRunnable(new Runnable() {
                            public void run() {
                                System.out.println("listSize in server:; " + listOfMovesFromServer.allMoves.size());
                                gameMap.getHandsFromServer(listOfMovesFromServer.allMoves, listOfMovesFromServer.id);
                                connection.sendTCP(listOfMovesFromServer);
                                gameMap.getHandsFromServer(listOfServerMoves, 0);
                                listOfServerMoves.clear();
                                listOfMovesFromServer.allMoves.clear();
                            }
                        });
                    }
                }
            }

        });
    }

    public static void main(String[]args) {
        GameServer server = new GameServer(new RoboRally());
    }
}




