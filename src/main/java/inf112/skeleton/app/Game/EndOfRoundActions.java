package inf112.skeleton.app.Game;

import inf112.skeleton.app.Player.Player;

import java.util.List;

/**
 * All actions for Players at the end of a phase
 */
public class EndOfRoundActions {


    private List<Player> players;

    public EndOfRoundActions(GameMap gameMap) {
        this.players = gameMap.getPlayers();
    }

    /**
     * Performs all end of round checks in rule order
     */
    public void performAllChecks() {
        fixPlayers();
        activatePlayers();
    }

    public void fixPlayers() {
        for (Player player : players) {
            if (player.isDestroyed())
                player.fix();
        }
    }

    /**
     * All powered down players are set to active
     */
    public void activatePlayers() {
        for (Player player : players) {
            player.activate();
        }
    }
}
