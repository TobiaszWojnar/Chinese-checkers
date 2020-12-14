package server;

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
        out.println("ALLCONNECTED GAMESTARTED");
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

    void moveMade(int x, int y) {
        out.println("MOVEMADE " + x + " " + y);
        out.flush();
        System.out.println("MOVEMADE " + x + " " + y);
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

    void welcome(int i) {
        out.println("WELCOME " + "Player" + i);
        out.flush();
    }
}
