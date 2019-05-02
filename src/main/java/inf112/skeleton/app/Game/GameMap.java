package inf112.skeleton.app.Game;

import com.badlogic.gdx.maps.tiled.*;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Directions.*;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Round.Phase;
import inf112.skeleton.app.Round.Round;


import java.util.ArrayList;

public class GameMap {

    private boolean isMultiplayer;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private Grid grid;

    private final TiledMapTileLayer playerLayer;
    private final TiledMapTileLayer specialLayer;
    private final TiledMapTileLayer flagLayer;
    private final TiledMapTileLayer backupLayer1;
    private final TiledMapTileLayer backupLayer2;
    private final TiledMapTileLayer backupLayer3;
    private final TiledMapTileLayer backupLayer4;
    private final TiledMapTileLayer laserLayer;

    private TiledMapTileSet tiles;
    private final int nPlayers;
    private IStartingPosition startingPositions;
    private ProgramCardDeck programCardDeck;
    private ArrayList<PlayerLayerObject> playerTiles;
    private ArrayList<Player> players;
    private Player winner;


    private boolean laserFire = true;
    private Round round;
    private Phase currentPhase;
    private EndOfPhaseActions endOfPhaseActions;
    private EndOfRoundActions endOfRoundActions;

    private boolean cardsDealt;

    public GameMap(String filename, int nPlayers, boolean isMultiplayer) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load(filename);
        this.grid = new Grid(map);
        this.tiles = map.getTileSets().getTileSet("testTileset");
        this.players = new ArrayList<>();
        this.nPlayers = nPlayers;
        if (filename.equals("assets/testMap.tmx"))
            this.startingPositions = new TestStartingPositions(grid.getWidth(), grid.getHeight());
        else
            this.startingPositions = new StartingPositions(grid.getWidth(), grid.getHeight(), nPlayers);
        this.isMultiplayer = isMultiplayer;
        this.programCardDeck = new ProgramCardDeck();
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get(8);
        this.specialLayer = (TiledMapTileLayer) map.getLayers().get(1);
        this.flagLayer = (TiledMapTileLayer) map.getLayers().get(2);
        this.backupLayer1 = (TiledMapTileLayer) map.getLayers().get(4);
        this.backupLayer2 = (TiledMapTileLayer) map.getLayers().get(5);
        this.backupLayer3 = (TiledMapTileLayer) map.getLayers().get(6);
        this.backupLayer4 = (TiledMapTileLayer) map.getLayers().get(7);
        this.laserLayer = (TiledMapTileLayer) map.getLayers().get(9);
        this.playerTiles = new ArrayList<>();

        initializePlayers();
        this.cardsDealt = true;

        this.round = new Round();
        this.endOfPhaseActions = new EndOfPhaseActions(this, grid, map);
        this.endOfRoundActions = new EndOfRoundActions(this);
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
            if (!isMultiplayer && id > 0)
                player.setAI();
        }
        // Give out cards to players
        programCardDeck.giveOutCardsToAllPlayers(players);

        drawPlayers();
    }

    public void initializePlayer(Player player) {
        players.add(player);
        Position startingPosition = startingPositions.getStartingPosition(player.getId());
        player.setPosition(startingPosition);
        player.setBackup(startingPosition);
        PlayerLayerObject playerTile = player.getPlayerTile();
        playerTile.setSprite(tiles);
        grid.setPlayerPosition(playerTile);
        setBackup(player);
        drawPlayer(player);

    }

    public void setPlayerToAI(int id) {
        players.get(id).setAI();
    }

    public void dealCards() {
        getDeck().giveOutCardsToAllPlayers(players);
    }

    public void drawPlayers() {
        for (Player player : players) {
            if (player.isAlive())
                drawPlayer(player);
        }
    }

    public void drawPlayer(Player player) {
        Position pos = player.getPosition();
        TiledMapTileLayer.Cell avatar = new TiledMapTileLayer.Cell();
        // If player is destroyed then draw a grey avatar
        if (player.isDestroyed())
            avatar.setTile(player.getDestroyedAvatar());
        else
            avatar.setTile(player.getAvatar());

        playerLayer.setCell(pos.getX(), pos.getY(), avatar);
    }

    public void drawBackup(Player player) {
        int playerId = player.getId();
        Position pos = player.getBackup();
        int x = pos.getX();
        int y = pos.getY();

        TiledMapTileLayer.Cell avatar = new TiledMapTileLayer.Cell();
        avatar.setTile(player.getBackupAvatar());

        switch (playerId) {
            case 0:
                backupLayer1.setCell(x, y, avatar);
                break;
            case 1:
                backupLayer2.setCell(x, y, avatar);
                break;
            case 2:
                backupLayer3.setCell(x, y, avatar);
                break;
            case 3:
                backupLayer4.setCell(x, y, avatar);
                break;
        }
    }

    /**
     * Removes the given player's backup point and sets it to the player's current position
     *
     * @param player
     */
    public void setBackup(Player player) {
        Position previousBackupPosition = player.getBackup();
        int x = previousBackupPosition.getX();
        int y = previousBackupPosition.getY();
        grid.removeBackupPosition(previousBackupPosition, player.getId());
        switch (player.getId()) {
            case 0:
                backupLayer1.setCell(x, y, null);
                break;
            case 1:
                backupLayer2.setCell(x, y, null);
                break;
            case 2:
                backupLayer3.setCell(x, y, null);
                break;
            case 3:
                backupLayer4.setCell(x, y, null);
                break;
        }

        grid.setBackupPosition(player.getPlayerTile());
        Position currentPosition = player.getPosition();
        player.setBackup(currentPosition);
        drawBackup(player);
    }

    public void getHandsFromServer(ArrayList<ProgramCard> listOfMovesFromServer, int id) {

        players.get(id).getPlayerDeck().setPlayerHand(listOfMovesFromServer);
        System.out.println("handdsize: " + players.get(id).getPlayerDeck().handSize() + "id: " + id);
        players.get(id).setHandChosen(true);
    }

    public void addPlayerHandToNewRound() {
        if (!round.allPhasesAddedToRound()) {
            round = new Round();
            cardsDealt = false;
            int amountOfPhases = 5;
            for (int i = 0; i < amountOfPhases; i++) {
                ArrayList<ProgramCard> cardsToAddInPhaseI = new ArrayList<>();
                for (Player player : players) {
                    if (player.isActive()) {
                        // If the players hand is empty then give out 9 new cards and select 5 cards for hand
                        // Temporary solution. Card selection system is coming.
                        if (player.getPlayerDeck().handIsEmpty()) {
                            giveOutCardsToPlayer(player);
                        }

                        ProgramCard tempCard = player.getPlayerDeck().getCardFromHand();
                        tempCard.setPlayerThatPlayedTheCard(player.getId());
                        cardsToAddInPhaseI.add(tempCard);
                    }
                }
                round.addPhases(new Phase(cardsToAddInPhaseI));
            }
        }
    }

    public boolean hasAllPlayersChosenHands() {
        for (Player player : players) {
            if (!player.getHandChosen())
                return false;
        }
        return true;
    }

    public void setAllPlayerHandsChosen(boolean handsChosen) {
        for (Player player : players) {
            player.setHandChosen(handsChosen);
        }
    }

    public void movePlayer(int playerId, ProgramCard card) {
        Player player = players.get(playerId);
        // A player which is destroyed cannot move
        if (player.isDestroyed() || !player.isAlive()) {
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
        player.returnToBackup();
        drawPlayers();

    }

    /**
     * Checks if there is a player already at the given player's backup
     *
     * @param player
     * @return true if there is another player on the backup
     */
    public boolean existsPlayerOnBackup(Player player) {
        Position backup = player.getBackup();
        TiledMapTileLayer.Cell currentPlayerLayerCell = playerLayer.getCell(backup.getX(), backup.getY());
        if (currentPlayerLayerCell == null)
            return false;
        return true;
    }

    /**
     * If someone is standing on the backup of a player when he is due to return,
     * then return player to a position adjacent to the backup
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
        if (steppedOnHole(player))
            return true;
        if (player.hasBeenRemoveFromBoard() || player.hasReturnedToBackup())
            return false;
        if (player.lostAllHealth())
            return true;
        return false;
    }

    /**
     * Checks if player is stepping on a hole. If so, destroy player
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

    public void performNextMovement() {
        cleanLasers();
        returnDestroyedPlayersToBackup();

        if (round.allPhasesAddedToRound()) {
            if (!round.isCompleted()) {
                if (round.getCurrentPhase().getPhaseComplete()) {
                    currentPhase = round.getCurrentPhase();
                    endOfPhaseChecks();
                    System.out.println("Phase " + (round.getCurrentPhaseNumber() + 1) + " er ferdig");
                    round.nextPhase();
                } else {
                    ProgramCard currentCard = round.getNextMovementCard();
                    int playerId = currentCard.getPlayerThatPlayedTheCard();
                    movePlayer(currentCard.getPlayerThatPlayedTheCard(), currentCard);
                    checkBoardLasers(playerId);
                }
            } else {
                if (!cardsDealt) {
                    dealCards();
                    cardsDealt = true;
                    setAllPlayerHandsChosen(false);
                }
                // If round is complete, revive all players for further play
                endOfRoundChecks();
            }
        }
    }

    /**
     * Checks all players for end of phase actions
     */
    public void endOfPhaseChecks() {
        endOfPhaseActions.performAllChecks();
        fireLasers();
        resetHitByBoardLaser();
        drawPlayers();
        resetHitByBoardLaser();
        fireLasers();
    }

    /**
     * Checks all players for end of round actions
     */
    public void endOfRoundChecks() {
        endOfRoundActions.performAllChecks();
        drawPlayers();
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player player) {
        winner = player;

    }

    private void resetHitByBoardLaser() {
        for (Player player : players)
            player.setHitByBoardLaser(false);
    }

    /**
     * Fire both player and board lasers
     */
    public void fireLasers() {
        if (!laserFire)
            return;
        for (Player player : players) {
            if (!player.isActive() || !player.isAlive() || player.isDestroyed())
                continue;
            Position startPos = player.getPosition();
            Direction dir = player.getDirection();
            int distance = getTargetDistance(startPos, dir);
            TiledMapTileLayer.Cell laserAvatar = new TiledMapTileLayer.Cell();
            laserAvatar.setTile(player.getLaserAvatar());
            drawLaser(distance, startPos, dir, laserAvatar);
        }
    }

    public void toggleLaser() {
        laserFire = !laserFire;
        System.out.println("Laserfire = " + laserFire);
    }

    /**
     * Checks if any players are hit by the board lasers.
     * Deals 1 damage if they are.
     */
    public void checkBoardLasers(int playerId) {
        Player player = players.get(playerId);
//        for (Player player : players) {
        Position pos = player.getPosition();
            /*if (grid.isLaser(pos) && player.isHitByBoardLaser() == false) {
                System.out.println("DAMAGE BOARD LASER " + player.getPlayerTile().getColor());*/
        if (grid.isLaser(pos) && !player.isHitByBoardLaser()) {
//                System.out.println("DAMAGE BOARD LASER " + player.getPlayerTile().getColor());
            player.damagePlayer(1);
            player.setHitByBoardLaser(true);
        }
    }

    /**
     * Draw laser tiles from startPos as far as distance
     *
     * @param distance the laser can travel unhindered
     * @param startPos of the laser
     * @param dir      of the laser
     */
    public void drawLaser(int distance, Position startPos, Direction dir, TiledMapTileLayer.Cell laserAvatar) {
//        System.out.println();
//        System.out.println("DISTANCE: " + distance);
//        System.out.println();
        TiledMapTileLayer.Cell laser = new TiledMapTileLayer.Cell();
        TiledMapTileLayer.Cell crossLaser = new TiledMapTileLayer.Cell();
        crossLaser.setTile(tiles.getTile(83));
        playerLayer.setCell(startPos.getX(), startPos.getY(), laserAvatar);
        switch (dir) {
            case NORTH:
                for (int i = 1; i < distance + 1; i++) {

                    laser.setTile(tiles.getTile(82));

                    if (laserLayer.getCell(startPos.getX(), startPos.getY() + i) != null &&
                            laserLayer.getCell(startPos.getX(), startPos.getY() + i).getTile().getId() == 81) {
                        laserLayer.setCell(startPos.getX(), startPos.getY() + i, crossLaser);
                        continue;
                    }

                    laserLayer.setCell(startPos.getX(), startPos.getY() + i, laser);
                }
                break;
            case SOUTH:
                for (int i = 1; i < distance + 1; i++) {
                    laser.setTile(tiles.getTile(82));

                    if (laserLayer.getCell(startPos.getX(), startPos.getY() - i) != null &&
                            laserLayer.getCell(startPos.getX(), startPos.getY() - i).getTile().getId() == 81) {
                        laserLayer.setCell(startPos.getX(), startPos.getY() - i, crossLaser);
                        continue;
                    }
                    laserLayer.setCell(startPos.getX(), startPos.getY() - i, laser);
                }
                break;
            case EAST:
                laser.setTile(tiles.getTile(81));
                for (int i = 1; i < distance + 1; i++) {

                    if (laserLayer.getCell(startPos.getX() + i, startPos.getY()) != null &&
                            laserLayer.getCell(startPos.getX() + i, startPos.getY()).getTile().getId() == 82) {
                        laserLayer.setCell(startPos.getX() + i, startPos.getY(), crossLaser);
                        continue;
                    }
                    laserLayer.setCell(startPos.getX() + i, startPos.getY(), laser);
                }
                break;
            case WEST:
                laser.setTile(tiles.getTile(81));
                for (int i = 1; i < distance + 1; i++) {

                    if (laserLayer.getCell(startPos.getX() - i, startPos.getY()) != null &&
                            laserLayer.getCell(startPos.getX() - i, startPos.getY()).getTile().getId() == 82) {
                        laserLayer.setCell(startPos.getX() - i, startPos.getY(), crossLaser);
                        continue;
                    }
                    laserLayer.setCell(startPos.getX() - i, startPos.getY(), laser);
                }
                break;
        }
    }

    /**
     * Remove laser tiles after fire. Ignore board lasers.
     */
    public void cleanLasers() {
        for (int x = 0; x < laserLayer.getWidth(); x++)
            for (int y = 0; y < laserLayer.getHeight(); y++) {

                if (grid.isBoardLaser(x, y)) {
                    TiledMapTileLayer.Cell laser = new TiledMapTileLayer.Cell();
                    laser.setTile(tiles.getTile(grid.getBoardLaserId(x, y)));
                    laserLayer.setCell(x, y, laser);
                    continue;
                }
                laserLayer.setCell(x, y, null);
            }
        drawPlayers();
    }

    /**
     * The distance the laser has to be drawn. Returns as soon as it has found a wall or a player.
     * If player is found, deal damage.
     *
     * @param pos
     * @param dir
     * @return distance to target
     */
    public int getTargetDistance(Position pos, Direction dir) {
        int distance = 0;

        while (grid.canFire(pos, dir)) {
            switch (dir) {
                case NORTH:
                    pos = pos.north();
                    break;
                case SOUTH:
                    pos = pos.south();
                    break;
                case EAST:
                    pos = pos.east();
                    break;
                case WEST:
                    pos = pos.west();
                    break;
            }

            if (grid.hasPlayer(pos)) {
//                System.out.println("TRYING TO HIT PLAYER");
                try {
                    getPlayerFromPosition(pos).damagePlayer(1);
//                    System.out.println("PLAYER " + getPlayerFromPosition(pos).getPlayerTile().getColor() + " GOT BLASTED");
                } catch (IllegalArgumentException e) {
//                    System.out.println("THE PLAYER IS DESTROYED");
                }
                return distance;
            }
            distance++;
        }
//        System.out.println("WALL");
        return distance;
    }

    public Player getPlayerFromPosition(Position pos) {
        if (grid.hasPlayer(pos))
            for (Player player : players) {
                if (player.getPosition().equals(pos))
                    return player;
            }
        throw new IllegalArgumentException("Position must have a player");
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

    /**
     * Give out cards to player deck and player selects 5 cards
     *
     * @param player
     */
    public void giveOutCardsToPlayer(Player player) {
        programCardDeck.giveOutCardsToPlayer(player);
    }

    /**
     * Select 5 first cards in playerDeck for all bots (not player 0)
     */
    public void selectCardsForBots() {
        for (Player player : players) {
            if(player.getisAI() && !player.getHandChosen()) {
                player.select5FirstCards();
                player.setHandChosen(true);
            }
        }
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

    public ArrayList<PlayerLayerObject> getPlayerTiles() {
        return playerTiles;
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

    public Phase getCurrentPhase() {
        return currentPhase;
    }
}