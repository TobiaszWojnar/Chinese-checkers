package client.chineseCheckers.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class ChineseCheckersLobbyGUI implements ActionListener {

    private final JTextField roomIdTextField = new JTextField("Room Id");

    private final String[] numberOfPlayers = new String[]{"2", "3", "4", "6"};
    private final JComboBox<String> numberOfPlayersList = new JComboBox<>(numberOfPlayers);

    private LobbyGuiListener listener;


    public void LobbyWindow() {
        JFrame frame = new JFrame("Chinese Checkers Menu Lobby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(400, 150));
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel l = new JLabel("To host a game choose number of players and click 'host'", JLabel.CENTER);
        panel.add(l);

        l = new JLabel("To join a game enter room Id and click 'join'", JLabel.CENTER);
        panel.add(l);

        panel.add(numberOfPlayersList);

        l.setLabelFor(roomIdTextField);
        panel.add(roomIdTextField);

        JButton bHost = new JButton("Host");
        //bHost.setActionCommand("host");
        panel.add(bHost);
        bHost.addActionListener(e -> {
            int chosenNumberOfPlayers = Integer.parseInt(
                    (String) Objects.requireNonNull(numberOfPlayersList.getSelectedItem()));
            listener.host(chosenNumberOfPlayers);
        });

        JButton bJoin = new JButton("Join");
        panel.add(bJoin);
        bJoin.addActionListener(e -> {
            String chosenRoomId = roomIdTextField.getText();
            System.out.println("joining room " + chosenRoomId);
            listener.join(chosenRoomId);
        });
        /*
            bJoin.setActionCommand("join");
            bJoin.addActionListener(this);
        */
        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        /*
        if ("host".equals(actionEvent.getActionCommand())) {
            int chosenNumberOfPlayers = Integer.parseInt(
                    (String) Objects.requireNonNull(numberOfPlayersList.getSelectedItem()));
            System.out.println("host for "+ chosenNumberOfPlayers);
            //TODO send request through client
        } else

            if("join".equals(actionEvent.getActionCommand())){
            String chosenRoomId = roomIdTextField.getText();
            System.out.println("joining room "+chosenRoomId);
            listener.join(chosenRoomId);
            //TODO send request through client
        }
        */
    }

    public void setListener(LobbyGuiListener listener) {
        this.listener = listener;
    }

    public interface LobbyGuiListener {
        void join(String roomId);

        void host(int chosenNumberOfPlayers);
    }
}
