package inf112.skeleton.app.Networking;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Screen.PlayScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameClient {

    private Client client;
    private int portSocket;
    private int timeToWaitForServerToRespond = 5000;
    private String IPAddress;

    private Player player;
    private int myId;
    private RoboRally game;
    private PlayScreen playScreen;

    private boolean isGameStarted;
    private boolean playerSentPoweredDown;
    private boolean iHaveDied;
    private GameMap gameMap;

    public GameClient(final RoboRally game, String IPAdress, int port) {
        playScreen = null;
        this.game = game;
        client = new Client();
        this.IPAddress = IPAdress;
        this.portSocket = port;

        NetworkUtils networkUtils = new NetworkUtils();
        networkUtils.registerPackets(client.getKryo());
        new Thread(client).start();

        try {
            client.connect(timeToWaitForServerToRespond, IPAddress, portSocket, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Packets.PacketRequest connectionRequest = new Packets.PacketRequest();
        client.sendTCP(connectionRequest);

        client.addListener(new Listener() {

            public void received(final Connection connection, final Object object) {

                if (object instanceof Packets.PacketRequestAnswer) {
                    if (((Packets.PacketRequestAnswer) object).accepted != true) {
                        connection.close();
                    }
                }

                if (object instanceof Packets.PacketStartGame) {
                    int howManyPlayers = ((Packets.PacketStartGame) object).howManyPlayers;
                    myId = ((Packets.PacketStartGame) object).yourID;
                    initializeMyGame(howManyPlayers, myId);
                }

                if (isGameStarted) {
                    //If poweredDown let server know
                    if (!player.isActive() && !playerSentPoweredDown) {
                        playerSentPoweredDown = true;
                        Packets.PacketIAmPoweredDown iAmPoweredDown = new Packets.PacketIAmPoweredDown();
                        iAmPoweredDown.ID = myId;
                        connection.sendTCP(iAmPoweredDown);
                    }
                    if (player.isActive()) {
                        playerSentPoweredDown = false;
                    }
                    //Another player powered down
                    if (object instanceof Packets.PacketIAmPoweredDown) {
                        int id = ((Packets.PacketIAmPoweredDown) object).ID;
                        playScreen.getGameMap().getPlayers().get(id).powerDown();

                    }
                    // in case this client is dead in game
                    if (!player.isAlive() && !iHaveDied) {
                        iHaveDied = true;
                        System.out.println("I HAVE DIED");
                        Packets.PacketIamDead iamDead = new Packets.PacketIamDead();
                        iamDead.ID = player.getId();
                        connection.sendTCP(iamDead);
                    }
                }

                //When client has confirmed hand it send it to server
                if (object instanceof Packets.PacketServerRequiersMoves) {
                    System.out.println("CLIENT" + connection.getID() + " got RequestMoves");
                    if (playerChoseHand()) {
                        Packets.PacketListOfMoves listOfMoves = new Packets.PacketListOfMoves();
                        for (int i = 0; i < 5; i++) {
                            listOfMoves.movesToSend.add(player.getPlayerDeck().getCardFromHand());
                        }
                        listOfMoves.id = myId;
                        connection.sendTCP(listOfMoves);
                        listOfMoves.movesToSend.clear();
                    }
                }

                //Getting hands from server and adding them to gamemap
                if (object instanceof Packets.PacketListOfMovesFromServer) {
                    final int id = ((Packets.PacketListOfMovesFromServer) object).id;
                    final ArrayList<ProgramCard> list = ((Packets.PacketListOfMovesFromServer) object).allMoves;
                    Gdx.app.postRunnable(new Runnable() {
                        public void run() {
                            gameMap.getHandsFromServer(list, id);
                        }
                    });
                }

                //When server lets client know it can start the game
                if (object instanceof Packets.PacketStartRound) {
                    gameMap.setStartRound(true);
                }

            }
        });
    }

    private void initializeMyGame(final int howManyPlayers, final int myId) {
        Gdx.app.postRunnable(new Runnable() {

            public void run() {
                playScreen = new PlayScreen(game, howManyPlayers, true, "assets/riskyExchange.tmx");

                playScreen.initializeUI(myId);
                player = playScreen.getGameMap().getPlayers().get(myId);

                game.setScreen(playScreen);
                playScreen.setMyID(myId);
                gameMap = playScreen.getGameMap();
                isGameStarted = true;
            }
        });
    }

    private boolean playerChoseHand() {
        return player != null && player.getHandChosen() && player.getPlayerDeck().handSize() == 5;
    }
}