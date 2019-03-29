package inf112.skeleton.app.Game;

import com.badlogic.gdx.maps.tiled.*;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;
import inf112.skeleton.app.Directions.StartingPositions;
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
    private final TiledMapTileLayer specialLayer;
    private final TiledMapTileLayer backupLayer;
    private TiledMapTileSet tiles;
    private final int nPlayers;
    private StartingPositions startingPositions;
    private ProgramCardDeck programCardDeck;
    private ArrayList<PlayerLayerObject> playerTiles;
    private ArrayList<Player> players;

    private Round round;
    private boolean cardsDealt;

    public GameMap(String filename, int nPlayers) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(filename);
        this.grid = new Grid(map);
        this.tiles = map.getTileSets().getTileSet("testTileset");
        this.players = new ArrayList<>();
        this.nPlayers = nPlayers;
        this.startingPositions = new StartingPositions(grid.getWidth(), grid.getHeight());
        this.programCardDeck = new ProgramCardDeck();
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get(5);
        this.specialLayer = (TiledMapTileLayer) map.getLayers().get(1);
        this.backupLayer = (TiledMapTileLayer) map.getLayers().get(4);
        this.playerTiles = new ArrayList<>();
        initializePlayers();
        this.cardsDealt = true;

        this.round = new Round();
    }

    /**
     * Creates all players and gives out cards
     */
    private void initializePlayers() {
        //Initializes each player and gives them a unique ID
        for (int id = 0; id < nPlayers; id++) {
            Player player = new Player(id);
            players.add(player);
            Position startingPosition = startingPositions.getStartingPosition(id);
            player.setPosition(startingPosition);
            player.setBackup(startingPosition);
            PlayerLayerObject playerTile = player.getPlayerTile();
            playerTile.setSprite(tiles);
            grid.setPlayerPosition(playerTile);
            setBackup(player);
        }
        // Give out cards to players
        programCardDeck.giveOutCardsToAllPlayers(players);

        drawPlayers();
    }

    public void dealCards() {
        getDeck().giveOutCardsToAllPlayers(players);
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

    public void drawBackup(Player player) {
        Position pos = player.getBackup();

        TiledMapTileLayer.Cell avatar = new TiledMapTileLayer.Cell();
        avatar.setTile(player.getBackupAvatar());

        backupLayer.setCell(pos.getX(), pos.getY(), avatar);
    }

    public void addPlayerHandToNewRound() {
        if (!round.isSet()) {
            round = new Round();
            cardsDealt = false;
            int amountOfPhases = 5;
            for (int i = 0; i < amountOfPhases; i++) {
                ArrayList<ProgramCard> cardsToAddInPhaseI = new ArrayList<>();
                for (Player player : players) {
                    // If the players hand is empty then give out 9 new cards and select 5 cards for hand
                    // Temporary solution. Card selection system is coming.
                    if (player.getPlayerDeck().handIsEmpty()) {
                        giveOutCardsToPlayer(player);
                    }
                    ProgramCard tempCard = player.getPlayerDeck().getCardFromHand();
                    tempCard.setPlayerThatPlayedTheCard(player.getId());
                    cardsToAddInPhaseI.add(tempCard);
                }
                round.addPhases(new Phase(cardsToAddInPhaseI));
            }
        }
    }

    public void setAllPlayerHandsChosen(boolean handsChosen) {
        for (Player player : players) {
            player.setHandChosen(handsChosen);
        }
    }

    public void movePlayer(int playerId, ProgramCard card) {
        Player player = players.get(playerId);
        // A player which is destroyed cannot move
        if (player.isDestroyed()) {
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
     * Removes the given player's backup point and sets it to the player's current position
     *
     * @param player
     */
    public void setBackup(Player player) {
        Position previousBackupPosition = player.getBackup();
        grid.removeBackupPosition(previousBackupPosition);
        backupLayer.setCell(previousBackupPosition.getX(), previousBackupPosition.getY(), null);

        grid.setBackupPosition(player.getPlayerTile());
        Position currentPosition = player.getPosition();
        player.setBackup(currentPosition);
        drawBackup(player);
    }

    /**
     * Sets players position to backup and draws players
     *
     * @param player
     */
    public void returnToBackup(Player player) {
        playerLayer.setCell(player.getPosition().getX(), player.getPosition().getY(), null);
        grid.removePlayerPosition(player.getPosition());
        Position backup = player.getBackup();

        if (existsPlayerOnBackup(player)) {
            movePlayerToNearestField(player);
        } else {
            player.setPosition(backup);
            grid.setPlayerPosition(player.getPlayerTile());
        }
        drawPlayers();
    }

    public boolean existsPlayerOnBackup(Player player) {
        Position backup = player.getBackup();
        TiledMapTileLayer.Cell currentPlayerLayerCell = playerLayer.getCell(backup.getX(), backup.getY());
        if (currentPlayerLayerCell == null)
            return false;
        return true;
    }

    /**
     * If someone is standing on the backup of a player when he is due to return, then return player to a position adjacent to the backup
     *
     * @param player
     */
    public void movePlayerToNearestField(Player player) {
        Position backup = player.getBackup();
        if (canGo(Direction.NORTH, backup) && !grid.isHole(backup.north())) {
            Position newPosition = backup.north();
            player.setPosition(newPosition);
            grid.setPlayerPosition(player.getPlayerTile());
        } else if (canGo(Direction.EAST, backup) && !grid.isHole(backup.east())) {
            Position newPosition = backup.east();
            player.setPosition(newPosition);
            grid.setPlayerPosition(player.getPlayerTile());
        } else if (canGo(Direction.SOUTH, backup) && !grid.isHole(backup.south())) {
            Position newPosition = backup.south();
            player.setPosition(newPosition);
            grid.setPlayerPosition(player.getPlayerTile());
        } else if (canGo(Direction.WEST, backup) && !grid.isHole(backup.west())) {
            Position newPosition = backup.west();
            player.setPosition(newPosition);
            grid.setPlayerPosition(player.getPlayerTile());
        }
    }

    /**
     * Checks if player is in a state where he/she has to return to backup
     *
     * @param player
     * @return
     */
    public boolean hasToReturnToBackup(Player player) {
        if (steppedOnHole(player) || player.lostAllDamageTokens())
            return true;
        return false;
    }

    /**
     * Checks if player is stepping on a hole. If so, kill player
     *
     * @param player
     * @return true if player is in hole
     */
    public boolean steppedOnHole(Player player) {
        Position currentPosition = player.getPosition();
        if (grid.isHole(currentPosition)) {
            player.destroy();
            return true;
        }
        return false;
    }

    /**
     * If player is standing on flag and another player does not have a backup there already, then set backup
     */
    public void steppedOnFlag() {
        for (Player player : players) {
            if (playerSteppedOnFlag(player)) {
                if (!grid.isBackup(player.getPosition()))
                    setBackup(player);
            }
        }
    }

    /**
     * Checks if player is stepping on a flag
     *
     * @param player
     * @return true if player is in hole
     */
    public boolean playerSteppedOnFlag(Player player) {
        Position currentPosition = player.getPosition();
        return grid.isFlag(currentPosition);
    }

    public void preformNextMovement() {
        returnDestroyedPlayersToBackup();

        if (round.isSet()) {
            if (!round.isCompleted()) {
                if (round.getCurrentPhase().getPhaseComplete()) {
                    // If any player has stepped on a flag then set current position as backup
                    steppedOnFlag();
                    System.out.println("Phase " + (round.getCurrentPhaseNumber() + 1) + " er ferdig");
                    round.nextPhase();
                } else {
                    ProgramCard currentCard = round.getNextMovementCard();
                    movePlayer(currentCard.getPlayerThatPlayedTheCard(), currentCard);
                }
            } else {
                if (!cardsDealt) {
                    dealCards();
                    cardsDealt = true;
                    setAllPlayerHandsChosen(false);
                }
                // If round is complete, revive all players for further play
                reviveAllPlayers();
            }
        }
    }

    /**
     * If a player has to return to backup, then they are returned to backup
     */
    public void returnDestroyedPlayersToBackup() {
        for (Player player : players) {
            if (hasToReturnToBackup(player)) {
                returnToBackup(player);
            }
        }
    }

    public void reviveAllPlayers() {
        for (Player player : players) {
            player.revive();
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

    public ProgramCardDeck getDeck() {
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


    public boolean getCardsDealt() {
        return cardsDealt;
    }

    public void setCardsDealt(boolean cardsDealt) {
        this.cardsDealt = cardsDealt;
    }
}