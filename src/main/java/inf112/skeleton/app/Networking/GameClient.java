package inf112.skeleton.app.Networking;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Screen.PlayScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameClient {


    public Client client;
    private int portSocket = 25135;
    ClientListener networkListener;
    private int timeToWaitForServerToRespond = 5000;

    //J-option ping, what would you like to connect to
    private String IPAddress = "10.113.28.4";
    private boolean connected;
    private Player player;
    RoboRally game;
    PlayScreen playScreen;
    private int myId;

    //Possibly take player as argument
    public GameClient(final RoboRally game) {
        playScreen = null;
        this.game = game;
        connected = false;
        client = new Client();

        NetworkUtils networkUtils = new NetworkUtils();
        networkUtils.registerPackets(client.getKryo());
        new Thread(client).start();

        Scanner in = new Scanner(System.in);

//        System.out.println("What port to connect to?");
//        portSocket = in.nextInt();
//        System.out.println("What IP?");
//        IPAddress = in.nextLine();

        //If connected
        try {
            client.connect(timeToWaitForServerToRespond, IPAddress, portSocket, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(client.getUpdateThread().getId());

        Packets.PacketRequest connectionRequest = new Packets.PacketRequest();
        client.sendTCP(connectionRequest);

        client.addListener(new Listener() {

            public void received(final Connection connection, final Object object) {

                if(object instanceof Packets.PacketRequestAnswer) {
                    if(((Packets.PacketRequestAnswer) object).accepted) {
                        IPAddress = ((Packets.PacketRequestAnswer) object).IPAdress;
                        connected = true;

                    }
                    else
                        connection.close();
                }

                if(object instanceof Packets.PacketStartGame) {
                    System.out.println("conn: " + connection.getID() + " , ");
                    final int howManyPlayers = ((Packets.PacketStartGame) object).howManyPlayers;
                    Gdx.app.postRunnable(new Runnable() {

                        public void run() {

                        playScreen = new PlayScreen(game, howManyPlayers);
                        myId = ((Packets.PacketStartGame) object).yourID;
                        playScreen.initializeUI(myId);
                        player = playScreen.getGameMap().getPlayers().get(myId);
                        game.setScreen(playScreen);
                        playScreen.setMyID(myId);
                        }
                    });
                }

                if(object instanceof Packets.PacketServerRequiersMoves) {
                    System.out.println("CLIENT" +connection.getID() + " got RequestMoves");
                    if (player!= null && player.getHandChosen() && player.getPlayerDeck().handSize() == 5) {
                        Packets.PacketListOfMoves listOfMoves = new Packets.PacketListOfMoves();
                        for (int i = 0; i < 5; i++) {
                            listOfMoves.movesToSend.add(player.getPlayerDeck().getCardFromHand());
                        }
                        listOfMoves.id = myId;
                        connection.sendTCP(listOfMoves);
                        listOfMoves.movesToSend.clear();
                    }
                }

                if(object instanceof Packets.PacketListOfMovesFromServer) {
                    System.out.println("CLIENT" +connection.getID() + " got ListOfMoves from server");
                    final int id = ((Packets.PacketListOfMovesFromServer) object).id;
                    final ArrayList<ProgramCard> list = ((Packets.PacketListOfMovesFromServer) object).allMoves;

                    Gdx.app.postRunnable(new Runnable() {
                        public void run() {
                            System.out.println("CLIENT " + connection.getID() + "ADDING CLIENT" + id +"s MOVES TO HIS GAMEMAP");
                            playScreen.getGameMap().getHandsFromServer(list, id);
                        }
                    });
                }
            }
        });
    }
}
