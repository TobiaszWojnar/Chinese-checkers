package com.javamaster.model.board;

/**
 * Point with integer coordinates
 */
public class IntPoint {
    /**
     * X coordinate
     */
    int x;

    /**
     * Y coordinate
     */
    int y;

    /**
     * Constructor which sets coordinates to given values.
     *
     * @param x x coordinate
     * @param y u coordinate
     */
    public IntPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X coordinate getter
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Y coordinate getter
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
}

