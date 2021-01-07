package client.chineseCheckers.GUI;

public interface GameGuiListener {
    void onClicked(int x, int y);

    void onSkipped();

    void onResigned();

    void onClose();
}
