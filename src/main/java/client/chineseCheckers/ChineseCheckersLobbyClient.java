package client.chineseCheckers;

import client.chineseCheckers.GUI.ChineseCheckersLobbyGUI;

public class ChineseCheckersLobbyClient {
    public static void main(String[] args) {
        ChineseCheckersLobbyGUI lobbyGUI = new ChineseCheckersLobbyGUI();

        javax.swing.SwingUtilities.invokeLater(() -> new ChineseCheckersLobbyGUI().LobbyWindow());
        lobbyGUI.setListener(new ChineseCheckersLobbyGUI.LobbyGuiListener() {
            @Override
            public void join(String roomId) {
                System.out.println("Join room " + roomId);
                //TODO send request to server
            }

            @Override
            public void host(int chosenNumberOfPlayers) {
                System.out.println("Host game for " + chosenNumberOfPlayers + " players");
                //TODO send request to server
            }
        });
    }
}
