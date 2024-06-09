import utilities.Constants;
import utilities.PlayerSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.util.Objects;

import static utilities.Constants.*;

public class GUI extends JComponent {
    private final Game game;
    private Image bg;
    private AffineTransform bgTransf;

    private PlayerSprite player1Sprite;
    private PlayerSprite player2Sprite;

    private int p1Config;
    private int p2Config;

    public GUI(Game game) {
        this.game = game;
    }

    public void setPlayer1Sprite(PlayerSprite player1Sprite) {
        this.player1Sprite = player1Sprite;
    }

    public void setPlayer2Sprite(PlayerSprite player2Sprite) {
        this.player2Sprite = player2Sprite;
    }

    public void setBackground() {
        int num = (int) (Math.random() * 11);
        bg = BACKGROUND_IMAGES.get(num);
        scaleBackground();
    }

    private void scaleBackground() {
        double imWidth = bg.getWidth(null);
        double imHeight = bg.getHeight(null);
        double stretchx = WINDOW_WIDTH / imWidth;
        double stretchy = Constants.WINDOW_HEIGHT / imHeight;
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);
    }

    private void startMenu(Graphics2D g) {
        g.setColor(new Color(79,200,243));
        g.fillRect(0, 0, WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        g.drawImage(LOGO.getScaledInstance(593 + 240, 232 + 80, 0), 300, 160, null);

        AffineTransform resetTranslation = g.getTransform();
        g.translate(535, 510);
        GlyphVector glyphVector = new Font("Trebuchet MS", Font.BOLD, 30).createGlyphVector(g.getFontRenderContext(), "PRESS ENTER TO START");
        Shape textShape = glyphVector.getOutline();
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.draw(textShape);
        g.setColor(new Color(252, 127, 3));
        g.fill(textShape);
        g.setTransform(resetTranslation);
    }

    private void charSelectMenu(Graphics2D g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        text(60, 100, 90, "Character Select", new Color(252, 127, 3), g);
        g.drawImage(game.sprites.get(0).portrait.getScaledInstance(150, 150, 0), 280, 200, 150, 150, null);
        g.drawImage(game.sprites.get(0).name.getScaledInstance(100, 50, 0), 300, 360, 100, 50, null);

        g.drawImage(game.sprites.get(1).portrait.getScaledInstance(150, 150, 0), 480, 200, 150, 150, null);
        g.drawImage(game.sprites.get(1).name.getScaledInstance(100, 50, 0), 500, 370, 100, 50, null);

        g.drawImage(game.sprites.get(2).portrait.getScaledInstance(150, 150, 0), 680, 200, 150, 150, null);
        g.drawImage(game.sprites.get(2).name.getScaledInstance(100, 50, 0), 680, 360, 160, 50, null);

        g.drawImage(game.sprites.get(3).portrait.getScaledInstance(140, 140, 0), 890, 210, 140, 140, null);
        g.drawImage(game.sprites.get(3).name.getScaledInstance(100, 50, 0), 890, 360, 110, 40, null);

        g.drawImage(game.sprites.get(4).portrait.getScaledInstance(150, 150, 0), 480, 400, 150, 150, null);
        g.drawImage(game.sprites.get(4).name.getScaledInstance(100, 50, 0), 500, 540, 110, 60, null);

        g.drawImage(game.sprites.get(5).portrait.getScaledInstance(150, 150, 0), 680, 400, 150, 150, null);
        g.drawImage(game.sprites.get(5).name.getScaledInstance(100, 50, 0), 695, 550, 110, 60, null);
    }

    private void mainMenu(Graphics2D g) {
        bg = MENU;
        scaleBackground();
        g.drawImage(bg, bgTransf, null);

        String str1 = "   Story";
        String str2 = "   Arcade";
        String str3 = "   Training";
        String str4 = "   Options";
        String str5 = "   Quit";

        switch (game.menuChoice) {
            case 0 -> str1 = str1.replace("   ", "-> ");
            case 1 -> str2 = str2.replace("   ", "-> ");
            case 2 -> str3 = str3.replace("   ", "-> ");
            case 3 -> str4 = str4.replace("   ", "-> ");
            case 4 -> str5 = str5.replace("   ", "-> ");
        }

        text(600, 200, 40, str1, new Color(252, 127, 3), g);
        text(600, 275, 40, str2, new Color(252, 127, 3), g);
        text(600, 350, 40, str3, new Color(252, 127, 3), g);
        text(600, 425, 40, str4, new Color(252, 127, 3), g);
        text(600, 500, 40, str5, new Color(252, 127, 3), g);
    }

    private void storyMenu(Graphics2D g) {
        bg = MENU;
        scaleBackground();
        g.drawImage(bg, bgTransf, null);

        text(100, 100, 40, "Story", new Color(252, 127, 3), g);

        String str = "Battle against the computer. You start on the left and have player 1's control scheme.";
        text(100, 300, 30, str, new Color(252, 127, 3), g);
    }

    private void arcadeMenu(Graphics2D g) {
        bg = MENU;
        scaleBackground();
        g.drawImage(bg, bgTransf, null);

        text(100, 100, 40, "Arcade", new Color(252, 127, 3), g);

        String str = "Face off against a friend in a 'Best of 3'.";
        text(100, 300, 30, str, new Color(252, 127, 3), g);
    }

    private void trainingMenu(Graphics2D g) {
        bg = MENU;
        scaleBackground();
        g.drawImage(bg, bgTransf, null);

        text(100, 100, 40, "Arcade", new Color(252, 127, 3), g);

        String str = "Test your moveset in an endless match. Press ESC to exit.";
        text(100, 300, 30, str, new Color(252, 127, 3), g);
    }

    private void optionsMenu(Graphics2D g) {
        bg = MENU;
        scaleBackground();
        g.drawImage(bg, bgTransf, null);

        text(100, 100, 30, "Options", new Color(252, 127, 3), g);

        String str1 = "   Player 1 Controls";
        String str2 = "   Player 2 Controls";
        String str3;
        if (game.debug) {
            str3 = "   Debug Mode: ON";
        }
        else {
            str3 = "   Debug Mode: OFF";
        }

        switch (game.menuChoice) {
            case 0 -> str1 = str1.replace("   ", "-> ");
            case 1 -> str2 = str2.replace("   ", "-> ");
            case 2 -> str3 = str3.replace("   ", "-> ");
        }

        text(100, 200, 30, str1, new Color(252, 127, 3), g);
        text(100, 300, 30, str2, new Color(252, 127, 3), g);
        text(100, 400, 30, str3, new Color(252, 127, 3), g);
    }

    private void player1Controls(Graphics2D g) {
        bg = MENU;
        scaleBackground();
        g.drawImage(bg, bgTransf, null);

        text(100, 100, 30, "Player 1 Controls", new Color(252, 127, 3), g);

        String str1 = "   Up Action: " + KeyEvent.getKeyText(game.player1Controls[0]);
        String str2 = "   Down Action: " + KeyEvent.getKeyText(game.player1Controls[1]);
        String str3 = "   Left Action: " + KeyEvent.getKeyText(game.player1Controls[2]);
        String str4 = "   Right Action: " + KeyEvent.getKeyText(game.player1Controls[3]);
        String str5 = "   Light Attack: " + KeyEvent.getKeyText(game.player1Controls[4]);
        String str6 = "   Medium Attack: " + KeyEvent.getKeyText(game.player1Controls[5]);
        String str7 = "   Heavy Attack: " + KeyEvent.getKeyText(game.player1Controls[6]);

        switch (game.menuChoice) {
            case 0 -> str1 = str1.replace("   ", "-> ");
            case 1 -> str2 = str2.replace("   ", "-> ");
            case 2 -> str3 = str3.replace("   ", "-> ");
            case 3 -> str4 = str4.replace("   ", "-> ");
            case 4 -> str5 = str5.replace("   ", "-> ");
            case 5 -> str6 = str6.replace("   ", "-> ");
            case 6 -> str7 = str7.replace("   ", "-> ");
        }

        text(100, 200, 30, str1, new Color(252, 127, 3), g);
        text(100, 250, 30, str2, new Color(252, 127, 3), g);
        text(100, 300, 30, str3, new Color(252, 127, 3), g);
        text(100, 350, 30, str4, new Color(252, 127, 3), g);
        text(100, 400, 30, str5, new Color(252, 127, 3), g);
        text(100, 450, 30, str6, new Color(252, 127, 3), g);
        text(100, 500, 30, str7, new Color(252, 127, 3), g);

        if (game.controlsError) {
            text(500, 500, 30, "Input error, try again", new Color(252, 127, 3), g);
        }
        else if (game.changingControls) {
            text(500, 500, 30, "Waiting for input...", new Color(252, 127, 3), g);
        }
    }

    private void player2Controls(Graphics2D g) {
        bg = MENU;
        scaleBackground();
        g.drawImage(bg, bgTransf, null);

        text(100, 100, 30, "Player 2 Controls", new Color(252, 127, 3), g);

        String str1 = "   Up Action: " + KeyEvent.getKeyText(game.player2Controls[0]);
        String str2 = "   Down Action: " + KeyEvent.getKeyText(game.player2Controls[1]);
        String str3 = "   Left Action: " + KeyEvent.getKeyText(game.player2Controls[2]);
        String str4 = "   Right Action: " + KeyEvent.getKeyText(game.player2Controls[3]);
        String str5 = "   Light Attack: " + KeyEvent.getKeyText(game.player2Controls[4]);
        String str6 = "   Medium Attack: " + KeyEvent.getKeyText(game.player2Controls[5]);
        String str7 = "   Heavy Attack: " + KeyEvent.getKeyText(game.player2Controls[6]);

        switch (game.menuChoice) {
            case 0 -> str1 = str1.replace("   ", "-> ");
            case 1 -> str2 = str2.replace("   ", "-> ");
            case 2 -> str3 = str3.replace("   ", "-> ");
            case 3 -> str4 = str4.replace("   ", "-> ");
            case 4 -> str5 = str5.replace("   ", "-> ");
            case 5 -> str6 = str6.replace("   ", "-> ");
            case 6 -> str7 = str7.replace("   ", "-> ");
        }

        text(100, 200, 30, str1, new Color(252, 127, 3), g);
        text(100, 250, 30, str2, new Color(252, 127, 3), g);
        text(100, 300, 30, str3, new Color(252, 127, 3), g);
        text(100, 350, 30, str4, new Color(252, 127, 3), g);
        text(100, 400, 30, str5, new Color(252, 127, 3), g);
        text(100, 450, 30, str6, new Color(252, 127, 3), g);
        text(100, 500, 30, str7, new Color(252, 127, 3), g);


        if (game.controlsError) {
            text(500, 500, 30, "Input error, try again", new Color(252, 127, 3), g);
        }
        else if (game.changingControls) {
            text(500, 500, 30, "Waiting for input...", new Color(252, 127, 3), g);
        }
    }

    private void gameplay(Graphics2D g) {
        g.drawImage(bg, bgTransf, null);
        player1Sprite(g);
        player2Sprite(g);
        overlay(g);
    }

    private void player1Sprite(Graphics2D g) {
        if (game.player1.states.dead) {
            switch (game.player1.frames.death / 8) {
                case 0 -> p1Config = 108;
                case 1 -> p1Config = 109;
                case 2 -> p1Config = 110;
                case 3 -> p1Config = 111;
                case 4,5 -> p1Config = 117;
            }
        }
        else if (game.player1.states.stunned > 0) {
            switch (game.player1.states.stunned) {
                case 1 -> {
                    switch (game.player1.frames.stun / 12) {
                        case 0 -> p1Config = 101;
                        case 1 -> p1Config = 102;
                    }
                }
                case 2 -> {
                    switch (game.player1.frames.stun / 12) {
                        case 0 -> p1Config = 103;
                        case 1 -> p1Config = 104;
                        case 2 -> p1Config = 105;
                    }
                }
                case 3 -> {
                    switch (game.player1.frames.stun / 12) {
                        case 0,2 -> p1Config = 106;
                        case 1 -> p1Config = 107;
                    }
                }
            }
        }
        else if (game.player1.states.knockdown) {
            switch (game.player1.frames.knockdown / 8) {
                case 0 -> p1Config = 108;
                case 1 -> p1Config = 109;
                case 2 -> p1Config = 110;
                case 3 -> p1Config = 111;
                case 4 -> p1Config = 112;
                case 5 -> p1Config = 113;
                case 6 -> p1Config = 114;
                case 7 -> p1Config = 115;
                case 8 -> p1Config = 116;
            }
        }
        else if (game.player1.states.blocking) {
            if (game.player1.states.crouched) {
                switch (game.player1.frames.block / 6) {
                    case 0 -> p1Config = 120;
                    case 1 -> p1Config = 121;
                }
            }
            else {
                switch (game.player1.frames.block / 6) {
                    case 0, 2 -> p1Config = 118;
                    case 1 -> p1Config = 119;
                }
            }
        }
        else if (game.player1.states.turning && !game.player1.states.jumping) {
            if (game.player1.states.crouched) {
                switch (game.player1.frames.turning / 6) {
                    case 0 -> p1Config = 49;
                    case 1 -> p1Config = 50;
                    case 2 -> p1Config = 51;
                }
            } else {
                switch (game.player1.frames.turning / 6) {
                    case 0 -> p1Config = 5;
                    case 1 -> p1Config = 6;
                    case 2 -> p1Config = 7;
                }
            }
        }
        else if (game.player1.states.attacking) {
            if (game.player1.states.standingLight > 0) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 20;
                    case 1 -> p1Config = 21;
                    case 2, 6 -> {
                        if (game.player1.frames.attacking == 1) {
                            p1Config = 20;
                        } else {
                            p1Config = 22;
                        }
                    }
                    case 3, 5 -> p1Config = 23;
                    case 4 -> p1Config = 24;
                }
            } else if (game.player1.states.standingMedium) {
                switch (game.player1.frames.attacking / 6) {
                    case 0, 4 -> p1Config = 25;
                    case 1, 3 -> p1Config = 26;
                    case 2 -> p1Config = 27;
                }
            } else if (game.player1.states.standingHeavy) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 28;
                    case 1 -> p1Config = 29;
                    case 2 -> p1Config = 30;
                    case 3 -> p1Config = 31;
                    case 4 -> p1Config = 32;
                }
            } else if (game.player1.states.jumpingLight > 0) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 52;
                    case 1 -> p1Config = 53;
                    case 2 -> p1Config = 54;
                    case 3 -> p1Config = 55;
                }
            } else if (game.player1.states.jumpingMedium) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 56;
                    case 1 -> p1Config = 57;
                }
            } else if (game.player1.states.jumpingHeavy) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 58;
                    case 1 -> p1Config = 59;
                    case 2 -> p1Config = 60;
                    case 3 -> p1Config = 61;
                    case 4 -> p1Config = 62;
                }
            } else if (game.player1.states.crouchedLight) {
                switch (game.player1.frames.attacking / 6) {
                    case 0,2 -> p1Config = 63;
                    case 1 -> p1Config = 64;
                }
            } else if (game.player1.states.crouchedMedium) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 65;
                    case 1,3 -> p1Config = 66;
                    case 2 -> p1Config = 67;
                }
            } else if (game.player1.states.crouchedHeavy) {
                switch (game.player1.frames.attacking / 6) {
                    case 0,4 -> p1Config = 68;
                    case 1,3 -> p1Config = 69;
                    case 2 -> p1Config = 70;
                }
            } else if (game.player1.states.forwardLight) {
                switch (game.player1.frames.attacking / 6) {
                    case 0,2 -> p1Config = 71;
                    case 1 -> p1Config = 72;
                }
            } else if (game.player1.states.forwardMedium) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 73;
                    case 1,5 -> p1Config = 74;
                    case 2,4 -> p1Config = 75;
                    case 3 -> p1Config = 76;
                }
            } else if (game.player1.states.forwardHeavy) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 77;
                    case 1,3 -> p1Config = 78;
                    case 2 -> p1Config = 79;
                }
            } else if (game.player1.states.backwardLight) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 80;
                    case 1,3 -> p1Config = 81;
                    case 2 -> p1Config = 82;
                }
            } else if (game.player1.states.backwardMedium) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 83;
                    case 1,3 -> p1Config = 84;
                    case 2 -> p1Config = 85;
                }
            } else if (game.player1.states.backwardHeavy) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 86;
                    case 1 -> p1Config = 87;
                    case 2 -> p1Config = 88;
                    case 3 -> p1Config = 89;
                    case 4 -> p1Config = 90;
                }
            } else if (game.player1.states.crouchedForwardLight) {
                switch (game.player1.frames.attacking / 6) {
                    case 0,2 -> p1Config = 91;
                    case 1 -> p1Config = 92;
                }
            } else if (game.player1.states.crouchedForwardMedium) {
                switch (game.player1.frames.attacking / 6) {
                    case 0,4 -> p1Config = 93;
                    case 1,3 -> p1Config = 94;
                    case 2 -> p1Config = 95;
                }
            } else if (game.player1.states.crouchedForwardHeavy) {
                switch (game.player1.frames.attacking / 6) {
                    case 0 -> p1Config = 96;
                    case 1 -> p1Config = 97;
                    case 2 -> p1Config = 98;
                    case 3 -> p1Config = 99;
                    case 4 -> p1Config = 100;
                }
            }
        }
        else if (game.player1.states.crouched) {
            switch (game.player1.frames.crouched / 6) {
                case 0 -> p1Config = 46;
                case 1 -> p1Config = 47;
                case 2,3 -> p1Config = 48;
            }
        }
        else if (game.player1.states.idle) {
            if (game.player1.states.jumping) {
                switch (game.player1.frames.jumping / 6) {
                    case 0 -> p1Config = 33;
                    case 1 -> p1Config = 34;
                    case 2 -> p1Config = 35;
                    case 3 -> p1Config = 36;
                    case 4 -> p1Config = 37;
                    case 5,6 -> p1Config = 38;
                }
            } else {
                switch (game.player1.frames.idle / 6) {
                    case 0 -> p1Config = 0;
                    case 1 -> p1Config = 1;
                    case 2 -> p1Config = 2;
                    case 3 -> p1Config = 3;
                    case 4 -> p1Config = 4;
                }
            }
        }
        else if ((game.player1.states.walking_forward && Objects.equals(game.player1.side, "Left")) || (game.player1.states.walking_backward && Objects.equals(game.player1.side, "Right"))) {
            if (game.player1.states.jumping) {
                switch (game.player1.frames.jumping / 6) {
                    case 0 -> p1Config = 39;
                    case 1 -> p1Config = 40;
                    case 2 -> p1Config = 41;
                    case 3 -> p1Config = 42;
                    case 4 -> p1Config = 43;
                    case 5 -> p1Config = 44;
                    case 6,7 -> p1Config = 45;
                }
            } else {
                switch (game.player1.frames.walking / 6) {
                    case 0 -> p1Config = 8;
                    case 1 -> p1Config = 9;
                    case 2 -> p1Config = 10;
                    case 3 -> p1Config = 11;
                    case 4 -> p1Config = 12;
                    case 5 -> p1Config = 13;
                }
            }
        }
        else if ((game.player1.states.walking_backward && Objects.equals(game.player1.side, "Left")) || (game.player1.states.walking_forward && Objects.equals(game.player1.side, "Right"))) {
            if (game.player1.states.jumping) {
                switch (game.player1.frames.jumping / 6) {
                    case 0 -> p1Config = 45;
                    case 1 -> p1Config = 44;
                    case 2 -> p1Config = 43;
                    case 3 -> p1Config = 42;
                    case 4 -> p1Config = 41;
                    case 5 -> p1Config = 40;
                    case 6,7 -> p1Config = 39;
                }
            } else {
                switch (game.player1.frames.walking / 6) {
                    case 0 -> p1Config = 14;
                    case 1 -> p1Config = 15;
                    case 2 -> p1Config = 16;
                    case 3 -> p1Config = 17;
                    case 4 -> p1Config = 18;
                    case 5 -> p1Config = 19;
                }
            }
        }

        if (Objects.equals(game.player1.side, "Left")) {
            player1Sprite.drawLeftSprite((int)game.player1.position.x, (int)game.player1.position.y, p1Config, g);
        } else {
            player1Sprite.drawRightSprite((int)game.player1.position.x, (int)game.player1.position.y, p1Config, g);
        }
    }

    private void player2Sprite(Graphics2D g) {
        if (game.player2.states.dead) {
            switch (game.player2.frames.death / 8) {
                case 0 -> p2Config = 108;
                case 1 -> p2Config = 109;
                case 2 -> p2Config = 110;
                case 3 -> p2Config = 111;
                case 4,5 -> p2Config = 117;
            }
        }
        else if (game.player2.states.stunned > 0) {
            switch (game.player2.states.stunned) {
                case 1 -> {
                    switch (game.player2.frames.stun / 12) {
                        case 0,2 -> p2Config = 101;
                        case 1 -> p2Config = 102;
                    }
                }
                case 2 -> {
                    switch (game.player2.frames.stun / 12) {
                        case 0 -> p2Config = 103;
                        case 1 -> p2Config = 104;
                        case 2 -> p2Config = 105;
                    }
                }
                case 3 -> {
                    switch (game.player2.frames.stun / 12) {
                        case 0,2 -> p2Config = 106;
                        case 1 -> p2Config = 107;
                    }
                }
            }
        }
        else if (game.player2.states.knockdown) {
            switch (game.player2.frames.knockdown / 8) {
                case 0 -> p2Config = 108;
                case 1 -> p2Config = 109;
                case 2 -> p2Config = 110;
                case 3 -> p2Config = 111;
                case 4 -> p2Config = 112;
                case 5 -> p2Config = 113;
                case 6 -> p2Config = 114;
                case 7 -> p2Config = 115;
                case 8 -> p2Config = 116;
            }
        }
        else if (game.player2.states.blocking) {
            if (game.player2.states.crouched) {
                switch (game.player2.frames.block / 6) {
                    case 0,2 -> p2Config = 120;
                    case 1 -> p2Config = 121;
                }
            }
            else {
                switch (game.player2.frames.block / 6) {
                    case 0 -> p2Config = 118;
                    case 1 -> p2Config = 119;
                }
            }
        }
        else if (game.player2.states.turning && !game.player2.states.jumping) {
            if (game.player2.states.crouched) {
                switch (game.player2.frames.turning / 6) {
                    case 0 -> p2Config = 49;
                    case 1 -> p2Config = 50;
                    case 2 -> p2Config = 51;
                }
            } else {
                switch (game.player2.frames.turning / 6) {
                    case 0 -> p2Config = 5;
                    case 1 -> p2Config = 6;
                    case 2 -> p2Config = 7;
                }
            }
        }
        else if (game.player2.states.attacking) {
            if (game.player2.states.standingLight > 0) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 20;
                    case 1 -> p2Config = 21;
                    case 2, 6 -> {
                        if (game.player2.frames.attacking == 1) {
                            p2Config = 20;
                        } else {
                            p2Config = 22;
                        }
                    }
                    case 3, 5 -> p2Config = 23;
                    case 4 -> p2Config = 24;
                }
            }
            else if (game.player2.states.standingMedium) {
                switch (game.player2.frames.attacking / 6) {
                    case 0, 4 -> p2Config = 25;
                    case 1, 3 -> p2Config = 26;
                    case 2 -> p2Config = 27;
                }
            }
            else if (game.player2.states.standingHeavy) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 28;
                    case 1 -> p2Config = 29;
                    case 2 -> p2Config = 30;
                    case 3 -> p2Config = 31;
                    case 4 -> p2Config = 32;
                }
            }
            else if (game.player2.states.jumpingLight > 0) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 52;
                    case 1 -> p2Config = 53;
                    case 2 -> p2Config = 54;
                    case 3 -> p2Config = 55;
                }
            }
            else if (game.player2.states.jumpingMedium) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 56;
                    case 1 -> p2Config = 57;
                }
            }
            else if (game.player2.states.jumpingHeavy) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 58;
                    case 1 -> p2Config = 59;
                    case 2 -> p2Config = 60;
                    case 3 -> p2Config = 61;
                    case 4 -> p2Config = 62;
                }
            }
            else if (game.player2.states.crouchedLight) {
                switch (game.player2.frames.attacking / 6) {
                    case 0,2 -> p2Config = 63;
                    case 1 -> p2Config = 64;
                }
            }
            else if (game.player2.states.crouchedMedium) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 65;
                    case 1,3 -> p2Config = 66;
                    case 2 -> p2Config = 67;
                }
            }
            else if (game.player2.states.crouchedHeavy) {
                switch (game.player2.frames.attacking / 6) {
                    case 0,4 -> p2Config = 68;
                    case 1,3 -> p2Config = 69;
                    case 2 -> p2Config = 70;
                }
            }
            else if (game.player2.states.forwardLight) {
                switch (game.player2.frames.attacking / 6) {
                    case 0,2 -> p2Config = 71;
                    case 1 -> p2Config = 72;
                }
            }
            else if (game.player2.states.forwardMedium) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 73;
                    case 1,5 -> p2Config = 74;
                    case 2,4 -> p2Config = 75;
                    case 3 -> p2Config = 76;
                }
            }
            else if (game.player2.states.forwardHeavy) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 77;
                    case 1,3 -> p2Config = 78;
                    case 2 -> p2Config = 79;
                }
            }
            else if (game.player2.states.backwardLight) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 80;
                    case 1,3 -> p2Config = 81;
                    case 2 -> p2Config = 82;
                }
            }
            else if (game.player2.states.backwardMedium) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 83;
                    case 1,3 -> p2Config = 84;
                    case 2 -> p2Config = 85;
                }
            }
            else if (game.player2.states.backwardHeavy) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 86;
                    case 1 -> p2Config = 87;
                    case 2 -> p2Config = 88;
                    case 3 -> p2Config = 89;
                    case 4 -> p2Config = 90;
                }
            }
            else if (game.player2.states.crouchedForwardLight) {
                switch (game.player2.frames.attacking / 6) {
                    case 0,2 -> p2Config = 91;
                    case 1 -> p2Config = 92;
                }
            }
            else if (game.player2.states.crouchedForwardMedium) {
                switch (game.player2.frames.attacking / 6) {
                    case 0,4 -> p2Config = 93;
                    case 1,3 -> p2Config = 94;
                    case 2 -> p2Config = 95;
                }
            }
            else if (game.player2.states.crouchedForwardHeavy) {
                switch (game.player2.frames.attacking / 6) {
                    case 0 -> p2Config = 96;
                    case 1 -> p2Config = 97;
                    case 2 -> p2Config = 98;
                    case 3 -> p2Config = 99;
                    case 4 -> p2Config = 100;
                }
            }
        }
        else if (game.player2.states.crouched) {
            switch (game.player2.frames.crouched / 6) {
                case 0 -> p2Config = 46;
                case 1 -> p2Config = 47;
                case 2,3 -> p2Config = 48;
            }
        }
        else if (game.player2.states.idle) {
            if (game.player2.states.jumping) {
                switch (game.player2.frames.jumping / 6) {
                    case 0 -> p2Config = 33;
                    case 1 -> p2Config = 34;
                    case 2 -> p2Config = 35;
                    case 3 -> p2Config = 36;
                    case 4 -> p2Config = 37;
                    case 5,6 -> p2Config = 38;
                }
            }
            else {
                switch (game.player2.frames.idle / 6) {
                    case 0 -> p2Config = 0;
                    case 1 -> p2Config = 1;
                    case 2 -> p2Config = 2;
                    case 3 -> p2Config = 3;
                    case 4 -> p2Config = 4;
                }
            }
        }
        else if ((game.player2.states.walking_forward && Objects.equals(game.player2.side, "Left")) || (game.player2.states.walking_backward && Objects.equals(game.player2.side, "Right"))) {
            if (game.player2.states.jumping) {
                switch (game.player2.frames.jumping / 6) {
                    case 0 -> p2Config = 39;
                    case 1 -> p2Config = 40;
                    case 2 -> p2Config = 41;
                    case 3 -> p2Config = 42;
                    case 4 -> p2Config = 43;
                    case 5 -> p2Config = 44;
                    case 6,7 -> p2Config = 45;
                }
            }
            else {
                switch (game.player2.frames.walking / 6) {
                    case 0 -> p2Config = 8;
                    case 1 -> p2Config = 9;
                    case 2 -> p2Config = 10;
                    case 3 -> p2Config = 11;
                    case 4 -> p2Config = 12;
                    case 5 -> p2Config = 13;
                }
            }
        }
        else if ((game.player2.states.walking_backward && Objects.equals(game.player2.side, "Left")) || (game.player2.states.walking_forward && Objects.equals(game.player2.side, "Right"))) {
            if (game.player2.states.jumping) {
                switch (game.player2.frames.jumping / 6) {
                    case 0 -> p2Config = 45;
                    case 1 -> p2Config = 44;
                    case 2 -> p2Config = 43;
                    case 3 -> p2Config = 42;
                    case 4 -> p2Config = 41;
                    case 5 -> p2Config = 40;
                    case 6,7 -> p2Config = 39;
                }
            }
            else {
                switch (game.player2.frames.walking / 6) {
                    case 0 -> p2Config = 14;
                    case 1 -> p2Config = 15;
                    case 2 -> p2Config = 16;
                    case 3 -> p2Config = 17;
                    case 4 -> p2Config = 18;
                    case 5 -> p2Config = 19;
                }
            }
        }

        if (Objects.equals(game.player2.side, "Left")) {
            player2Sprite.drawLeftSprite((int)game.player2.position.x, (int)game.player2.position.y, p2Config, g);
        } else {
            player2Sprite.drawRightSprite((int)game.player2.position.x, (int)game.player2.position.y, p2Config, g);
        }
    }

    private void overlay(Graphics2D g) {
        if (game.debug) {
            hitbox(g);
        }

        // player 1 health bar
        g.setColor(Color.red);
        g.fillRect(130, 10, 500, 80);
        g.setColor(Color.green);
        g.fillRect(130, 10, 5 * game.player1.health, 80);
        g.setColor(Color.white);
        g.drawRect(130, 10, 500, 80);

        // player 2 health bar
        g.setColor(Color.red);
        g.fillRect(780, 10, 500, 80);
        g.setColor(Color.green);
        g.fillRect(780 - 5 * (game.player2.health - 100), 10, 5 * game.player2.health, 80);
        g.setColor(Color.white);
        g.drawRect(780, 10, 500, 80);

        // Character Portraits
        g.drawImage(player1Sprite.portrait.getScaledInstance(150, 150, 0), 10, 0, 150, 150, null);
        g.drawImage(player2Sprite.portrait.getScaledInstance(150, 150, 0), 1400, 0, -150, 150, null);

        // Character Names
        g.drawImage(player1Sprite.name.getScaledInstance(100, 50, 0), 160, 100, 100, 50, null);
        g.drawImage(player2Sprite.name.getScaledInstance(100, 50, 0), 1150, 110, 100, 50, null);

        // Player wins tally
        g.setColor(Color.white);
        g.drawOval(510, 100, 50, 50);
        g.drawOval(570, 100, 50, 50);
        if (game.p1Wins == 0) {
            g.setColor(Color.red);
            g.fillOval(510, 100, 50, 50);
            g.fillOval(570, 100, 50, 50);
        }
        else if (game.p1Wins == 1) {
            g.setColor(Color.red);
            g.fillOval(510, 100, 50, 50);
            g.setColor(Color.green);
            g.fillOval(570, 100, 50, 50);
        }
        else if (game.p1Wins == 2) {
            g.setColor(Color.green);
            g.fillOval(510, 100, 50, 50);
            g.fillOval(570, 100, 50, 50);
        }

        g.setColor(Color.white);
        g.drawOval(800, 100, 50, 50);
        g.drawOval(860, 100, 50, 50);
        if (game.p2Wins == 0) {
            g.setColor(Color.red);
            g.fillOval(800, 100, 50, 50);
            g.fillOval(860, 100, 50, 50);
        }
        else if (game.p2Wins == 1) {
            g.setColor(Color.green);
            g.fillOval(800, 100, 50, 50);
            g.setColor(Color.red);
            g.fillOval(860, 100, 50, 50);
        }
        else if (game.p2Wins == 3) {
            g.setColor(Color.green);
            g.fillOval(800, 100, 50, 50);
            g.fillOval(860, 100, 50, 50);
        }

        if (game.mode != 3) {
            int x = game.timeLeft > 9 ? 635 : 670;
            Color color = game.timeLeft > 5 ? new Color(252, 127, 3) : Color.red;
            text(x, 110, 123, String.valueOf(game.timeLeft), color, g);
        }
        else {
            text(670, 110, 123, "∞", new Color(252, 127, 3), g);
        }

        if (game.roundStart) {
            text(330, 410, 190, "ROUND " + game.round, new Color(252, 127, 3), g);
            text(470, 610, 190, "FIGHT", new Color(252, 127, 3), g);
        }
        // Time Up
        else if (game.timeLeft == 0) {
            text(350, 420, 190, "TIME UP", new Color(252, 127, 3), g);
        }
        // Player 1 wins
        else if (game.winner == 1 && game.p1Wins == 1) {
            text(530, 530, 280, "KO", new Color(252, 127, 3), g);
        }
        else if (game.winner == 1 && game.p1Wins == 2) {
            text(267, 450, 190, "RYU WINS", new Color(252, 127, 3), g);
        }
        // Player 2 wins
        else if (game.winner == 2 && game.p2Wins == 1) {
            text(530, 530, 280, "KO", new Color(252, 127, 3), g);
        }
        else if (game.winner == 2 && game.p2Wins == 2) {
            text(267, 450, 190, "KEN WINS", new Color(252, 127, 3), g);
        }
    }

    private void hitbox(Graphics2D g) {
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(3));
        int y1;
        int y2;
        int h1;
        int h2;
        if (game.player1.states.crouched) {
            y1 = (int) game.player1.position.y + 100;
            h1 = 220;
        }
        else {
            y1 = (int) game.player1.position.y;
            h1 = 320;
        }
        if (game.player2.states.crouched) {
            y2 = (int) game.player2.position.y + 100;
            h2 = 220;
        }
        else {
            y2 = (int) game.player2.position.y;
            h2 = 320;
        }
        g.drawRect((int)game.player1.position.x, y1, 220, h1);
        g.drawRect((int)game.player2.position.x, y2, 220, h2);
    }

    private void text(int x, int y, int fontSize, String text, Color color, Graphics2D g) {
        AffineTransform resetTranslation = g.getTransform();
        g.translate(x, y);
        GlyphVector glyphVector = new Font("Trebuchet MS", Font.BOLD, fontSize).createGlyphVector(g.getFontRenderContext(), text);
        Shape textShape = glyphVector.getOutline();
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.draw(textShape);
        g.setColor(color);
        g.fill(textShape);
        g.setTransform(resetTranslation);
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        if (game.start) {
            startMenu(g);
        }
        else if (Objects.equals(game.menu, "main")) {
            mainMenu(g);
        }
        else if (Objects.equals(game.menu, "story")) {
            storyMenu(g);
        }
        else if (Objects.equals(game.menu, "arcade")) {
            arcadeMenu(g);
        }
        else if (Objects.equals(game.menu, "training")) {
            trainingMenu(g);
        }
        else if (Objects.equals(game.menu, "options")) {
            optionsMenu(g);
        }
        else if (Objects.equals(game.menu, "player1controls")) {
            player1Controls(g);
        }
        else if (Objects.equals(game.menu, "player2controls")) {
            player2Controls(g);
        }
        else if (Objects.equals(game.menu, "charSelect")) {
            charSelectMenu(g);
        }
        else {
            gameplay(g);
        }
    }

    public Dimension getPreferredSize() {
        return WINDOW_DIMENSION;
    }
}