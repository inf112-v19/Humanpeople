package inf112.skeleton.app.Networking;

import com.esotericsoftware.kryo.Kryo;

public class NetworkUtils {


    public void registerPackets(Kryo kryo) {

        kryo.register(Packets.PacketRequest.class);
        kryo.register(Packets.PacketRequestAnswer.class);
        kryo.register(Packets.PacketListOfMoves.class);
        kryo.register(Packets.PacketListOfMovesFromServer.class);
        kryo.register(Packets.PacketEndConnection.class);
        kryo.register(Packets.PacketStartGame.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(inf112.skeleton.app.Cards.ProgramCard.class);
        kryo.register(inf112.skeleton.app.Cards.ProgramType.class);
        kryo.register(Packets.PacketServerRequiersMoves.class);
        kryo.register(Packets.PacketPlayerDisconnected.class);


    }
}
