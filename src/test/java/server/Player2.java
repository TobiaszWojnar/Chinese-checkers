package server;

import client.model.GameClient;

public class Player2 {

    // how to start a player thread
    public static void main(String[] args) {
        GameClient player = new GameClient();
        new Thread(() -> {
            System.out.println("Player started");
            try {
                player.setConnection("localhost");
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}