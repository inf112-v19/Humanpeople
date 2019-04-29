package inf112.skeleton.app.Networking;


import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;

import java.util.ArrayList;

public class Packets {
    public static class PacketRequest { Player player;
    }
    public static  class PacketRequestAnswer { public String IPAdress;  public boolean accepted; public RoboRally game;
    }
    public static class PacketListOfMoves { public ArrayList<ProgramCard> movesToSend = new ArrayList<>(); public int id;
    }
    public static class PacketListOfMovesFromServer {
        public ArrayList<ProgramCard> allMoves = new ArrayList<>(); public int id;
    }

    public static class PacketServerRequiersMoves {}
    public static class PacketEndConnection {

    }

    public static class PacketStartGame { public int howManyPlayers; public int yourID;

    }
}
