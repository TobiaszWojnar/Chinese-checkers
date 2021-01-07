package client.chineseCheckers.GUI;

/**
 * Listener for events connected to GUI
 */
public interface GameGuiListener {
    /**
     * When detected click
     * @param x field coordinate
     * @param y field coordinate
     */
    void onClicked(int x, int y);

    /**
     * if player skips
     */
    void onSkipped();
    /**
     * if player resigns
     */
    void onResigned();
    /**
     * if gui is closed
     */
    void onClose();
}
