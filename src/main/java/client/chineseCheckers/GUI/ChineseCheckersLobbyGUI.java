package client.chineseCheckers.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class ChineseCheckersLobbyGUI implements ActionListener {

    private final JTextField roomIdTextField = new JTextField("Room Id");

    private final String[] numberOfPlayers = new String[]{"2","3","4","6"};
    private final JComboBox<String> numberOfPlayersList = new JComboBox<>(numberOfPlayers);


    //TODO communicate with server has list of games
    //TODO creates games
    public void MenuWindow(){//TODO make look nice
        JFrame frame = new JFrame("Chinese Checkers Menu Lobby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(400, 150));
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel l = new JLabel("To host a game choose number of players and click 'host'", JLabel.CENTER);
        panel.add(l);

        l = new JLabel("To join a game enter room Id and click 'join'", JLabel.CENTER);
        panel.add(l);
        //TODO add eventually password

        panel.add(numberOfPlayersList);

        l.setLabelFor(roomIdTextField);
        panel.add(roomIdTextField);

        JButton bHost = new JButton("Host");
        bHost.setActionCommand("host");
        panel.add(bHost);
        bHost.addActionListener(this);

        JButton bJoin = new JButton("Join");
        bJoin.setActionCommand("join");
        panel.add(bJoin);
        bJoin.addActionListener(this);

        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if ("host".equals(actionEvent.getActionCommand())) {

            int chosenNumberOfPlayers = Integer.parseInt(
                    (String) Objects.requireNonNull(numberOfPlayersList.getSelectedItem()));
            System.out.println("host for "+ chosenNumberOfPlayers);
            //TODO send request through client
        } else if("join".equals(actionEvent.getActionCommand())){
            String chosenRoomId = roomIdTextField.getText();
            System.out.println("joining room "+chosenRoomId);
            //TODO send request through client
        }
    }
}
