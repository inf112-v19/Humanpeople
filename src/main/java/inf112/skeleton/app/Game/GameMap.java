package inf112.skeleton.app.Game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Round.Phase;
import inf112.skeleton.app.Round.Round;


import java.util.ArrayList;

public class GameMap {

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private Grid grid;
    private final TiledMapTileLayer playerLayer;
    private TiledMapTileSet tiles;
    private final int nPlayers;
    private ProgramCardDeck programCardDeck;
    private ArrayList<PlayerLayerObject> playerTiles;
    private ArrayList<Player> players;

    private Round round;


    public GameMap(String filename, int nPlayers) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(filename);
        this.grid = new Grid(map);
        this.tiles = map.getTileSets().getTileSet("testTileset");
        this.players = new ArrayList<>();
        this.nPlayers = nPlayers;
        this.programCardDeck = new ProgramCardDeck();
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get(2);
        this.playerTiles = new ArrayList<>();
        initializePlayers();


        round = new Round();



    }

    /**
     * Creates all players and gives out cards
     */
    private void initializePlayers() {
        //Initializes each player and gives them a unique ID
        for (int id = 0; id < nPlayers; id++) {
            Player player = new Player(tiles, id);
            players.add(player);
            grid.setPlayerPosition(player.getPlayerTile());
        }
        // Give out cards to players
        programCardDeck.giveOutCardsToAllPlayers(players);
        chooseRandomCardsForAllPlayersHand();

        drawPlayers();
    }

    public void chooseRandomCardsForAllPlayersHand() {
        for (Player player : players) {
            player.select5FirstCards();
        }
    }

    public void drawPlayers() {
        for (Player player : players) {
            drawPlayer(player);
        }
    }

    public void drawPlayer(Player player) {
        Position pos = player.getPosition();

        TiledMapTileLayer.Cell avatar = new TiledMapTileLayer.Cell();
        avatar.setTile(player.getAvatar());

        playerLayer.setCell(pos.getX(), pos.getY(), avatar);
    }


    public void addPlayerHandToNewRound() {
        if(!round.isSet()){
            round = new Round();

            int amountOfPhases = 5;
            for(int i = 0; i< amountOfPhases; i++){
                ArrayList<ProgramCard> cardsToAddInPhaseI = new ArrayList<>();
                for (Player player : players) {

                    // If the players hand is empty then give out 9 new cards and select 5 cards for hand
                    // Temporary solution. Card selection system is coming.
                    if (player.getPlayerDeck().handIsEmpty()) {
                        giveOutCardsToPlayer(player);
                    }
                    //TODO flytt id adding til fornuftig sted(for at man skal vite hvem som spilte kortet)
                    ProgramCard tempCard = player.getPlayerDeck().getCardFromHand();
                    tempCard.setPlayerThatPlayedTheCard(player.getId());

                    cardsToAddInPhaseI.add(tempCard);
                }
                round.addPhases(new Phase(cardsToAddInPhaseI));
            }
        }
    }

    public void movePlayer(int playerId, ProgramCard card) {
        ProgramType programType = card.getProgramType();
        Player player = players.get(playerId);

        Direction playerDir = player.getDirection();
        Direction moveDir = playerDir;

        if (programType.isMoveCard()) {

            if (programType == ProgramType.BACKUP)
                moveDir = rotate(ProgramType.UTURN, playerDir);



                Position newPos = player.getPosition();

                if (canGo(moveDir, newPos))
                    movePlayerTilesInList(moveDir);

            if (programType == ProgramType.BACKUP)
                player.getPlayerTile().setDirection(playerDir);
        }
        //If rotation card
        else {
            Direction rotated = rotate(card.getProgramType(), playerDir);
            player.getPlayerTile().setDirection(rotated);
        }
        drawPlayers();
    }

    public void preformNextMovement(){
        if(round.isSet()){
            if(!round.isCompleted()){
                if(round.getCurrentPhase().getPhaseComplete()){
                    //Todo do special movment (rullebånd og slikt)
                    System.out.println("phase "+ (round.getCurrentPhaseNumber()+1) +" er ferdig");
                    round.nextPhase();
                }else
                {
                    ProgramCard currentCard = round.getNextMovementCard();
                    movePlayer(currentCard.getPlayerThatPlayedTheCard(),currentCard);
                }

            }
        }
    }

    /**
     * Give out cards to player deck and player selects 5 cards
     *
     * @param player
     */
    public void giveOutCardsToPlayer(Player player) {
        programCardDeck.giveOutCardsToPlayer(player);
        player.select5FirstCards();
    }

    public ProgramCardDeck getDeck(){
        return programCardDeck;
    }

    /**
     * Flytter alle spillere som koliderer i direction og oppdaterer grid
     *
     * @param direction
     */
    public void movePlayerTilesInList(Direction direction) {
        int numberOfPlayersToMove = playerTiles.size() - 1;

        //Flytter siste spiller først for å ikke ødelegge grid.set og grid.remove logikken
        for (int i = numberOfPlayersToMove; i >= 0; i--) {
            PlayerLayerObject playerLayerObject = playerTiles.get(i);
            Position playerPosition = playerLayerObject.getPosition();

            grid.removePlayerPosition(playerPosition);
            playerLayer.setCell(playerPosition.getX(), playerPosition.getY(), null);

            playerLayerObject.moveTileInDirection(direction);

            grid.setPlayerPosition(playerLayerObject);
        }
    }

    /**
     * Check if valid position
     *
     * @param dir
     * @param pos
     * @return true if valid position
     */
    public boolean canGo(Direction dir, Position pos) {
        playerTiles.clear();
        playerTiles = grid.numberOfPlayersToMove(dir, pos);

        if (playerTiles.size() > 0)
            return true;

        return false;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TiledMap getMap() {
        return map;
    }

    public Direction rotate(ProgramType rotate, Direction currentDir) {
        switch (rotate) {
            case ROTATELEFT:
                return Direction.rotate(currentDir, -1);
            case ROTATERIGHT:
                return Direction.rotate(currentDir, 1);
            case UTURN:
                return Direction.rotate(Direction.rotate(currentDir, 1), 1);
        }
        return currentDir;
    }
}
