package gui;

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
            case 3:
                prepareForThreePlayers();
            case 4:
                prepareForFourPlayers();
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

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void putMap(Field value, Color color) {
        colorMap.put(value,color);
    }
}
/*
        colors = new EnumMap<>(Field.class);
//TODO wywal do klasy na kolory
        colors.put(Field.Empty, Color.GRAY);
        colors.put(Field.Player1, Color.YELLOW);
        colors.put(Field.Player2, Color.RED);
        colors.put(Field.Player3, Color.GREEN);
        colors.put(Field.Player4, Color.BLUE);
        colors.put(Field.Player5, Color.ORANGE);
        colors.put(Field.Player6, Color.PINK);
        colors.put(Field.Possible, Color.WHITE);
 */