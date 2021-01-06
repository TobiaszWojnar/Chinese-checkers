package server;

import client.chineseCheckers.ChineseCheckersGameClient;
import org.junit.Test;
import server.chineseCheckers.Game;
import server.chineseCheckers.Server;

public class ServerTest {

    @Test
    public void serverTest() {
        Server.main(new String[] {"2", "1", "normal"});
        /*ChineseCheckersGameClient player = new ChineseCheckersGameClient();
        new Thread(() -> {
            System.out.println("Player started");
            try {
                player.setConnection("localhost");
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        ChineseCheckersGameClient player2 = new ChineseCheckersGameClient();
        new Thread(() -> {
            System.out.println("Player started");
            try {
                player2.setConnection("localhost");
                player2.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
