package server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerList {
    private final List<Game.Player> players;
    private final int numOfPlayers;
    public PlayerList(int num) {
        players = new ArrayList<>();
        this.numOfPlayers = num;
    }

    public void add(Game.Player player) {
        players.add(player);
    }

    public Iterator<Game.Player> iterator() {
        return new PlayerIterator(numOfPlayers);
    }

/*
    public Game.Player get(int i) {
        return players.get(i);
    }
*/

    public List<Game.Player> getList() {
        return players;
    }

    public class PlayerIterator implements Iterator<Game.Player> {
        private int index;
        private final int numOfPlayers;

        public PlayerIterator(int num) {
            this.index = 0;
            this.numOfPlayers = num;
        }

        @Override
        public boolean hasNext() {
            for (Game.Player player : players) {
                if (player.lives()) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Game.Player next() {
            while (!players.get(index).lives()) {
                index++;
                if (index >= numOfPlayers) {
                    index = 0;
                }
            }
            return players.get(index);
        }
    }
}
