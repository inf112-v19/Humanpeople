package inf112.skeleton.app;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class nett {
    Server server = new  Server();
    public nett(){
        server.start();
        try {
            server.bind(64554,54776);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
