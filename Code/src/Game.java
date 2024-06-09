import utilities.Constants;
import utilities.PlayerSprite;
import utilities.SoundManager;
import utilities.Window;

import javax.sound.sampled.Clip;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static utilities.SoundManager.*;

public class Game {
    public GUI gui;
    public Window window;
    public MenuNav menuNav;
    public Controls c1;
    public Controls c2;
    public Constants constants;
    public ArrayList<PlayerSprite> sprites;

    public boolean start;
    public String menu;
    public int menuChoice;
    public int p1;
    public int p2;

    public Player player1;
    public Player player2;
    private long startTime;
    public int timeLeft;
    public int winner;
    public int round;
    public boolean roundStart;
    public int p1Wins;
    public int p2Wins;

    public boolean debug;
    public int[] player1Controls;
    public int[] player2Controls;
    public boolean changingControls;
    public boolean controlsError;
    public int mode;
    private Clip music;

    public Game() throws IOException {
        constants = new Constants();
        new SoundManager();
        gui = new GUI(this);
        sprites = new ArrayList<>();
        sprites.add(new PlayerSprite(1));
        sprites.add(new PlayerSprite(2));
        sprites.add(new PlayerSprite(3));
        sprites.add(new PlayerSprite(4));
        sprites.add(new PlayerSprite(5));
        sprites.add(new PlayerSprite(6));
        debug = false;
        changingControls = false;
        controlsError = false;
        mode = 0;
        start = true;
        player1Controls = Constants.PLAYER1_DEFAULT;
        player2Controls = Constants.PLAYER2_DEFAULT;
        window = new Window(gui);
        menuNav = new MenuNav(this);
        c1 = new Controls(this, 1);
        c2 = new Controls(this, 2);
        window.addKeyListener(menuNav);
    }

    public void menus() {
        while (start) {
            gui.repaint();
            try {
                Thread.sleep(20);
            }
            catch (Exception ignored) {
            }
        }
        while (menu != null) {
            gui.repaint();
            try {
                Thread.sleep(20);
            }
            catch (Exception ignored) {
            }

            if (controlsError) {
                gui.repaint();
                try {
                    Thread.sleep(1000);
                }
                catch (Exception ignored) {
                }
                controlsError = false;
            }
        }

        setPlayerSprites();
        gui.setBackground();
        window.removeKeyListener(menuNav);
        window.addKeyListener(c1);
        if (mode != 1) {
            window.addKeyListener(c2);
        }
        gameLoop();
    }

    private void setPlayerSprites() {
        switch (p1) {
            case 1 -> gui.setPlayer1Sprite(sprites.get(0));
            case 2 -> gui.setPlayer1Sprite(sprites.get(1));
        }
        switch (p2) {
            case 1 -> gui.setPlayer2Sprite(sprites.get(0));
            case 2 -> gui.setPlayer2Sprite(sprites.get(1));
        }
    }

    private void gameLoop() {
        p1Wins = 0;
        p2Wins = 0;
        round = 1;
        resetGame();
        while (!start) {
            updateGame();
            try {
                Thread.sleep(20);
            }
            catch (Exception ignored) {
            }
            if (mode != 3) {
                checkWinLoseConditions();
            }
            else {
                if (player1.health < 100) {
                    player1.health += 1;
                }
                if (player2.health < 100) {
                    player2.health += 1;
                }
            }
        }
        window.removeKeyListener(c1);
        if (mode != 1) {
            window.removeKeyListener(c2);
        }
        window.addKeyListener(menuNav);
        menus();
    }

    private void updateGame() {
        player1.update();
        player2.update();
        if (mode != 3) {
            timeLeft = 99 - (int) (System.currentTimeMillis() - startTime) / 1000;
        }
        gui.repaint();
    }

    private void resetGame() {
        //int num = (int) (Math.random() * 3);
        //music = MUSIC.get(num);
        //SoundManager.playClip(music);

        player1 = new Player("Left");
        if (mode != 1) {
            player2 = new Player("Right");
        }
        else {
            player2 = new AI();
        }
        player1.setOther(player2);
        player2.setOther(player1);
        setPlayerSprites();
        winner = 0;
        roundStart = true;
        timeLeft = 99;
        gui.repaint();
        SoundManager.playClip(FIGHT);
        try {
            Thread.sleep(2000);
        }
        catch (Exception ignored) {
        }
        startTime = System.currentTimeMillis();
        roundStart = false;
    }

    private void checkWinLoseConditions() {
        if (timeLeft == 0) {
            if (player1.health < player2.health) {
                winner = 2;
                p2Wins++;
            }
            else if (player1.health > player2.health) {
                winner = 1;
                p1Wins++;
            }
            round++;
            gui.repaint();
            try {
                Thread.sleep(1500);
            }
            catch (Exception ignored) {
            }
            //SoundManager.stopClip(music);
            start = true;
        }
        if (player1.health <= 0 || player2.health <= 0) {
            if (player1.health <= 0) {
                player1.states.resetStates();
                player1.frames.resetFrames();
                player1.states.dead = true;
                if (Objects.equals(player1.side, "Left")) {
                    player1.velocity.set(-480, -580);
                }
                else {
                    player1.velocity.set(480, -580);
                }
                player2.states.resetStates();
                player2.frames.resetFrames();
                player2.states.idle = true;
                player2.velocity.set(0, player2.velocity.y);
                window.removeKeyListener(c1);
                window.removeKeyListener(c2);
                SoundManager.playClip(DEATH);
                for (int i=0; i<100; i++) {
                    updateGame();
                    try {
                        Thread.sleep(20);
                    }
                    catch (Exception ignored) {
                    }
                }
                winner = 2;
                p2Wins++;
            }
            else {
                player2.states.resetStates();
                player2.frames.resetFrames();
                player2.states.dead = true;
                if (Objects.equals(player2.side, "Left")) {
                    player2.velocity.set(-480, -580);
                }
                else {
                    player2.velocity.set(480, -580);
                }
                player1.states.resetStates();
                player1.frames.resetFrames();
                player1.states.idle = true;
                player1.velocity.set(0, player1.velocity.y);
                window.removeKeyListener(c1);
                window.removeKeyListener(c2);
                SoundManager.playClip(DEATH);
                for (int i=0; i<100; i++) {
                    updateGame();
                    try {
                        Thread.sleep(20);
                    }
                    catch (Exception ignored) {
                    }
                }
                winner = 1;
                p1Wins++;
            }
            window.addKeyListener(c1);
            window.addKeyListener(c2);
            round++;
            gui.repaint();
            try {
                Thread.sleep(2000);
            }
            catch (Exception ignored) {
            }
            //SoundManager.stopClip(music);
            if (p1Wins == 2 || p2Wins == 2) {
                start = true;
            }
            else {
                resetGame();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.menus();
    }
}
