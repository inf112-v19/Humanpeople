package inf112.skeleton.app;

import com.esotericsoftware.kryonet.Client;
import java.net.InetAddress;

public class Slave {
    Client client;

    public Slave() {
        this.client = new Client();
    }
    public InetAddress checkIfServerOnline(){
        return client.discoverHost(54777,5000);
    }
}

