package game.server.chineseCheckers;

public class Server {

    public static void main(String[] args) {
        if (args.length == 3) {
            int numOfPlayers = Integer.parseInt(args[0]);
            int variant = Integer.parseInt(args[1]);
            String boardType = args[2];
            // argument correctness handling is done in Game
            System.out.println("Server is running.");
            Game game = new Game(numOfPlayers, variant, boardType);
            game.start();
        } else {
            System.err.println("Usage: Server numOfPlayers variant boardType");
        }
    }
}
