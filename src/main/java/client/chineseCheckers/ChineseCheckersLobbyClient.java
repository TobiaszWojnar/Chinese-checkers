package client.chineseCheckers;

import client.chineseCheckers.GUI.ChineseCheckersLobbyGUI;

import javax.swing.*;

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

        javax.swing.SwingUtilities.invokeLater(lobbyGUI::createAndShowGUI);
        lobbyGUI.setListener(new ChineseCheckersLobbyGUI.LobbyGuiListener() {
            @Override
            public void join(String roomId) {
                System.out.println("Join room " + roomId);
                ChineseCheckersGameClient.main(new String[] {});
            }

            @Override
            public void host(String numberOfPlayers, String ruleSet, String boardSize) {
                System.out.println("Host game for " + numberOfPlayers + " players");
                try {
                    if(ruleSet.equals("standard"))
                        ruleSet="1";
                    server.chineseCheckers.Server.main(new String[]{numberOfPlayers, ruleSet, boardSize});
                    System.out.println("Join room ");
                    ChineseCheckersGameClient.main(new String[] {});
                }catch (Exception e){
                    System.out.println("Unable to host new game");
                    JOptionPane.showMessageDialog(lobbyGUI, "Unable to host new game", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void replay(String gameId) {
                System.out.println("Play recorded game number" + gameId);
                //TODO send request to server
            }
        });
    }
}
