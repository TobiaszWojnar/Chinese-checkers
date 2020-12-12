package server;

import java.io.PrintWriter;

public class Protocol {
    private final PrintWriter out;

    Protocol(PrintWriter out) {
        this.out = out;
    }

    void allConnected() {
        out.println("ALLCONNECTED");
    }

    void gameStarted() {
        out.println("GAMESTARTED");
    }

    void movesHighlighted(int x, int y) {
        out.println("HIGHLIGHTED FOR " + x + " " + y);
    }

    void moveMade() {
        out.println("MOVEMADE");
    }


}
