package game.server.chineseCheckers.datastructures;

import game.server.chineseCheckers.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class providing a list of players with custom iterator
 */
public class PlayerList {

    private final List<Game.Player> players;
    private final int numOfPlayers;

    /**
     * Construcor
     *
     * @param num number of players
     */
    public PlayerList(int num) {
        players = new ArrayList<>();
        this.numOfPlayers = num;
    }

    /**
     * Adds player to player list
     *
     * @param player player being added
     */
    public void add(Game.Player player) {
        players.add(player);
    }

    /**
     * Custom iterator getter
     *
     * @return custom iterator
     */
    public Iterator<Game.Player> iterator() {
        return new PlayerIterator(numOfPlayers);
    }

/*
    public Game.Player get(int i) {
        return players.get(i);
    }
*/

    /**
     * Player list getter
     *
     * @return player list
     */
    public List<Game.Player> getList() {
        return players;
    }

    /**
     * Inner class. Custom iterator for PlayerList
     */
    public class PlayerIterator implements Iterator<Game.Player> {
        private int index;
        private final int numOfPlayers;

        /**
         * Constructor
         *
         * @param num number of players
         */
        public PlayerIterator(int num) {
            this.index = 0;
            this.numOfPlayers = num;
        }

        /**
         * Checks if there are players who haven't finished the game yet
         *
         * @return True if there are still players in game
         */
        @Override
        public boolean hasNext() {
            for (Game.Player player : players) {
                if (player.lives()) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns next player
         *
         * @return next player
         */
        @Override
        public Game.Player next() {
            index++;
            if (index >= numOfPlayers) {
                index = 0;
            }
            while (!players.get(index).lives()) {
                index++;
                if (index >= numOfPlayers) {
                    index = 0;
                }
            }
            if (index >= numOfPlayers) {
                index = 0;
            }
            return players.get(index);
        }
    }
}