package server.chineseCheckers;

import java.io.PrintWriter;

public class Protocol {
    private final PrintWriter out;

    /**
     * Constructor
     *
     * @param out PrintWriter to write to
     */
    public Protocol(PrintWriter out) {
        this.out = out;
    }

/*
    void allConnected() {
        out.println("ALLCONNECTED");
        out.flush();
    }
*/

    /**
     * Informs client about game being started
     *
     * @param currentplayer Player who has the first move
     * @param boardType     Board type (small, normal, big)
     */
    public void gameStarted(String currentplayer, String boardType) {
        out.println("ALLCONNECTED_GAMESTARTED " + currentplayer + " " + boardType);
        out.flush();
    }

    /**
     * Informs client about highlighting possible moves for a pawn
     *
     * @param x x cooridnate of the pawn
     * @param y y coordinate of the pawn
     */
    public void movesHighlighted(int x, int y) {
        out.println("HIGHLIGHTED " + x + " " + y);
        out.flush();
        System.out.println("HIGHLIGHTED " + x + " " + y);
    }

    /**
     * Informs client about pawn being deselected
     *
     * @param x x cooridnate of the pawn
     * @param y y coordinate of the pawn
     */
    public void deselected(int x, int y) {
        out.println("DESELECTED " + x + " " + y);
        out.flush();
        System.out.println("DESELECTED " + x + " " + y);
    }

    /**
     * Informs client about move being made
     *
     * @param me     client who is being informed
     * @param player player who made the move
     * @param x      x coordinate of the pawn
     * @param y      y coordinate of the pawn
     */
    public void moveMade(String me, String player, int x, int y) {
        out.println("MOVEMADE " + player + " " + x + " " + y);
        out.flush();
        System.out.println(me + ": MOVEMADE " + player + " " + x + " " + y);
    }

    /**
     * Informs client about a winner
     *
     * @param player winner
     */
    public void winner(String player) {
        out.println("WINNER: " + player);
        out.flush();
        System.out.println("WINNER: " + player);
    }

    /**
     * Informs client that it's their his turn
     */
    public void notYourTurn() {
        out.println("NOT YOUR TURN");
        out.flush();
    }

    /**
     * Sends welcome message to client on connection
     *
     * @param i            number of player
     * @param numOfPlayers number of players in game
     */
    public void welcome(int i, int numOfPlayers) {
        out.println("WELCOME " + "Player" + i + " " + numOfPlayers);
        out.flush();
    }

    /**
     * Informs client about the end of a turn
     *
     * @param currentPlayer player whose turn ended
     */
    public void turnEnded(String currentPlayer) {
        out.println("TURNENDED " + currentPlayer);
        out.flush();
    }
}
