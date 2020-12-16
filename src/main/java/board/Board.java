package board;

/**
 * Board is realized by 2D table
 */
public abstract class Board {
    protected Field[][] board;

    protected abstract void cleanBoard();

    public abstract Board prepareForPlayers(int numberOfPlayers);

    public void setBoard(Field[][] board) {
        this.board = board;
    }

    public void setField(int x, int y, Field field){
        board[y][x] = field;
    }

    public Field getField(int x, int y) {
        return board[y][x];
    }

    public Field[][] getBoard(){
        return board;
    }

    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        return board.length;
    }

    public boolean isValidField(int x, int y){
        if((x+y)%2==0){
            switch (y){
                case 0:
                case 16:
                    return x == 12;
                case 1:
                case 15:
                    return x > 10 && x < 14;
                case 2:
                case 14:
                    return x > 9 && x < 15;
                case 3:
                case 13:
                    return x > 8 && x < 16;
                case 4:
                case 12:
                    return true;
                case 5:
                case 11:
                    return x > 0 && x < 24;
                case 6:
                case 10:
                    return x > 1 && x < 23;
                case 7:
                case 9:
                    return x > 2 && x < 22;
                case 8:
                    return x > 3 && x < 21;
                default:
                    return false;
            }
        }
        return false;
    }

}
