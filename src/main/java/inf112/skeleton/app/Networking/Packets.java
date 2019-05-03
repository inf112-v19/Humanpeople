package inf112.skeleton.app.Networking;


import inf112.skeleton.app.Cards.ProgramCard;


import java.util.ArrayList;

public class Packets {
    public static class PacketRequest {  }

    public static  class PacketRequestAnswer {public boolean accepted; }

    public static class PacketListOfMoves { public ArrayList<ProgramCard> movesToSend = new ArrayList<>(); public int id; }

    public static class PacketListOfMovesFromServer { public ArrayList<ProgramCard> allMoves = new ArrayList<>(); public int id;}

    public static class PacketServerRequiersMoves {}

    public static class PacketEndConnection { }

    public static class PacketStartGame { public int howManyPlayers; public int yourID; }

    public static class PacketIamDead{public int ID;}

    public static class PacketIAmPoweredDown { public int ID;}

    public static class PacketStartRound {}
}
