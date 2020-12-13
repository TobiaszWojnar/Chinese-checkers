package server;

import board.Field;

public class Server {
    public static void main(String[] args) throws Exception {
        Field field = Field.valueOf("Player1");
        System.out.println(field.toString());

    }
}
