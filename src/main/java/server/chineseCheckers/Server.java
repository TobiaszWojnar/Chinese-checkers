package server.chineseCheckers;

public class Server {

    public static void main(String[] args) {
        if (args.length == 2) {
            int numOfPlayers = Integer.parseInt(args[0]);
            int variant = Integer.parseInt(args[1]);
            // argument correctness handling is done in Game
            System.out.println("Server is running.");
            Game game = new Game(numOfPlayers, variant);
            game.start();
        } else {
            System.err.println("Usage: Server numOfPlayers variant");
        }
    }
}
