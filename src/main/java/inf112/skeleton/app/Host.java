package inf112.skeleton.app;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class Host {
    Server server = new  Server();
    public Host(){
        server.start();
        try {
            server.bind(64554,54776);
            Registrer(server.getKryo());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void Registrer(Kryo kryo){
        //server.stop();


    }


}
