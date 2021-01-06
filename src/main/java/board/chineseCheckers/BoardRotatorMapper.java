package board.chineseCheckers;


import java.awt.geom.Point2D;

public class BoardRotatorMapper {
    Point2D.Float[][] mapper = new Point2D.Float[31][21];


    public BoardRotatorMapper (){//x,y
        mapper[16][10] = new Point2D.Float(0,0);

        mapper[14][10] = new Point2D.Float(-1,-1);
        mapper[15][9] = new Point2D.Float(1,-1);
        mapper[17][9] = new Point2D.Float(2,0);
        mapper[18][10] = new Point2D.Float(1,1);
        mapper[17][11] = new Point2D.Float(-1,1);
        mapper[15][11] = new Point2D.Float(-2,0);

        mapper[12][10] = new Point2D.Float(-2,-2);
        mapper[14][8] = new Point2D.Float(2,-2);
        mapper[18][8] = new Point2D.Float(4,0);
        mapper[20][10] = new Point2D.Float(2,2);
        mapper[18][12] = new Point2D.Float(-2,2);
        mapper[14][12] = new Point2D.Float(-4,0);

        mapper[13][9] = new Point2D.Float(0,-2);
        mapper[16][8] = new Point2D.Float(3,-1);
        mapper[19][9] = new Point2D.Float(3,1);
        mapper[19][11] = new Point2D.Float(0,2);
        mapper[16][12] = new Point2D.Float(-3,1);
        mapper[13][11] = new Point2D.Float(-3,-1);


        mapper[10][10] = new Point2D.Float(-3,-3);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[19][7] = new Point2D.Float(6,0);
        mapper[22][10] = new Point2D.Float(3,3);
        mapper[19][13] = new Point2D.Float(-4,2);
        mapper[13][13] = new Point2D.Float(-5,-1);

        mapper[11][9] = new Point2D.Float(-1,-3);
        mapper[15][7] = new Point2D.Float(4,-2);
        mapper[20][8] = new Point2D.Float(5,1);
        mapper[21][11] = new Point2D.Float(1,3);
        mapper[17][13] = new Point2D.Float(-3,3);
        mapper[12][12] = new Point2D.Float(-6,0);

        mapper[12][8] = new Point2D.Float(1,-3);
        mapper[17][7] = new Point2D.Float(5,-1);
        mapper[21][9] = new Point2D.Float(4,2);
        mapper[20][12] = new Point2D.Float(-1,3);
        mapper[15][13] = new Point2D.Float(-5,1);
        mapper[11][11] = new Point2D.Float(-4,-2);

        mapper[8][10] = new Point2D.Float(-4,-4);
        mapper[12][6] = new Point2D.Float(4,-4);
        mapper[20][6] = new Point2D.Float(8,0);
        mapper[24][20] = new Point2D.Float(4,4);
        mapper[20][14] = new Point2D.Float(-4,4);
        mapper[12][14] = new Point2D.Float(-8,0);

        mapper[10][8] = new Point2D.Float(-2,-4);
        mapper[14][6] = new Point2D.Float(5,-3);
        mapper[21][7] = new Point2D.Float(7,1);
        mapper[23][11] = new Point2D.Float(2,4);
        mapper[19][14] = new Point2D.Float(-5,3);
        mapper[12][13] = new Point2D.Float(-7,-1);
//##########################################################################
        mapper[10][10] = new Point2D.Float(-2,-4);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(7,1);
        mapper[10][16] = new Point2D.Float(2,4);
        mapper[10][16] = new Point2D.Float(-3,3);
        mapper[10][16] = new Point2D.Float(-7,-1);
//##########################################################################
        mapper[10][10] = new Point2D.Float(-4,-4);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-5,-5);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-5,-5);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-5,-5);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-5,-5);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-5,-5);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-6,-6);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-6,-6);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-6,-6);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-6,-6);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-6,-6);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-7,-7);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-7,-7);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-7,-7);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-7,-7);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-8,-8);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-8,-8);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-8,-8);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-9,-9);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-9,-9);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-9,-9);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);

        mapper[10][10] = new Point2D.Float(-10,-10);
        mapper[13][7] = new Point2D.Float(3,-3);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);
        mapper[10][16] = new Point2D.Float(0,0);


    }
}
