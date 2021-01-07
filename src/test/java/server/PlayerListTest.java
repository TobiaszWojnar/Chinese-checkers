package server;

import org.junit.Test;
import server.chineseCheckers.Game;
import server.chineseCheckers.datastructures.PlayerList;

import java.util.Iterator;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PlayerListTest {

    @Test
    public void playerListTest() {
        PlayerList players;
        Iterator<Game.Player> playerIterator;
        players = new PlayerList(6);
        playerIterator = players.iterator();
        Game.Player player0 = mock(Game.Player.class);
        Game.Player player1 = mock(Game.Player.class);
        Game.Player player2 = mock(Game.Player.class);
        Game.Player player3 = mock(Game.Player.class);
        Game.Player player4 = mock(Game.Player.class);
        Game.Player player5 = mock(Game.Player.class);

        players.add(player0);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);

        for (Game.Player player : players.getList()) {
            when(player.lives()).thenReturn(true);
        }

        for (int i = 0; i < 6; i++) {
            when(players.getList().get(i).toString()).thenReturn("Player" + i);
        }

        Game.Player current = player0;

        for (int i = 0; i < 12; i++) {
            System.out.println(i + ": " + current.toString());
            current = playerIterator.next();
        }

        when(player5.lives()).thenReturn(false);

        for (int i = 0; i < 12; i++) {
            System.out.println(i + ": " + current.toString());
            current = playerIterator.next();
        }

        assertTrue(playerIterator.hasNext());

        for (Game.Player player : players.getList()) {
            when(player.lives()).thenReturn(false);
        }

        assertFalse(playerIterator.hasNext());
    }
}
