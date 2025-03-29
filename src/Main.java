import start_window.StartController;
import start_window.StartView;

public class Main {
    public static void main(String[] args) {
        StartController ticTacToe = new StartController(new StartView());
        ticTacToe.startGame();
    }
}