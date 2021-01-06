package client.chineseCheckers.GUI;

import board.Field;

import java.awt.*;
import java.util.EnumMap;

public class ColorManager {
    private Color backgroundColor;
    private final EnumMap<Field, Color> colorMap;

    public ColorManager(int numberOfPlayers){
        backgroundColor = new Color(238,238,238);
        colorMap = new EnumMap<>(Field.class);
        switch(numberOfPlayers){
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
            //TODO error handling
        }
    }

    private void prepareForTwoPlayers() {
        colorMap.put(Field.Empty, Color.LIGHT_GRAY);
        colorMap.put(Field.Player1, Color.BLUE);
        colorMap.put(Field.Player2, Color.RED);
        colorMap.put(Field.Possible, Color.WHITE);
        colorMap.put(Field.Chosen, Color.BLACK);
    }

    private void prepareForThreePlayers() {
        prepareForTwoPlayers();
        colorMap.put(Field.Player3, Color.YELLOW);
    }

    private void prepareForFourPlayers() {
        prepareForTwoPlayers();
        colorMap.put(Field.Player3, Color.GREEN);
        colorMap.put(Field.Player4, Color.YELLOW);
    }

    private void prepareForSixPlayers() {
        prepareForFourPlayers();
        colorMap.put(Field.Player5, Color.CYAN);
        colorMap.put(Field.Player6, Color.ORANGE);
    }


    public Color getMappedColor(Field field){
        return colorMap.get(field);
    }

    public Color getMappedColor(int indexInEnum){
        return colorMap.get(Field.values()[indexInEnum]);
    }

    public void putMap(Field value, Color color) {
        colorMap.put(value,color);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
