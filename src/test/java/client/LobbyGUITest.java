package client;

import javax.swing.*;
import javax.swing.JComponent;
import java.awt.*;
import java.util.Objects;

/**
 * Lobby for creating games. In current version unused.
 */
public class LobbyGUITest extends JFrame {

    private final JTextField joinGameIdField = new JTextField("Game Id");
    private final JTextField replayGameIdField = new JTextField("Game Id");

    private final String[] nrOfPlayers = new String[]{"2", "3", "4", "6"};
    private final JComboBox<String> nrOfPlayersBox = new JComboBox<>(nrOfPlayers);

    private final String[] ruleSet = new String[]{"standard"};
    private final JComboBox<String> ruleSetBox = new JComboBox<>(ruleSet);

    private final String[] boardSize = new String[]{"small","normal","big"};
    private final JComboBox<String> boardSizeBox = new JComboBox<>(boardSize);

    //private LobbyGuiListener listener;

    /**
     * GUI
     */
    public void LobbyWindow() {//TODO https://stackoverflow.com/questions/43007625/align-jbuttons-in-center-of-nested-jpanel-boxlayout
        JFrame frame = new JFrame("Chinese Checkers Lobby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        JPanel pane = new JPanel(new GridLayout(3, 2));
        frame.add(pane);

//        JPanel paneL = new JPanel(new GridLayout(3, 1));
//        JPanel paneR = new JPanel(new GridLayout(3, 1));
//        pane.add(paneL);
//        pane.add(paneR);

        JPanel wrapperRule = new JPanel(new GridLayout(2, 1));
        wrapperRule.add(new JLabel("Rule set"));
        wrapperRule.add(ruleSetBox);

        JPanel wrapperNumber = new JPanel(new GridLayout(2, 1));
        wrapperNumber.add(new JLabel("Nr of players"));
        wrapperNumber.add(nrOfPlayersBox);

        JPanel wrapperSize = new JPanel(new GridLayout(2, 1));
        wrapperSize.add(new JLabel("Board size"));
        wrapperSize.add(boardSizeBox);

        JPanel wrapper = new JPanel();
        wrapper.add(wrapperRule);
        wrapper.add(wrapperNumber);
        wrapper.add(wrapperSize);

        pane.add(wrapper);

        JButton bHost = new JButton("Host");
        JPanel bHostWrapper = new JPanel();
        bHostWrapper.add(bHost);
        pane.add(bHostWrapper);
        bHost.addActionListener(e -> {
            String chosenNumberOfPlayers =
                    (String) Objects.requireNonNull(nrOfPlayersBox.getSelectedItem());
           // listener.host(chosenNumberOfPlayers, "1", "small");//TODO
            System.out.println("listener.host("+chosenNumberOfPlayers+",\"1\",boardSizeBox);");
        });


        JPanel joinWrapper = new JPanel();
        joinGameIdField.setColumns(15);
        joinWrapper.add(joinGameIdField);
        pane.add(joinWrapper);

        JButton bJoin = new JButton("Join");
        JPanel bJoinWrapper = new JPanel();
        bJoinWrapper.add(bJoin);
        pane.add(bJoinWrapper);
        bJoin.addActionListener(e -> {
            String chosenRoomId = joinGameIdField.getText();
            System.out.println("joining room " + chosenRoomId);
            //listener.join(chosenRoomId);
        });

        JPanel replayWrapper = new JPanel();
        replayGameIdField.setColumns(15);
        replayWrapper.add(replayGameIdField);
        pane.add(replayWrapper);
        JButton bReplay = new JButton("Replay");
        JPanel bReplayWrapper = new JPanel();
        bReplayWrapper.add(bReplay);
        pane.add(bReplayWrapper);
        bReplay.addActionListener(e -> {
            String chosenRoomId = replayGameIdField.getText();
            System.out.println("replay room " + chosenRoomId);
            //listener.replay(chosenRoomId);
        });



//        JPanel panelLeft = new JPanel(//new GridLayout(3, 2)
//                );
//        JPanel panelRight = new JPanel();
//        frame.add(panelLeft);
//        frame.add(panelRight);

//
//
//        JLabel l = new JLabel("To host a game choose number of players and click 'host'", JLabel.CENTER);
//        panel.add(l);
//
//        l = new JLabel("To join a game enter room Id and click 'join'", JLabel.CENTER);
//        panel.add(l);
//
//        panel.add(nrOfPlayersBox);
//
//        l.setLabelFor(roomIdTextField);
//        panel.add(roomIdTextField);
//
//        JButton bHost = new JButton("Host");
//        panel.add(bHost);
//        bHost.addActionListener(e -> {
//            String chosenNumberOfPlayers =
//                    (String) Objects.requireNonNull(nrOfPlayersBox.getSelectedItem());
//            System.out.println("listener.host(chosenNumberOfPlayers, \"1\", \"small\");//TODO");
//        });
//
//        JButton bJoin = new JButton("Join");
//        panel.add(bJoin);
//        bJoin.addActionListener(e -> {
//            String chosenRoomId = roomIdTextField.getText();
//            System.out.println("joining room " + chosenRoomId);
//            System.out.println("listener.join(chosenRoomId);");
//        });
//

        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new LobbyGUITest().LobbyWindow();
    }


}
