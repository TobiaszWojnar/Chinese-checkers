package server.chineseCheckers;

import board.ChineseCheckersBoard;
import board.Field;
import board.IntPoint;
import server.chineseCheckers.datastructures.CornerMap;
import server.GameAbstract;
import server.chineseCheckers.datastructures.PlayerList;
import server.chineseCheckers.logic.LogicUnitAbstract;
import server.chineseCheckers.logic.LogicUnitAllFilled;
import server.chineseCheckers.logic.LogicUnitAtLeastOneFilled;
import server.chineseCheckers.logic.LogicUnitCanSwap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Game extends GameAbstract {
    private final ChineseCheckersBoard board;
    private int numOfPlayers;
    private Player currentPlayer;
    private LogicUnitAbstract logic;
    private final PlayerList players;
    private final Iterator<Player> playerIterator;
    private boolean started;
    private boolean running;
    private ServerSocket serverSocket;
    private static final int PORT = 8081;

    // for testing only
    /*public Board getBoard() {
        return board;
    }*/

    public Game(int numOfPlayers, int variant) {
        started = false;
        serverSocket = null;
        if (!Arrays.asList(2, 3, 4, 6).contains(numOfPlayers)) {
            throw new IllegalArgumentException("Illegal number of players");
        }
        if (variant < 1 || variant > 3) {
            throw new IllegalArgumentException("Illegal variant");
        }
        this.numOfPlayers = numOfPlayers;
        board = new ChineseCheckersBoard(numOfPlayers);
        CornerMap corners = new CornerMap(numOfPlayers);
        //logic = new LogicUnitAllFilled(board, corners);
        setLogic(variant, corners);
        players = new PlayerList(numOfPlayers);
        playerIterator = players.iterator();
    }

    public void start() {
        if (!started) {
            started = true;

            try {
                serverSocket = new ServerSocket(PORT);
                running = true;

                Thread serverThread = new Thread(this);
                serverThread.start();

                System.out.println("Server started!\n");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    /*
        public void stop() {
            running = false;
            started = false;

            if (serverThread != null)
                serverThread.interrupt();
            serverThread = null;
        }
    */

    @Override
    public void run() {
        int i = 1;
        try {
            while (running) {
                try {
                    if (i <= numOfPlayers) {
                        Socket client = serverSocket.accept();
                        System.out.println("Player" + i + " connected");

                        Player player = new Player("Player" + i, client);

                        players.add(player);
                        //currentPlayer = player;

                        player.protocol.welcome(i, numOfPlayers);

                        if (i == numOfPlayers) {
                            for (int j = 0; j <= new Random().nextInt(numOfPlayers); j++) {
                                //System.out.println("blad");
                                currentPlayer = playerIterator.next();
                            }
                            gameStarted();
                            System.out.println("ALLCONNECTED_GAMESTARTED");
                            System.out.println("STARTING_PLAYER: " + currentPlayer.getPlayer());
                        }

                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLogic(Integer variant, CornerMap corners) {
        switch (variant) {
            case 1:
                this.logic = new LogicUnitAllFilled(board, corners);
                break;
            case 2:
                this.logic = new LogicUnitAtLeastOneFilled(board, corners);
                break;
            case 3:
                this.logic = new LogicUnitCanSwap(board, corners);
                break;
        }
    }

    private void gameStarted() {
        for (Player player : players.getList()) {
            player.protocol.gameStarted();
        }
    }

    private void sendMoveToAll(String play, int x, int y) {
        for (Player player : players.getList()) {
            player.protocol.moveMade(player.getPlayer(), play, x, y);
        }
    }

    private void endTurn() {
        System.out.print(currentPlayer.getPlayer() + " -> ");
        currentPlayer = playerIterator.next();
        System.out.println(currentPlayer.getPlayer());
    }

    private void checkIfWinner() {
        if (logic.isWinner(Field.valueOf(currentPlayer.getPlayer()))) {
            for (Player player : players.getList()) {
                player.protocol.winner(currentPlayer.getPlayer());
            }
            currentPlayer.kill();
            numOfPlayers--;
        }
    }

    private void sendAllBoards() {
        for (Player player : players.getList()) {
            player.sendBoard();
        }
    }

/*
    public static void main(String[] args)
    {
        Game game = new Game(2);
        game.start();
    }
*/

    public class Player implements Runnable {
        private Protocol protocol;
        private final String player;
        private IntPoint chosen;
        private Socket socket;
        private PrintWriter output;
        private BufferedReader input;
        private Thread runningThread;
        private boolean running;

        public Player(String player, Socket socket) {
            this.socket = socket;
            this.player = player;

            try {
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                running = true;
                protocol = new Protocol(output);
                runningThread = new Thread(this);
                runningThread.start();
            } catch (Exception e) {
                e.printStackTrace();
                disconnect();
            }
        }

        @Override
        public void run() {
            try {
                //protocol.allConnected();
                //protocol.gameStarted();
                handleCommands();
                disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                disconnect();
            }
        }

        public boolean lives() {
            return running;
        }

        private void sendBoard() {
            System.out.println(player + ": Sending board to client");
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < 17; j++) {
                    String singleField = board.getField(i, j).toString();
                    sendMessage(singleField);
                }
            }
        }

        private void handleCommands() throws IOException {
            String command;
            while ((command = input.readLine()) != null && running) {
                System.out.println("Message from client: " + command);
                if (command.startsWith("SELECT")) {
                    String[] words = command.split(" ");
                    if (words[1].equals(currentPlayer.getPlayer())) {
                        int x = Integer.parseInt(words[2]);
                        int y = Integer.parseInt(words[3]);
                        chosen = new IntPoint(x, y);
                        logic.highlightPossible(x, y, Field.valueOf(player));
                        protocol.movesHighlighted(x, y);
                        sendBoard();
                    } else {
                        protocol.notYourTurn();
                    }
                } else if (command.startsWith("DESELECT")) {
                    String[] words = command.split(" ");
                    if (words[1].equals(currentPlayer.getPlayer())) {
                        int x = Integer.parseInt(words[2]);
                        int y = Integer.parseInt(words[3]);
                        logic.deselect(x, y,
                                Field.valueOf(currentPlayer.getPlayer()));
                        chosen = null;
                        protocol.deselected(x, y);
                        sendBoard();
                    } else {
                        protocol.notYourTurn();
                    }
                } else if (command.startsWith("MOVE")) {
                    String[] words = command.split(" ");
                    if (words[1].equals(currentPlayer.getPlayer())) {
                        int x = Integer.parseInt(words[2]);
                        int y = Integer.parseInt(words[3]);
                        logic.move(x, y, chosen.getX(), chosen.getY(),
                                Field.valueOf(currentPlayer.getPlayer()));
                        chosen = null;
                        sendMoveToAll(currentPlayer.getPlayer(), x, y);
                        sendAllBoards();
                        checkIfWinner();
                        endTurn();
                    } else {
                        protocol.notYourTurn();
                    }
                } else if (command.startsWith("ENDTURN")) {
                    String[] words = command.split(" ");
                    if (words[1].equals(currentPlayer.getPlayer())) {
                        endTurn();
                    } else {
                        protocol.notYourTurn();
                    }
                }
            }
        }

        private void disconnect() {
            running = false;
            if (runningThread != null)
                runningThread.interrupt();
            runningThread = null;

            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            input = null;

            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            output = null;
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            socket = null;
        }

        private void sendMessage(String message) {
            if (running) {
                output.println(message);
                output.flush();
            }
        }

        private String getPlayer() {
            return player;
        }

        private void kill() {
            running = false;
        }
    }
}