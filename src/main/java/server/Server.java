package server;

public class Server {

    public static void main(String[] args) {
        if (args[0] != null) {
            int numOfPlayers = Integer.parseInt(args[0]);
            if (numOfPlayers < 2 || numOfPlayers > 6 || numOfPlayers == 5) {
                throw new IllegalArgumentException("Illegal number of players.");
            }
            System.out.println("Server is running.");
            Game game = new Game(numOfPlayers);
            game.start();
        } else {
            System.err.println("Usage: Server numOfPlayers");
        }
    }
}
