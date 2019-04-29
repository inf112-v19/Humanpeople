package inf112.skeleton.app.Networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientListener extends Listener {

    private String IPAdress;
    boolean connected = false;
    public ClientListener () {

    }

    public void connected(Connection connection) {

    }

    public void disconnected(Connection connection) {

    }

    public void received(Connection connection, Object object) {
        if(object instanceof Packets.PacketRequestAnswer) {
            if(((Packets.PacketRequestAnswer) object).accepted) {
                IPAdress = ((Packets.PacketRequestAnswer) object).IPAdress;
                connected = true;
            }
            else
                connection.close();
        }
        while(connected) {
            Packets.PacketListOfMoves listOfMoves = new Packets.PacketListOfMoves();
            connection.sendTCP(listOfMoves);
        }
    }

    public String getIPAddress() {
        return IPAdress;
    }
}

