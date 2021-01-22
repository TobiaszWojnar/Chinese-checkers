package com.javamaster.model.chineseCheckers;

import com.javamaster.model.Field;
import com.javamaster.model.Player;
import com.javamaster.model.board.IntPoint;
import com.javamaster.model.board.chineseCheckers.ChineseBoardFactory;
import com.javamaster.model.board.chineseCheckers.ChineseCheckersBoard;
import com.javamaster.model.chineseCheckers.datastructures.PlayerList;
import com.javamaster.model.chineseCheckers.logic.LogicUnitAbstract;
import com.javamaster.model.chineseCheckers.logic.LogicUnitAtLeastOneFilled;
import com.javamaster.model.chineseCheckers.logic.LogicUnitCanSwap;
import com.javamaster.model.chineseCheckers.datastructures.CornerMap;
import com.javamaster.model.chineseCheckers.logic.LogicUnitAllFilled;

import java.util.Arrays;

/**
 * Class which realizes a Chinese Checkers game
 */
public class GameInstance {
    private String gameId;
    private final ChineseCheckersBoard board;
    private int numOfPlayers;
    private int currentPlayer;
    private LogicUnitAbstract logic;
    private final PlayerList players;//TODO replace that one with one from Game everywhere
    private IntPoint chosen;
    private final int variant;
    private final String boardType;
    private int moveCount;

    public void incMoveCount() {
        moveCount = moveCount + 1;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public ChineseCheckersBoard getBoard() {
        return board;
    }

    public IntPoint getChosen() {
        return chosen;
    }

    public void setChosen(IntPoint chosen) {
        this.chosen = chosen;
    }

    public LogicUnitAbstract getLogic() {
        return logic;
    }

    public int getVariant() {
        return variant;
    }

    public String getBoardType() {
        return boardType;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     * Constructor which checks if passed arguments are legal and initializes data structures
     *
     * @param numOfPlayers number of players
     * @param ruleset      variant of rules
     * @param boardType    type of board
     */
    public GameInstance(int numOfPlayers, int ruleset, String boardType) {
        if (!Arrays.asList(2, 3, 4, 6).contains(numOfPlayers)) {
            throw new IllegalArgumentException("Illegal number of players");
        }
        if (ruleset < 1 || ruleset > 3) {
            throw new IllegalArgumentException("Illegal variant");
        }
        this.numOfPlayers = numOfPlayers;
        ChineseBoardFactory boardFactory = new ChineseBoardFactory();
        board = boardFactory.getBoard(boardType);
        board.prepareForPlayers(numOfPlayers);
        CornerMap corners = new CornerMap(numOfPlayers);
        setLogic(ruleset, corners);
        players = new PlayerList(numOfPlayers);
        this.variant = ruleset;
        this.boardType = boardType;
        moveCount = 0;
    }

    /**
     * Sets logic unit based on variant
     *
     * @param variant variant of rules
     * @param corners corner map for {@link LogicUnitAbstract Logic unit}
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

/*    private void gameStarted() {
        for (Player player : players.getList()) {
            player.protocol.gameStarted(currentPlayer.getPlayer(), boardType);
        }
    }

    private void sendMoveToAll(String play, int x, int y) {
        for (Player player : players.getList()) {
            player.protocol.moveMade(player.getPlayer(), play, x, y);
        }
    }*/

    public void endTurn() {
        currentPlayer = (currentPlayer + 1) % (numOfPlayers + 1);
    }

    public boolean checkIfWinner() {
        if (logic.isWinner(Field.valueOf("Player" + currentPlayer))) {
            kill(currentPlayer);
            numOfPlayers--;
            return true;
        }
        return false;
    }

    public void kill(int player) {
        players.getMap().get(player).setAlive(false);
    }

/*    private void sendAllBoards() {
        for (Player player : players.getList()) {
            player.sendBoard();
        }
    }

*//*
    public static void main(String[] args)
    {
        Game game = new Game(2);
        game.start();
    }
*//*

    *//**
     * Inner class of player thread
     *//*
    public class PlayerOld implements Runnable {
        private Protocol protocol;
        private final String player;
        private IntPoint chosen;
        private Socket socket;
        private PrintWriter output;
        private BufferedReader input;
        private Thread runningThread;
        private boolean running;

        *//**
         * Constructor which sets socket and communication buffers with client
         *
         * @param player name of player
         * @param socket socket of client
         *//*
        public PlayerOld(String player, Socket socket) {
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

        *//**
         * Runs player thread
         *//*
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

        *//**
         * Check if player is still playing
         *
         * @return false if player finished the game true otherwise
         *//*
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
    }*/
}