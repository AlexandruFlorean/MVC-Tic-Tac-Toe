package common.models;

public enum PlayMode {
    PLAYER_VS_PLAYER("Player vs Player"),
    PLAYER_VS_PC("Player vs PC"),
    PC_VS_PC("PC vs PC");

    private final String label;

    PlayMode(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
