package com.javamaster.model.chineseCheckers.datastructures;


import com.javamaster.model.Player;

import java.util.*;

/**
 * Class providing a list of players with custom iterator
 */
public class PlayerList {

    private final Map<Integer, Player> players;
    private final int numOfPlayers;
    private int index;

    /**
     * Constructor
     *
     * @param num number of players
     */
    public PlayerList(int num) {
        players = new HashMap<>();
        this.numOfPlayers = num;
        index = 1;
    }

    /**
     * Adds player to player list
     *
     * @param player player being added
     */
    public void add(Player player) {
        if (index <= numOfPlayers) {
            players.put(index, player);
            index++;
        }
    }

    public boolean last() {
        int i = 0;
        for (Player player : players.values()) {
            if (player.isAlive()) {
                i++;
            }
        }
        return i == 1;
    }

    public boolean full() {
        return index == numOfPlayers + 1;
    }

    /**
     * Custom iterator getter
     *
     * @return custom iterator
     */
    /*public Iterator<Player> iterator() {
        return new PlayerIterator(numOfPlayers);
    }*/

/*
    public Player get(int i) {
        return players.get(i);
    }
*/

    /**
     * Player list getter
     *
     * @return player list
     */
    public Map<Integer, Player> getMap() {
        return players;
    }

    /**
     * Inner class. Custom iterator for PlayerList
     *//*
    public class PlayerIterator implements Iterator<Player> {
        private int index;
        private final int numOfPlayers;

        *//**
         * Constructor
         *
         * @param num number of players
         *//*
        public PlayerIterator(int num) {
            this.index = 0;
            this.numOfPlayers = num;
        }

        *//**
         * Checks if there are players who haven't finished the game yet
         *
         * @return True if there are still players in game
         *//*
        @Override
        public boolean hasNext() {
            for (int i = 1; i <= numOfPlayers; i++) {
                if (players.get(i).isAlive()) {
                    return true;
                }
            }
            return false;
        }

        *//**
         * Returns next player
         *
         * @return next player
         *//*
        @Override
        public Player next() {
            index++;
            if (index >= numOfPlayers) {
                index = 0;
            }
            while (!players.get(index).isAlive()) {
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
    }*/
}