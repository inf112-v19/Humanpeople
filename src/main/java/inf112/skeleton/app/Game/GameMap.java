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


import java.util.ArrayList;

public class GameMap {

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private Grid grid;
    private final TiledMapTileLayer playerLayer;
    private TiledMapTileSet tiles;
    private final int nPlayers;
    private StartingPositions startingPositions;
    private ProgramCardDeck programCardDeck;
    private ArrayList<PlayerLayerObject> playerTiles;
    private ArrayList<Player> players;
    private ArrayList<ProgramCard> nextMovement;
    private int movesLeftOfCurrentCard;


    public GameMap(String filename, int nPlayers) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(filename);
        this.grid = new Grid(map);
        this.tiles = map.getTileSets().getTileSet("testTileset");
        this.players = new ArrayList<>();
        this.nPlayers = nPlayers;
        this.startingPositions = new StartingPositions(grid.getWidth(), grid.getHeight());
        this.programCardDeck = new ProgramCardDeck();
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get(2);
        this.playerTiles = new ArrayList<>();
        initializePlayers();

        nextMovement = new ArrayList<>();
        movesLeftOfCurrentCard = -1;


    }

    /**
     * Creates all players and gives out cards
     */
    private void initializePlayers() {
        //Initializes each player and gives them a unique ID
        for (int id = 0; id < nPlayers; id++) {
            Player player = new Player(tiles, id);
            players.add(player);
            Position startingPosition = startingPositions.getStartingPosition(id);
            player.setPosition(startingPosition);
            player.setBackup(startingPosition);
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


    public void addMovementFromAllPlayers() {
        for (Player player : players) {

            // If the players hand is empty then give out 9 new cards and select 5 cards for hand
            // Temporary solution. Card selection system is coming.
            if (player.getPlayerDeck().handIsEmpty()) {
                giveOutCardsToPlayer(player);
            }
//            movePlayer(player.getId(), player.getPlayerDeck().getCardFromHand());

            //TODO flytt id adding til fornuftig sted(for at man skal vite hvem som spilte kortet)
            ProgramCard tempCard = player.getPlayerDeck().getCardFromHand();
            tempCard.setPlayerThatPlayedTheCard(player.getId());


            addMovement(tempCard);
        }
    }

    public  void addMovement(ProgramCard card){
        nextMovement.add(card);
    }

    public void movePlayer(int playerId, ProgramCard card) {
        Player player = players.get(playerId);
        // Checks if player is in a state where he/she has to return to backup
        if (hasToReturnToBackup(player)) {
            returnToBackup(player);
            return;
        }
        ProgramType programType = card.getProgramType();
        Direction playerDir = player.getDirection();
        Direction moveDir = playerDir;

        if (programType.isMoveCard()) {

            if (programType == ProgramType.BACKWARD)
                moveDir = rotate(ProgramType.UTURN, playerDir);



                Position newPos = player.getPosition();

                if (canGo(moveDir, newPos))
                    movePlayerTilesInList(moveDir);

            if (programType == ProgramType.BACKWARD)
                player.getPlayerTile().setDirection(playerDir);
        }
        //If rotation card
        else {
            Direction rotated = rotate(card.getProgramType(), playerDir);
            player.getPlayerTile().setDirection(rotated);
        }

        drawPlayers();
    }

    /**
     * Sets players position to backup and draws players
     * @param player
     */
    public void returnToBackup(Player player) {
        playerLayer.setCell(player.getPosition().getX(), player.getPosition().getY(), null);
        grid.removePlayerPosition(player.getPosition());
        Position backup = player.getBackup();
        player.setPosition(backup);
        grid.setPlayerPosition(player.getPlayerTile());
        drawPlayers();
    }

    /**
     * Checks if player is in a state where he/she has to return to backup
     * @param player
     * @return
     */
    public boolean hasToReturnToBackup(Player player) {
        if (steppedOnHole(player) || player.lostAllDamageTokens() || walkedOffMap(player))
            return true;
        return false;
    }

    /**
     * Checks if player has walked off the game map
     * @param player
     * @return true if player is off map
     */
    public boolean walkedOffMap(Player player) {
        return false;
    }

    /**
     * Checks if player is stepping on a hole
     * @param player
     * @return true if player is in hole
     */
    public boolean steppedOnHole(Player player) {
        Position currentPosition = player.getPosition();
        if (grid.isHole(currentPosition))
            return true;
        return false;
    }

    public void preformNextMovement(){
        if(!nextMovement.isEmpty()){
            ProgramCard currentCard = nextMovement.get(0);
            if (currentCard.getProgramType().isMoveCard()){
                if(movesLeftOfCurrentCard == -1){
                    movesLeftOfCurrentCard = currentCard.getProgramType().nSteps();
                }
                movesLeftOfCurrentCard--;
                if(movesLeftOfCurrentCard == 0){
                    nextMovement.remove(0);
                    movesLeftOfCurrentCard =-1;
                }
            }
            else {
                nextMovement.remove(0);
            }
            movePlayer(currentCard.getPlayerThatPlayedTheCard(),currentCard);

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
