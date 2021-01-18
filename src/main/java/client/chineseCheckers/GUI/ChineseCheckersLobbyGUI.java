package client.chineseCheckers.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Lobby for creating games. In current version unused.
 */
public class ChineseCheckersLobbyGUI extends JFrame {

    private final JTextField roomIdTextField = new JTextField("Room Id");

    private final String[] nrOfPlayers = new String[]{"2", "3", "4", "6"};
    private final JComboBox<String> nrOfPlayersBox = new JComboBox<>(nrOfPlayers);

    private final String[] ruleSet = new String[]{"standard"};
    private final JComboBox<String> ruleSetBox = new JComboBox<>(ruleSet);

    private final String[] boardSize = new String[]{"small","normal","big"};
    private final JComboBox<String> boardSizeBox = new JComboBox<>(boardSize);

    private LobbyGuiListener listener;

    /**
     * GUI
     */
    public void LobbyWindow() {
        //TODO implement record
        //TODO when start or host close window
        //TODO when one widow close all close; make new game new thread
        JFrame frame = new JFrame("Chinese Checkers Menu Lobby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(400, 150));
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel l = new JLabel("To host a game choose number of players and click 'host'", JLabel.CENTER);
        panel.add(l);

        l = new JLabel("To join a game enter room Id and click 'join'", JLabel.CENTER);
        panel.add(l);

        panel.add(nrOfPlayersBox);

        l.setLabelFor(roomIdTextField);
        panel.add(roomIdTextField);

        JButton bHost = new JButton("Host");
        panel.add(bHost);
        bHost.addActionListener(e -> {
            String chosenNumberOfPlayers =
                    (String) Objects.requireNonNull(nrOfPlayersBox.getSelectedItem());
            listener.host(chosenNumberOfPlayers, "1", "small");//TODO
        });

        JButton bJoin = new JButton("Join");
        panel.add(bJoin);
        bJoin.addActionListener(e -> {
            String chosenRoomId = roomIdTextField.getText();
            System.out.println("joining room " + chosenRoomId);
            listener.join(chosenRoomId);
        });

        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Setter enabling Lobby client to get updates.
     * @param listener enabling Lobby client to get updates.
     */
    public void setListener(LobbyGuiListener listener) {
        this.listener = listener;
    }

    /**
     * TODO
     */
    public interface LobbyGuiListener {
        void join(String roomId);
        void host(String numberOfPlayers, String ruleSet, String boardSize);
        void playRecorded(String roomId);
    }
}
