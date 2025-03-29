package start_window;

import common.HelperService;
import common.PlayMode;
import pc_vs_pc.PcVsPcController;
import pc_vs_pc.PcVsPcModel;
import pc_vs_pc.PcVsPcView;
import player_vs_pc.PlayerVsPcController;
import player_vs_pc.PlayerVsPcModel;
import player_vs_pc.PlayerVsPcView;
import player_vs_player.PlayerVsPlayerController;
import player_vs_player.PlayerVsPlayerModel;
import player_vs_player.PlayerVsPlayerView;

public class StartController {
    private final StartView startView;

    public StartController(StartView startView) {
        this.startView = startView;
    }

    public void startGame() {
        startView.init();
        addButtonsListeners();
    }

    private void addButtonsListeners() {
        var buttons = startView.getPlayModeButtons();
        for (var button : buttons) {
          button.addActionListener(e -> {
              startView.closeWindow();
             if (button.getText().equals(String.valueOf(PlayMode.PLAYER_VS_PC))) {
                 new PlayerVsPcController(new PlayerVsPcView(), new PlayerVsPcModel(new HelperService())).start();
              } else if (button.getText().equals(String.valueOf(PlayMode.PLAYER_VS_PLAYER))) {
                 new PlayerVsPlayerController(new PlayerVsPlayerView(), new PlayerVsPlayerModel(new HelperService())).start();
             } else {
                 new PcVsPcController(new PcVsPcView(), new PcVsPcModel(new HelperService())).start();
             }
          });
        }
    }
}
