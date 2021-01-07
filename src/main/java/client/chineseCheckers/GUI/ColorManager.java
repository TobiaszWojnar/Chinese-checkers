package client.chineseCheckers.GUI;

import board.Field;

import java.awt.*;
import java.util.EnumMap;

/**
 * Is a map from Field to color, additional chooses initial colors.
 */
public class ColorManager {
    private Color backgroundColor;
    private final EnumMap<Field, Color> colorMap;

    /**
     * Based on number of players chooses initial colors and sets mapping of colors.
     * @param numberOfPlayers integer equals to 2,3,4 or 6
     */
    public ColorManager(int numberOfPlayers) {
        backgroundColor = new Color(238, 238, 238);
        colorMap = new EnumMap<>(Field.class);
        switch (numberOfPlayers) {
            case 2:
                prepareForTwoPlayers();
                break;
            case 3:
                prepareForThreePlayers();
                break;
            case 4:
                prepareForFourPlayers();
                break;
            case 6:
                prepareForSixPlayers();
                break;
        }
    }

    /**
     * Sets initial map for 2 players
     */
    private void prepareForTwoPlayers() {
        colorMap.put(Field.Empty, Color.LIGHT_GRAY);
        colorMap.put(Field.Player1, Color.BLUE);
        colorMap.put(Field.Player2, Color.RED);
        colorMap.put(Field.Possible, Color.WHITE);
        colorMap.put(Field.Chosen, Color.BLACK);
    }

    /**
     * Sets initial map for 3 players
     */
    private void prepareForThreePlayers() {
        prepareForTwoPlayers();
        colorMap.put(Field.Player3, Color.YELLOW);
    }

    /**
     * Sets initial map for 4 players
     */
    private void prepareForFourPlayers() {
        prepareForTwoPlayers();
        colorMap.put(Field.Player3, Color.GREEN);
        colorMap.put(Field.Player4, Color.YELLOW);
    }

    /**
     * Sets initial map for 6 players
     */
    private void prepareForSixPlayers() {
        prepareForFourPlayers();
        colorMap.put(Field.Player5, Color.CYAN);
        colorMap.put(Field.Player6, Color.ORANGE);
    }

    /**
     * Getter of color based on Field
     * @param field type of field
     * @return mapped color
     */
    public Color getMappedColor(Field field) {
        return colorMap.get(field);
    }

    /**
     * Getter of color based on Field index
     * @param indexInEnum index of field
     * @return mapped color
     */
    public Color getMappedColor(int indexInEnum) {
        return colorMap.get(Field.values()[indexInEnum]);
    }

    /**
     * Adds map
     * @param value field
     * @param color color
     */
    public void putMap(Field value, Color color) {
        colorMap.put(value, color);
    }

    /**
     * @return background Color of game.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor Sets background Color of game.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
