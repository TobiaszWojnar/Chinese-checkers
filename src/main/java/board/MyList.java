package board;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

// TEMPORARY - just general idea
class MyList extends LinkedList<Point[]> {
    public MyList(LinkedList<Point[]> all) {
        this.addAll(all);
    }

    public static void main(String[] args) {
        List<Point[]> points2 = new LinkedList<Point[]>();

        MyList[] allPoints = new MyList[8];

        /*public ChineseCheckersBoard(int numberOfPlayers) {
            board = new Field[17][25];
            //cleanBoard();
            //prepareForPlayers(numberOfPlayers);
            allPoints[0] = new MyList((LinkedList<Point[]>)points2);
        }*/
    }
}



