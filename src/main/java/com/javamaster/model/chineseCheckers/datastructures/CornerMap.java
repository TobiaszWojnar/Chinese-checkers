package com.javamaster.model.chineseCheckers.datastructures;



import com.javamaster.model.Field;
import com.javamaster.model.board.chineseCheckers.corners.*;

import java.util.HashMap;

/**
 * Class providing a map of players to their destination corners dependant on number of players
 */
public class CornerMap extends HashMap<Field, Corner> {

    /**
     * Constructor
     *
     * @param num number of players
     */
    public CornerMap(int num) {
        switch (num) {
            case 2:
                this.put(Field.Player1, LowerCorner.getInstance());
                this.put(Field.Player2, UpperCorner.getInstance());
                break;
            case 3:
                this.put(Field.Player1, LowerCorner.getInstance());
                this.put(Field.Player2, UpperLeftCorner.getInstance());
                this.put(Field.Player3, UpperRightCorner.getInstance());
                break;
            case 4:
                this.put(Field.Player1, LowerRightCorner.getInstance());
                this.put(Field.Player2, LowerLeftCorner.getInstance());
                this.put(Field.Player3, UpperLeftCorner.getInstance());
                this.put(Field.Player4, UpperRightCorner.getInstance());
                break;
            case 6:
                this.put(Field.Player1, LowerCorner.getInstance());
                this.put(Field.Player2, LowerLeftCorner.getInstance());
                this.put(Field.Player3, UpperLeftCorner.getInstance());
                this.put(Field.Player4, UpperCorner.getInstance());
                this.put(Field.Player5, UpperRightCorner.getInstance());
                this.put(Field.Player6, LowerRightCorner.getInstance());
                break;
            default:
                break;
        }
    }
}
