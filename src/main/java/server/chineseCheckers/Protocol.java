package server.chineseCheckers;

import java.io.PrintWriter;

public class Protocol {
    private final PrintWriter out;

    Protocol(PrintWriter out) {
        this.out = out;
    }

/*
    void allConnected() {
        out.println("ALLCONNECTED");
        out.flush();
    }
*/

    void gameStarted() {
        out.println("ALLCONNECTED_GAMESTARTED");
        out.flush();
    }

    void movesHighlighted(int x, int y) {
        out.println("HIGHLIGHTED " + x + " " + y);
        out.flush();
        System.out.println("HIGHLIGHTED " + x + " " + y);
    }

    void deselected(int x, int y) {
        out.println("DESELECTED " + x + " " + y);
        out.flush();
        System.out.println("DESELECTED " + x + " " + y);
    }

    void moveMade(String me, String player, int x, int y) {
        out.println("MOVEMADE " + player + " " + x + " " + y);
        out.flush();
        System.out.println(me + ": MOVEMADE " + player + " " + x + " " + y);
    }

    void winner(String player) {
        out.println("WINNER: " + player);
        out.flush();
        System.out.println("WINNER: " + player);
    }

    void notYourTurn() {
        out.println("NOT YOUR TURN");
        out.flush();
    }

    void welcome(int i, int numOfPlayers) {
        out.println("WELCOME " + "Player" + i + " " + numOfPlayers);
        out.flush();
    }

    void turnEnded(String currentPlayer) {
        out.println("TURNENDED " + currentPlayer);
        out.flush();
    }
}
