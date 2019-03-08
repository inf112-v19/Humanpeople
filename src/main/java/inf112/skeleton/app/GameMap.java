package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;


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
    //List of players and list of their cells on the map
    private ArrayList<Player> players;


    //Takes String (filename for tileset), int (number of players)
    public GameMap(String name, int nPlayers) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(name);
        this.grid = new Grid(map);
        this.tiles = map.getTileSets().getTileSet("testTileset");
        this.players = new ArrayList<>();
        this.nPlayers = nPlayers;
        this.programCardDeck = new ProgramCardDeck();
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get(2);
        playerTiles = new ArrayList<PlayerLayerObject>();
        initializePlayers();

    }

    private void initializePlayers() {
        //Initializes each player and gives them a unique ID and cell
        //Arrays 'players' and 'cells' have corresponding player/cell indices
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

    /**
     * Pick random cards for all players
     */
    public void chooseRandomCardsForAllPlayersHand() {
        for (Player player : players) {
            player.select5FirstCards();
        }
    }

    //Draws players
    public void drawPlayers() {
        for (Player player : players) {
            Position pos = player.getPosition();

            TiledMapTileLayer.Cell avatar = new TiledMapTileLayer.Cell();
            avatar.setTile(player.getAvatar());

            playerLayer.setCell(pos.getX(), pos.getY(), avatar);
        }
    }

    /**
     * Draw single player
     *
     * @param player
     */
    public void drawPlayer(Player player) {
        Position pos = player.getPosition();

        TiledMapTileLayer.Cell avatar = new TiledMapTileLayer.Cell();
        avatar.setTile(player.getAvatar());

        playerLayer.setCell(pos.getX(), pos.getY(), avatar);
    }

    //Moves players one by one
    public void movePlayers() {
        for (Player player : players) {
            // If the players hand is empty then give out 9 new cards and select 5 cards for hand
            if (player.getPlayerDeck().handIsEmpty()) {
                giveOutCardsToPlayer(player);
            }
            Direction dir = player.getDirection();
            Position pos = player.getPosition();
            ProgramType programType = player.getMove();

            // If the player has drawn a move card then update player nSteps-time if legal move
            if (programType.isMoveCard()) {
                int nSteps = programType.nSteps();
                for (int i = 0; i < nSteps; i++) {
                    Position newPos = player.getPosition();
                    if (canGo(dir, newPos)) {
                        movePlayerTilesInList(dir);
                    }
                }
            }
            playerLayer.setCell(pos.getX(), pos.getY(), null);
        }
        drawPlayers();
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

    //Check if valid position
    public boolean canGo(Direction dir, Position pos) {
        playerTiles.clear();
        playerTiles = grid.numberOfPlayersToMove(dir, pos);

        if (playerTiles.size() > 0) {

            return true;
        } else {
            return false;
        }
    }

    public TiledMapTileLayer getPlayerLayer() {
        return playerLayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TiledMap getMap() {
        return map;
    }

    //TEST ZONE:

    //For testing purposes only
    public void movePlayer(int playerId, ProgramCard card) {
        ProgramType programType = card.getProgramType();

        Player player = players.get(playerId);
        Direction playerDir = player.getDirection();
        Direction moveDir = playerDir;
        Position pos = player.getPosition();

        //If movement card
        if (programType.isMoveCard()) {

            if(programType == ProgramType.BACKUP)
                moveDir = rotate(ProgramType.UTURN, playerDir);

            int nSteps = programType.nSteps();
            for (int i = 0; i < nSteps; i++) {
                Position newPos = player.getPosition();

                if (canGo(moveDir, newPos))
                    movePlayerTilesInList(moveDir);

            }
            if(programType == ProgramType.BACKUP)
                player.getPlayerTile().setDirection(playerDir);
        }
        //If rotation card
        else {
            Direction rotated = rotate(card.getProgramType(), playerDir);
            player.getPlayerTile().setDirection(rotated);
        }
        playerLayer.setCell(pos.getX(), pos.getY(), null);
        drawPlayers();
    }
    //For testing purposes only
    public Direction rotate(ProgramType rotate, Direction currentDir){
        switch(rotate) {
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
