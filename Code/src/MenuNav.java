import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Objects;

public class MenuNav extends KeyAdapter {
    private Game game;
    private int charSelectCount;

    public MenuNav(Game game) {
        this.game = game;
        charSelectCount = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (game.start) {
            if (key == KeyEvent.VK_ENTER) {
                game.start = false;
                game.menu = "main";
                game.menuChoice = 0;
            }
        }
        else if (Objects.equals(game.menu, "main")) {
            if (key == KeyEvent.VK_UP && game.menuChoice > 0) {
                game.menuChoice--;
            }
            else if (key == KeyEvent.VK_DOWN && game.menuChoice < 4) {
                game.menuChoice++;
            }
            else if (key == KeyEvent.VK_ENTER) {
                switch (game.menuChoice) {
                    case 0 -> game.menu = "story";
                    case 1 -> game.menu = "arcade";
                    case 2 -> game.menu = "training";
                    case 3 -> game.menu = "options";
                    case 4 -> System.exit(0);
                }
                game.menuChoice = 0;
            }
        }
        else if (Objects.equals(game.menu, "story")) {
            if (key == KeyEvent.VK_ESCAPE) {
                game.menu = "main";
                game.menuChoice = 0;
            }
            else if (key == KeyEvent.VK_ENTER) {
                game.menu = "charSelect";
                game.mode = 1;
            }
        }
        else if (Objects.equals(game.menu, "arcade")) {
            if (key == KeyEvent.VK_ESCAPE) {
                game.menu = "main";
                game.menuChoice = 1;
            }
            else if (key == KeyEvent.VK_ENTER) {
                game.menu = "charSelect";
                game.mode = 2;
            }
        }
        else if (Objects.equals(game.menu, "training")) {
            if (key == KeyEvent.VK_ESCAPE) {
                game.menu = "main";
                game.menuChoice = 2;
            }
            else if (key == KeyEvent.VK_ENTER) {
                game.menu = "charSelect";
                game.mode = 3;
            }
        }
        else if (Objects.equals(game.menu, "options")) {
            if (key == KeyEvent.VK_ESCAPE) {
                game.menu = "main";
                game.menuChoice = 3;
            }
            else if (key == KeyEvent.VK_UP && game.menuChoice > 0) {
                game.menuChoice--;
            }
            else if (key == KeyEvent.VK_DOWN && game.menuChoice < 2) {
                game.menuChoice++;
            }
            else if (key == KeyEvent.VK_ENTER) {
                switch (game.menuChoice) {
                    case 0 -> game.menu = "player1controls";
                    case 1 -> {
                        game.menu = "player2controls";
                        game.menuChoice = 0;
                    }
                    case 2 -> game.debug = !game.debug;
                }
            }
        }
        else if (Objects.equals(game.menu, "player1controls")) {
            if (key == KeyEvent.VK_ESCAPE && !game.changingControls) {
                game.menu = "options";
                game.menuChoice = 0;
            }
            else if (key == KeyEvent.VK_UP && game.menuChoice > 0 && !game.changingControls) {
                game.menuChoice--;
            }
            else if (key == KeyEvent.VK_DOWN && game.menuChoice < 6 && !game.changingControls) {
                game.menuChoice++;
            }
            else if (key == KeyEvent.VK_ENTER && !game.changingControls) {
                game.changingControls = true;
            }
            else if (game.changingControls) {
                if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_ESCAPE) {
                    game.controlsError = true;
                    game.changingControls = false;
                }

                for (int i=0; i<7; i++) {
                    if (key == game.player1Controls[i] || key == game.player2Controls[i]) {
                        game.controlsError = true;
                        game.changingControls = false;
                        break;
                    }
                }

                if (!game.controlsError) {
                    game.player1Controls[game.menuChoice] = key;
                    game.changingControls = false;
                }
            }
        }
        else if (Objects.equals(game.menu, "player2controls")) {
            if (key == KeyEvent.VK_ESCAPE && !game.changingControls) {
                game.menu = "options";
                game.menuChoice = 1;
            }
            else if (key == KeyEvent.VK_UP && game.menuChoice > 0 && !game.changingControls) {
                game.menuChoice--;
            }
            else if (key == KeyEvent.VK_DOWN && game.menuChoice < 6 && !game.changingControls) {
                game.menuChoice++;
            }
            else if (key == KeyEvent.VK_ENTER && !game.changingControls) {
                game.changingControls = true;
            }
            else if (game.changingControls) {
                if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_ESCAPE) {
                    game.controlsError = true;
                    game.changingControls = false;
                }

                for (int i=0; i<7; i++) {
                    if (key == game.player1Controls[i] || key == game.player2Controls[i]) {
                        game.controlsError = true;
                        game.changingControls = false;
                        break;
                    }
                }

                if (!game.controlsError) {
                    game.player2Controls[game.menuChoice] = key;
                    game.changingControls = false;
                }
            }
        }
        else if (Objects.equals(game.menu, "charSelect")) {
            if (charSelectCount == 0) {
                switch (key) {
                    case KeyEvent.VK_1 -> {
                        game.p1 = 1;
                        charSelectCount++;
                    }
                    case KeyEvent.VK_2 -> {
                        game.p1 = 2;
                        charSelectCount++;
                    }
                }
            }
            else if (charSelectCount == 1) {
                switch (key) {
                    case KeyEvent.VK_1 -> {
                        game.p2 = 1;
                        charSelectCount = 0;
                        game.menu = null;
                    }
                    case KeyEvent.VK_2 -> {
                        game.p2 = 2;
                        charSelectCount = 0;
                        game.menu = null;
                    }
                }
            }
        }
    }
}

