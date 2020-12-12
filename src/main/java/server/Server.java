package server;
import board.Field;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Executors;


public class Server {
    public static void main(String[] args) throws Exception {
        Field field = Field.valueOf("Player1");
        System.out.println(field.toString());

    }
}
