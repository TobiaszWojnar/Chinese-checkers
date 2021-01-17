package client.chineseCheckers;

import client.chineseCheckers.GUI.ChineseCheckersLobbyGUI;

/**
 * Lobby client
 */
public class ChineseCheckersLobbyClient {
    /**
     * Main method creates GUI
     *
     * @param args unused
     */
    public static void main(String[] args) {
        ChineseCheckersLobbyGUI lobbyGUI = new ChineseCheckersLobbyGUI();

        javax.swing.SwingUtilities.invokeLater(lobbyGUI::LobbyWindow);
        lobbyGUI.setListener(new ChineseCheckersLobbyGUI.LobbyGuiListener() {
            @Override
            public void join(String roomId) {
                System.out.println("Join room " + roomId);//TODO send request to server
                ChineseCheckersGameClient.main(new String[] {});
            }

            @Override
            public void host(String numberOfPlayers, String ruleSet, String boardSize) {
                System.out.println("Host game for " + numberOfPlayers + " players"); //TODO send request to server
                server.chineseCheckers.Server.main(new String[] {numberOfPlayers, ruleSet, boardSize});
            }

            @Override
            public void playRecorded(String gameId) {
                System.out.println("Play recorded game number" + gameId);
                //TODO send request to server
            }
        });
    }
}
