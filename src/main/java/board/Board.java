package board;

/**
 * Board is realized by 2D table
 */
public abstract class Board {
    protected Field[][] board;

    protected abstract void cleanBoard();

    protected abstract void prepareForPlayers(int numberOfPlayers);

    public void setBoard(Field[][] board) {
        this.board = board;
    }

    public void setField(int x, int y, Field field) {
        board[y][x] = field;
    }

    public Field getField(int x, int y) {
        return board[y][x];
    }

    public Field[][] getBoard() {
        return board;
    }

    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        return board.length;
    }

    public abstract boolean isValidField(int x, int y);
}
