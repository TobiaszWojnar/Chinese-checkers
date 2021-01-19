package client.chineseCheckers.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Lobby for creating games. In current version unused.
 */
public class ChineseCheckersLobbyGUI extends JFrame {

    private final JTextField joinGameIdField;

    private final JTextField replayGameIdField;

    private final JComboBox<String> nrOfPlayersBox;

    private final String[] ruleSet = new String[]{"standard"};
    private final JComboBox<String> ruleSetBox = new JComboBox<>(ruleSet);

    private final String[] boardSize = new String[]{"small","normal","big"};
    private final JComboBox<String> boardSizeBox = new JComboBox<>(boardSize);

    private LobbyGuiListener listener;

    public ChineseCheckersLobbyGUI(){
        joinGameIdField = new JTextField("Game Id");
        replayGameIdField = new JTextField("Game Id");
        String[] nrOfPlayers = new String[]{"2", "3", "4", "6"};
        nrOfPlayersBox = new JComboBox<>(nrOfPlayers);
    }

    /**
     * GUI
     */
    public void createAndShowGUI(){
        setTitle("Chinese Checkers Lobby");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel pane = new JPanel(new GridLayout(3, 2));
        add(pane);

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
            String chosenRuleSet = (String) Objects.requireNonNull(ruleSetBox.getSelectedItem());
            String chosenNrOfPlayers =
                    (String) Objects.requireNonNull(nrOfPlayersBox.getSelectedItem());
            String chosenBoardSize = (String) Objects.requireNonNull(boardSizeBox.getSelectedItem());
            listener.host(chosenNrOfPlayers, chosenRuleSet, chosenBoardSize);//TODO
            System.out.println("listener.host("+chosenNrOfPlayers+",\"1\",boardSizeBox);");
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
            listener.join(chosenRoomId);
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
            listener.replay(chosenRoomId);
        });

        pack();
        setVisible(true);
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
        void replay(String roomId);
    }
}
