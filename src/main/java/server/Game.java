package server;

import board.Board;
import board.ChineseCheckersBoard;
import board.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final ChineseCheckersBoard board;
    private int numOfPlayers;
    private Player currentPlayer;
    private final LogicUnit logic;
    private int moveCounter;
    private List<Player> players;
    private final ServerSocket server;

    public Game(int numOfPlayers, ServerSocket server) throws IOException {
        if (!Arrays.asList(2, 3, 4, 6).contains(numOfPlayers)) {
            throw new IllegalArgumentException("Illegal number of players");
        }
        this.numOfPlayers = numOfPlayers;
        this.server = server;
        board = new ChineseCheckersBoard(numOfPlayers);
        logic = new LogicUnit(board);

    }

    class Player implements Runnable {
        BufferedReader input;
        PrintWriter output;
        private final Socket socket;
        String player;
        boolean alive;
        Protocol protocol;

        Player(String player, Socket socket) {
            this.player = player;
            this.socket = socket;
            alive = true;

            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // PrintWriter with automatic line flushing
                output = new PrintWriter(socket.getOutputStream(), true);

                protocol = new Protocol(this.output);

                output.println("");
                output.println("WELCOME " + player);
                output.println("Waiting for your opponents to connect...");
            } catch (IOException ex) {
                System.out.println("Player error: " + ex);
            }
        }

        void handleCommand(String command) {
            System.out.println("Message from client: " + command);
            if (command.startsWith("SELECT")) {
                String[] words = command.split(" ");
                if (words[1].equals(this.player)) {
                    int x = Integer.parseInt(words[2]);
                    int y = Integer.parseInt(words[3]);
                    if (board.getField(x, y) == Field.valueOf(player)) {
                        board.setField(x, y, Field.Chosen);
                        logic.highlightPossible(x, y);
                        this.protocol.movesHighlighted(x, y);
                        // TODO send serialized board to client
                    }
                }
            }
            if (command.startsWith("DESELECT")) {
                logic.deselect(Field.valueOf(this.player));
            }

        }

        @Override
        public void run() {
            try {
                System.out.println("All players connected");
                protocol.allConnected();
                protocol.gameStarted();
                while (alive) {
                    String command = input.readLine();
                    if (command != null) {
                        handleCommand(command);
                    }
                }
                } catch (IOException ex) {
                    System.out.println("server.Game.Player error: " + ex);
                } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


}
