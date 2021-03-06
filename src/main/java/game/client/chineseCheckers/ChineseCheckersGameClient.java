package game.client.chineseCheckers;

import game.board.Field;
import game.board.chineseCheckers.ChineseBoardFactory;
import game.board.chineseCheckers.ChineseCheckersBoard;
import game.client.chineseCheckers.GUI.ChineseCheckersGameGUI;
import game.client.chineseCheckers.GUI.GameGuiListener;
import game.client.model.GameClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;


public class ChineseCheckersGameClient extends GameClient {

    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;
    private String player;
    private String currentPlayer;
    private int numOfPlayers;
    private boolean highlighted;
    private boolean initialized;
    private ChineseCheckersBoard board;

    private ChineseCheckersGameGUI gameGUI;

    private class Listener implements GameGuiListener {//TODO eventually change to sending JSON or XML
        @Override
        public void onClicked(int x, int y) {
            if (player.equals(currentPlayer)) {
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

        @Override
        public void onSkipped() {
            if (player.equals(currentPlayer)) {
                System.out.println("Sending SKIP");
                sendMessage("SKIP");
            }
        }


        @Override
        public void onResigned() {
            System.out.println("Sending RESIGN");
            sendMessage("RESIGN " + player);//TODO if you resign and it is your turn you not resigned but GUI title change
        }

        @Override
        public void onClose() {
            System.out.println("Sending CLOSE");
            sendMessage("CLOSE " + player);
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
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
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
                            ChineseBoardFactory factory = new ChineseBoardFactory();
                            board = factory.getBoard(words[2]);
                            board.prepareForPlayers(numOfPlayers);
                            currentPlayer = words[1];
                            gameGUI = new ChineseCheckersGameGUI(numOfPlayers,
                                    player, board, currentPlayer
                            );
                            gameGUI.setListener(new Listener());
                            initialized = true;
                        }
                        break;
                    case "HIGHLIGHTED":
                        receiveBoard();
                        gameGUI.updateBoard(board);
                        highlighted = true;
                        break;
                    case "DESELECTED":
                    case "MOVEMADE":
                        receiveBoard();
                        gameGUI.updateBoard(board);
                        highlighted = false;
                        break;
                    case "TURNENDED":
                        currentPlayer = words[1];
                        gameGUI.setCurrentPlayer(currentPlayer);
                        break;
                    case "WINNER":
                        String winner = words[1];
                        gameGUI.showWinner(winner);
                        break;
                    case "REIGNED":
                        String looser = words[1];
                        gameGUI.showResigned(looser);
                        break;
                    case "MESSAGE":
                        gameGUI.showMessage(String.join(" ", Arrays.copyOfRange(words,1,words.length)));
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

    public static void main(String[] args) {
        ChineseCheckersGameClient client = new ChineseCheckersGameClient();
        new Thread(() -> {
            System.out.println("Player started");
            try {
                client.setConnection("localhost");
                client.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}