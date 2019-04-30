package inf112.skeleton.app.Networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class ServerListener extends Listener {

    public ServerListener() {

    }

    public void connected(Connection connection) {
        System.out.println("Someone has connected");

    }

    public void disconnected(Connection connection) {
        System.out.println("Someone has disconnected");

    }

    public void received(Connection connection, Object object) {

        if(object instanceof Packets.PacketRequest) {
            //Check for correct connection
            Packets.PacketRequestAnswer answer = new Packets.PacketRequestAnswer();
            answer.IPAdress = "localhost";
            answer.accepted = true;
            connection.sendTCP(answer);

        }

        if(object instanceof Packets.PacketListOfMoves) {

        }


    }

}
