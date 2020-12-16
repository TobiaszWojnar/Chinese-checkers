package client.model;

import board.Board;
import board.ChineseCheckersBoard;
import board.Field;
import client.chineseCheckers.GUI.GameGuiListener;
import client.chineseCheckers.GUI.ChineseCheckersGameGUI;
import client.chineseCheckers.GUI.ColorManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Board board;
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;
    private String player;
    private int numOfPlayers;
    private boolean highlighted;
    private boolean initialized;

    private ChineseCheckersGameGUI gameGUI;

    private class Listener implements GameGuiListener {
        @Override
        public void onClicked(int x, int y) {
            if (board.getField(x, y) == Field.valueOf(player)
                    && !highlighted) {
                System.out.println("Sending SELECT");
                sendMessage("SELECT " + player + " " + x + " " + y);
            } else if (board.getField(x, y) == Field.Chosen
                    && highlighted) {
                System.out.println("Sending DESELECT");
                sendMessage("DESELECT " + player + " " + x + " " + y);
            } else if (board.getField(x, y) == Field.Possible
                    && highlighted) {
                System.out.println("Sending MOVE");
                sendMessage("MOVE " + player + " " + x + " " + y);
            }
        }
    }

    public void setConnection(String serverAddress) throws IOException {
        // Setup networking
        socket = new Socket(serverAddress, 8081);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        initialized = false;
    }

    public void receiveBoard() throws Exception {
        System.out.println("Receiving board from server");
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 17; j++) {
                String singleField = input.readLine();
                board.setField(i, j, Field.valueOf(singleField));
            }
        }
    }

    public void play() throws Exception {
        try {
            String response;
            while ((response = input.readLine()) != null) {
                System.out.println("Message from server: " + response);
                String[] words = response.split(" ");
                switch (words[0]) {
                    case "WELCOME":
                        player = words[1];
                        numOfPlayers = Integer.parseInt(words[2]);
                        break;
                    case "ALLCONNECTED_GAMESTARTED":
                        if (!initialized) {
                            ColorManager colorManager = new ColorManager(numOfPlayers);
                            board = new ChineseCheckersBoard(numOfPlayers);
                            gameGUI = new ChineseCheckersGameGUI(numOfPlayers, player, board, colorManager);
                            gameGUI.setListener(new Listener());
                            initialized = true;
                        }
                        break;
                    case "HIGHLIGHTED":
                        //int x = Integer.parseInt(words[1]);
                        //int y = Integer.parseInt(words[2]);
                        receiveBoard();
                        gameGUI.updateBoard((ChineseCheckersBoard) board);
                        highlighted = true;
                        break;
                    case "DESELECTED":
                    case "MOVEMADE":
                        receiveBoard();
                        gameGUI.updateBoard((ChineseCheckersBoard) board);
                        highlighted = false;
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    public void sendMessage(String message) {
        output.println(message);
        output.flush();
    }
}
