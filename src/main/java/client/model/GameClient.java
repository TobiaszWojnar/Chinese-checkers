package client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {//TODO make abstaract class and move implementation to ChineseCheckersGameClient
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;
    private String player;
    private int numOfPlayers;
    private boolean highlighted;

    public void setConnection(String serverAddress) throws IOException {
        // Setup networking
        socket = new Socket(serverAddress, 8081);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void play() throws Exception {
        try {
            String response;
            while ((response = input.readLine()) != null) {
                System.out.println("Message from server: " + response);
                String[] words = response.split(" ");
                switch (words[0]) {
                    case "WELCOME":
                        player = words[1];
                        numOfPlayers = Integer.parseInt(words[2]);
                        break;
                    case "HIGHLIGHTED":
                        //int x = Integer.parseInt(words[1]);
                        //int y = Integer.parseInt(words[2]);
                        highlighted = true;
                        break;
                    case "DESELECTED":
                        highlighted = false;
                        break;
                    case "MOVEMADE":
                        highlighted = false;
                        output.println("ENDTURN " + player);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

/*
    public void sendMessage(String message)
    {
        output.println(message);
        output.flush();
    }
*/
}
