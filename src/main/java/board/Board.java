package board;

/**
 * Board is realized by 2D table
 */
public abstract class Board {
    protected Field[][] board;

    protected abstract void cleanBoard();

    protected abstract void prepareForPlayers(int numberOfPlayers);

    /**
     * Setter for the 2D table of fields
     * @param board 2D table
     */
    public void setBoard(Field[][] board) {
        this.board = board;
    }

    /**
     * Sets given field to given value
     * @param x x coordinate of the field
     * @param y y coordinate of the field
     * @param field value the field is to be set to
     */
    public void setField(int x, int y, Field field) {
        board[y][x] = field;
    }

    /**
     * Gets value of given field
     * @param x x coordinate of the field
     * @param y y coordinate of the field
     * @return value of the given field
     */
    public Field getField(int x, int y) {
        return board[y][x];
    }

    /**
     * Getter for the 2D table of the board
     * @return board
     */
    public Field[][] getBoard() {
        return board;
    }

    /**
     * Getter for board width
     * @return board width
     */
    public int getWidth() {
        return board[0].length;
    }

    /**
     * Getter for board height
     * @return board height
     */
    public int getHeight() {
        return board.length;
    }

    /**
     * Abstract method meant to check if given field is valid
     * @param x x coordinate of the field
     * @param y y coordinate of the field
     * @return true if field is valid false otherwise
     */
    public abstract boolean isValidField(int x, int y);
}
