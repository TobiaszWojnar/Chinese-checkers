package board;

/**
 * Board is realized by 2D table
 */
public abstract class  Board {
    protected Field[][] board;

    protected abstract void cleanBoard();

    public abstract Board prepareForPlayers(int numberOfPlayers);

    public void setField(int x, int y, Field field){
        board[y][x] = field;
    }

    public Field[][] getBoard(){
        return board;
    }

}
