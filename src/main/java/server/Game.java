package server;

import board.ChineseCheckersBoard;
import board.Field;
import board.IntPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Game {
    private final ChineseCheckersBoard board;
    private final CornerMap corners;
    private int numOfPlayers;
    private Player currentPlayer;
    private final LogicUnit logic;
    private final PlayerList players;
    Iterator<Player> playerIterator;
    private final ServerSocket server;

    public Game(int numOfPlayers, ServerSocket server) throws IOException {
        if (!Arrays.asList(2, 3, 4, 6).contains(numOfPlayers)) {
            throw new IllegalArgumentException("Illegal number of players");
        }
        this.numOfPlayers = numOfPlayers;
        this.server = server;
        board = new ChineseCheckersBoard(numOfPlayers);
        corners = new CornerMap(numOfPlayers);
        logic = new LogicUnit(board, corners);
        players = new PlayerList(numOfPlayers);
        playerIterator = players.iterator();
        addPlayers();
        currentPlayer = players.get(0);
        startPlayerThreads();
    }

    private void addPlayers() throws IOException {
        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.println("Player" + i + " connected");
            players.add(new Player("Player" + i, server.accept()));
        }
    }

    private void startPlayerThreads() {
        for (Player player : players.getList()) {
            player.start();
        }
    }

    private void endTurn() {
        currentPlayer = playerIterator.next();
    }

    private void checkIfWinner() {
        if (logic.isWinner(Field.valueOf(currentPlayer.getPlayer()))) {
            for (Player player : players.getList()) {
                player.protocol.winner(currentPlayer.getPlayer());
            }
        }
        currentPlayer.kill();
        numOfPlayers--;
    }

    class Player extends Thread implements Runnable {
        BufferedReader input;
        PrintWriter output;
        private final Socket socket;
        String player;
        boolean alive;
        Protocol protocol;
        IntPoint chosen;

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

        private String getPlayer() {
            return player;
        }

        private void kill() {
            alive = false;
        }

        public boolean lives() {
            return alive;
        }

        void handleCommand(String command) {
            System.out.println("Message from client: " + command);
            if (command.startsWith("SELECT")) {
                String[] words = command.split(" ");
                if (words[1].equals(currentPlayer.getPlayer())) {
                    int x = Integer.parseInt(words[2]);
                    int y = Integer.parseInt(words[3]);
                    chosen = new IntPoint(x, y);
                    logic.highlightPossible(x, y, Field.valueOf(player));
                    protocol.movesHighlighted(x, y);
                    // TODO send serialized board to client after every command
                }
            }
            if (command.startsWith("DESELECT")) {
                String[] words = command.split(" ");
                if (words[1].equals(currentPlayer.getPlayer())) {
                    int x = Integer.parseInt(words[2]);
                    int y = Integer.parseInt(words[3]);
                    logic.deselect(x, y, Field.valueOf(currentPlayer.getPlayer()));
                    protocol.deselected(x, y);
                }
            }
            if (command.startsWith("MOVE")) {
                String[] words = command.split(" ");
                if (words[1].equals(currentPlayer.getPlayer())) {
                    int x = Integer.parseInt(words[2]);
                    int y = Integer.parseInt(words[3]);
                    logic.move(x, y, chosen.getX(), chosen.getY(), Field.valueOf(currentPlayer.getPlayer()));
                    chosen = null;
                    protocol.moveMade(x, y);
                    checkIfWinner();
                }
            }
            if (command.startsWith("ENDTURN")) {
                String[] words = command.split(" ");
                if (words[1].equals(currentPlayer.getPlayer())) {
                    endTurn();
                }
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
                    System.out.println("Player error: " + ex);
                } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
