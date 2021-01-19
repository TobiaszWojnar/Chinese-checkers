package server.chineseCheckers;

import board.chineseCheckers.ChineseBoardFactory;
import board.chineseCheckers.ChineseCheckersBoard;
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

/**
 * Class which realizes a Chinese Checkers game
 */
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
    private final String boardType;

    // for testing only
    /*
    public Board getBoard() {
        return board;
    }
    */

    /**
     * Constructor which checks if passed arguments are legal and initializes data structures
     *
     * @param numOfPlayers number of players
     * @param variant      variant of rules
     * @param boardType    type of board
     */
    public Game(int numOfPlayers, int variant, String boardType) {
        started = false;
        serverSocket = null;
        if (!Arrays.asList(2, 3, 4, 6).contains(numOfPlayers)) {
            throw new IllegalArgumentException("Illegal number of players");
        }
        if (variant < 1 || variant > 3) {
            throw new IllegalArgumentException("Illegal variant");
        }
        this.numOfPlayers = numOfPlayers;
        this.boardType = boardType;
        //board = new ChineseCheckersBoard(numOfPlayers);
        ChineseBoardFactory boardFactory = new ChineseBoardFactory();
        board = boardFactory.getBoard(boardType);
        board.prepareForPlayers(numOfPlayers);
        CornerMap corners = new CornerMap(numOfPlayers);
        //logic = new LogicUnitAllFilled(board, corners);
        setLogic(variant, corners);
        players = new PlayerList(numOfPlayers);
        playerIterator = players.iterator();
    }

    /**
     * Creates socket of server and starts its thread
     */
    @Override
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
                //System.exit(0);//TODO zmieniłem na razieS
                //Wywala java.net.BindException: Address already in use
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

    /**
     * Waits for required number of players to connect, adds them to player list, sends welcome messages
     * and sets current player to random
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
                            System.out.println("ALLCONNECTED_GAMESTARTED " + currentPlayer.getPlayer());
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

    /**
     * Sets logic unit based on variant
     *
     * @param variant variant of rules
     * @param corners corner map for {@link server.chineseCheckers.logic.LogicUnitAbstract Logic unit}
     */
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
            player.protocol.gameStarted(currentPlayer.getPlayer(), boardType);
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
        for (Player player : players.getList()) {
            player.protocol.turnEnded(currentPlayer.getPlayer());
        }
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

    /**
     * Inner class of player thread
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

        /**
         * Constructor which sets socket and communication buffers with client
         *
         * @param player name of player
         * @param socket socket of client
         */
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

        /**
         * Runs player thread
         */
        @Override
        public void run() {
            try {
                handleCommands();
                disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                disconnect();
            }
        }

        /**
         * Check if player is still playing
         *
         * @return false if player finished the game true otherwise
         */
        public boolean lives() {
            return running;
        }

        private void sendBoard() {
            System.out.println(player + ": Sending board to client");
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    String singleField = board.getField(i, j).toString();
                    sendMessage(singleField);
                }
            }
        }

        private void handleCommands() throws IOException {
            String command;
            while ((command = input.readLine()) != null) {
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
                } else if (command.startsWith("SKIP")) {
                    if (chosen != null) {
                        logic.deselect(chosen.getX(), chosen.getY(),
                                Field.valueOf(currentPlayer.getPlayer()));
                        protocol.deselected(chosen.getX(), chosen.getY());
                        chosen = null;
                        sendBoard();
                    }
                    endTurn();
                } else if (command.startsWith("RESIGN")) {
                    String[] words = command.split(" ");
                    if (words[1].equals(currentPlayer.getPlayer())) {
                        for (Player player : players.getList()) {
                            player.protocol.resigned(currentPlayer.getPlayer());
                        }
                        kill();
                        endTurn();
                    }
                } else if (command.startsWith("CLOSE")) {
                    kill();
                    if (player.equals(currentPlayer.getPlayer())) {
                        endTurn();
                    }
                }
            }
            System.out.println("I'm out " + player);
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
            if (output != null) {
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