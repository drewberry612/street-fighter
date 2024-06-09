import utilities.SoundManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

import static utilities.SoundManager.ATTACK;

public class Controls extends KeyAdapter {
    private Game game;
    private final int type;
    private final int xSpeed;
    private final int ySpeed;

    public Controls(Game game, int type) {
        this.game = game;
        this.type = type;
        xSpeed = 490;
        ySpeed = 940;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // player 1
        if (key == game.player1Controls[2]) { // Left action
            if (type == 1 && game.player1.velocity.x == 0 && !game.player1.states.crouched && !game.player1.states.attacking && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking) {
                game.player1.velocity.subtract(xSpeed, 0);
                game.player1.states.walking_backward = true;
                game.player1.states.idle = false;
            }
            if (type == 1 && game.player1.states.crouched && !game.player1.states.idle) {
                game.player1.states.walking_backward = true;
            }
        }
        else if (key == game.player1Controls[3]) { // Right action
            if (type == 1 && game.player1.velocity.x == 0 && !game.player1.states.crouched && !game.player1.states.attacking && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking) {
                game.player1.velocity.add(xSpeed, 0);
                game.player1.states.walking_forward = true;
                game.player1.states.idle = false;
            }
            if (type == 1 && game.player1.states.crouched && !game.player1.states.idle) {
                game.player1.states.walking_forward = true;
            }
        }
        else if (key == game.player1Controls[0]) { // Up action
            if (type == 1 && !game.player1.states.jumping && !game.player1.states.crouched && !game.player1.states.attacking && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking) {
                game.player1.velocity.subtract(0, ySpeed);
                game.player1.states.jumping = true;
            }
        }
        else if (key == game.player1Controls[1]) { // Down action
            if (type == 1 && !game.player1.states.crouched && !game.player1.states.jumping && !game.player1.states.attacking && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking) {
                game.player1.states.crouched = true;
                game.player1.states.walking_forward = false;
                game.player1.states.walking_backward = false;
                game.player1.frames.walking = 0;
                game.player1.velocity.set(0,0);
                game.player1.states.idle = false;
            }
        }
        else if (key == game.player1Controls[4]) { // Light attack
            if (type == 1 && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking && !(game.player1.states.idle && game.player1.states.crouched) && !(game.player1.frames.crouched < 18 && game.player1.states.crouched)) {
                SoundManager.playClip(ATTACK);
                if (game.player1.states.attacking && game.player1.states.standingLight == 1) {
                    game.player1.states.standingLight = 2;
                } else if (game.player1.states.attacking && game.player1.states.jumpingLight == 1) {
                    game.player1.states.jumpingLight = 2;
                } else if (!game.player1.states.attacking && game.player1.states.crouched && game.player1.frames.crouched == 18) {
                    game.player1.states.attacking = true;
                    if (game.player1.states.walking_forward && Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.crouchedForwardLight = true;
                    }
                    else if (game.player1.states.walking_backward && Objects.equals(game.player1.side, "Right")) {
                        game.player1.states.crouchedForwardLight = true;
                    }
                    else {
                        game.player1.states.crouchedLight = true;
                    }
                } else if (!game.player1.states.attacking && game.player1.states.jumping) {
                    game.player1.states.attacking = true;
                    game.player1.states.jumpingLight = 1;
                } else if (!game.player1.states.attacking && game.player1.states.walking_forward) {
                    game.player1.states.attacking = true;
                    if (Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.forwardLight = true;
                    }
                    else {
                        game.player1.states.backwardLight = true;
                    }
                    game.player1.velocity.set(0, 0);
                } else if (!game.player1.states.attacking && game.player1.states.walking_backward) {
                    game.player1.states.attacking = true;
                    if (Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.backwardLight = true;
                    }
                    else {
                        game.player1.states.forwardLight = true;
                    }
                    game.player1.velocity.set(0, 0);
                } else if (!game.player1.states.attacking && game.player1.states.idle) {
                    game.player1.states.attacking = true;
                    game.player1.states.standingLight = 1;
                    game.player1.states.idle = false;
                }
            }
        }
        else if (key == game.player1Controls[5]) { // Medium attack
            if (type == 1 && !game.player1.states.attacking && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking && !(game.player1.states.idle && game.player1.states.crouched) && !(game.player1.frames.crouched < 18 && game.player1.states.crouched)) {
                SoundManager.playClip(ATTACK);
                if (game.player1.states.jumping) {
                    game.player1.states.attacking = true;
                    game.player1.states.jumpingMedium = true;
                } else if (game.player1.states.crouched && game.player1.frames.crouched == 18) {
                    game.player1.states.attacking = true;
                    if (game.player1.states.walking_forward && Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.crouchedForwardMedium = true;
                    }
                    else if (game.player1.states.walking_backward && Objects.equals(game.player1.side, "Right")) {
                        game.player1.states.crouchedForwardMedium = true;
                    }
                    else {
                        game.player1.states.crouchedMedium = true;
                    }
                } else if (game.player1.states.walking_forward) {
                    game.player1.states.attacking = true;
                    if (Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.forwardMedium = true;
                    }
                    else {
                        game.player1.states.backwardMedium = true;
                    }
                    game.player1.velocity.set(0, 0);
                } else if (game.player1.states.walking_backward) {
                    game.player1.states.attacking = true;
                    if (Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.backwardMedium = true;
                    }
                    else {
                        game.player1.states.forwardMedium = true;
                    }
                    game.player1.velocity.set(0, 0);
                } else if (game.player1.states.idle) {
                    game.player1.states.attacking = true;
                    game.player1.states.standingMedium = true;
                    game.player1.states.idle = false;
                }
            }
        }
        else if (key == game.player1Controls[6]) { // Heavy attack
            if (type == 1 && !game.player1.states.attacking && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking && !(game.player1.states.idle && game.player1.states.crouched) && !(game.player1.frames.crouched < 18 && game.player1.states.crouched)) {
                SoundManager.playClip(ATTACK);
                if (game.player1.states.jumping) {
                    game.player1.states.attacking = true;
                    game.player1.states.jumpingHeavy = true;
                } else if (game.player1.states.crouched && game.player1.frames.crouched == 18) {
                    game.player1.states.attacking = true;
                    if (game.player1.states.walking_forward && Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.crouchedForwardHeavy = true;
                    }
                    else if (game.player1.states.walking_backward && Objects.equals(game.player1.side, "Right")) {
                        game.player1.states.crouchedForwardHeavy = true;
                    }
                    else {
                        game.player1.states.crouchedHeavy = true;
                    }
                } else if (game.player1.states.walking_forward) {
                    game.player1.states.attacking = true;
                    if (Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.forwardHeavy = true;
                    }
                    else {
                        game.player1.states.backwardHeavy = true;
                    }
                    game.player1.velocity.set(0, 0);
                } else if (game.player1.states.walking_backward) {
                    game.player1.states.attacking = true;
                    if (Objects.equals(game.player1.side, "Left")) {
                        game.player1.states.backwardHeavy = true;
                    }
                    else {
                        game.player1.states.forwardHeavy = true;
                    }
                    game.player1.velocity.set(0, 0);
                } else if (game.player1.states.idle) {
                    game.player1.states.attacking = true;
                    game.player1.states.standingHeavy = true;
                    game.player1.states.idle = false;
                }
            }
        }

        // player 2
        else if (key == game.player2Controls[2]) { // Left action
            if (type == 2 && game.player2.velocity.x == 0 && !game.player2.states.crouched && !game.player2.states.attacking && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking) {
                game.player2.velocity.subtract(xSpeed, 0);
                game.player2.states.walking_backward = true;
                game.player2.states.idle = false;
            }
            if (type == 2 && game.player2.states.crouched && !game.player2.states.idle) {
                game.player2.states.walking_backward = true;
            }
        }
        else if (key == game.player2Controls[3]) { // Right action
            if (type == 2 && game.player2.velocity.x == 0 && !game.player2.states.crouched && !game.player2.states.attacking && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking) {
                game.player2.velocity.add(xSpeed, 0);
                game.player2.states.walking_forward = true;
                game.player2.states.idle = false;
            }
            if (type == 2 && game.player2.states.crouched && !game.player2.states.idle) {
                game.player2.states.walking_forward = true;
            }
        }
        else if (key == game.player2Controls[0]) { // Up action
            if (type == 2 && !game.player2.states.jumping && !game.player2.states.crouched && !game.player2.states.attacking && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking) {
                game.player2.velocity.subtract(0, ySpeed);
                game.player2.states.jumping = true;
            }
        }
        else if (key == game.player2Controls[1]) { // Down action
            if (type == 2 && !game.player2.states.crouched && !game.player2.states.jumping && !game.player2.states.attacking && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking) {
                game.player2.states.crouched = true;
                game.player2.states.walking_forward = false;
                game.player2.states.walking_backward = false;
                game.player1.frames.walking = 0;
                game.player2.velocity.set(0,0);
                game.player2.states.idle = false;
            }
        }
        else if (key == game.player2Controls[4]) { // Light attack
            if (type == 2 && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking && !(game.player2.states.idle && game.player2.states.crouched) && !(game.player2.frames.crouched < 18 && game.player2.states.crouched)) {
                SoundManager.playClip(ATTACK);
                if (game.player2.states.attacking && game.player2.states.standingLight == 1) {
                    game.player2.states.standingLight = 2;
                } else if (game.player2.states.attacking && game.player2.states.jumpingLight == 1) {
                    game.player2.states.jumpingLight = 2;
                } else if (!game.player2.states.attacking && game.player2.states.crouched && game.player2.frames.crouched == 18) {
                    game.player2.states.attacking = true;
                    if (game.player2.states.walking_forward && Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.crouchedForwardLight = true;
                    }
                    else if (game.player2.states.walking_backward && Objects.equals(game.player2.side, "Right")) {
                        game.player2.states.crouchedForwardLight = true;
                    }
                    else {
                        game.player2.states.crouchedLight = true;
                    }
                } else if (!game.player2.states.attacking && game.player2.states.jumping) {
                    game.player2.states.attacking = true;
                    game.player2.states.jumpingLight = 1;
                } else if (!game.player2.states.attacking && game.player2.states.walking_forward) {
                    game.player2.states.attacking = true;
                    if (Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.forwardLight = true;
                    }
                    else {
                        game.player2.states.backwardLight = true;
                    }
                    game.player2.velocity.set(0, 0);
                } else if (!game.player2.states.attacking && game.player2.states.walking_backward) {
                    game.player2.states.attacking = true;
                    if (Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.backwardLight = true;
                    }
                    else {
                        game.player2.states.forwardLight = true;
                    }
                    game.player2.velocity.set(0, 0);
                } else if (!game.player2.states.attacking && game.player2.states.idle) {
                    game.player2.states.attacking = true;
                    game.player2.states.standingLight++;
                    game.player2.states.idle = false;
                }
            }
        }
        else if (key == game.player2Controls[5]) { // Medium attack
            if (type == 2 && !game.player2.states.attacking && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking && !(game.player2.states.idle && game.player2.states.crouched) && !(game.player2.frames.crouched < 18 && game.player2.states.crouched)) {
                SoundManager.playClip(ATTACK);
                if (game.player2.states.jumping) {
                    game.player2.states.attacking = true;
                    game.player2.states.jumpingMedium = true;
                } else if (game.player2.states.crouched && game.player2.frames.crouched == 18) {
                    game.player2.states.attacking = true;
                    if (game.player2.states.walking_forward && Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.crouchedForwardMedium = true;
                    }
                    else if (game.player2.states.walking_backward && Objects.equals(game.player2.side, "Right")) {
                        game.player2.states.crouchedForwardMedium = true;
                    }
                    else {
                        game.player2.states.crouchedMedium = true;
                    }
                } else if (game.player2.states.walking_forward) {
                    game.player2.states.attacking = true;
                    if (Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.forwardMedium = true;
                    }
                    else {
                        game.player2.states.backwardMedium = true;
                    }
                    game.player2.velocity.set(0, 0);
                } else if (game.player2.states.walking_backward) {
                    game.player2.states.attacking = true;
                    if (Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.backwardMedium = true;
                    }
                    else {
                        game.player2.states.forwardMedium = true;
                    }
                    game.player2.velocity.set(0, 0);
                } else if (game.player2.states.idle) {
                    game.player2.states.attacking = true;
                    game.player2.states.standingMedium = true;
                    game.player2.states.idle = false;
                }
            }
        }
        else if (key == game.player2Controls[6]) { // Heavy attack
            if (type == 2 && !game.player2.states.attacking && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking && !(game.player2.states.idle && game.player2.states.crouched) && !(game.player2.frames.crouched < 18 && game.player2.states.crouched)) {
                SoundManager.playClip(ATTACK);
                if (game.player2.states.jumping) {
                    game.player2.states.attacking = true;
                    game.player2.states.jumpingHeavy = true;
                } else if (game.player2.states.crouched && game.player2.frames.crouched == 18) {
                    game.player2.states.attacking = true;
                    if (game.player2.states.walking_forward && Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.crouchedForwardHeavy = true;
                    }
                    else if (game.player2.states.walking_backward && Objects.equals(game.player2.side, "Right")) {
                        game.player2.states.crouchedForwardHeavy = true;
                    }
                    else {
                        game.player2.states.crouchedHeavy = true;
                    }
                } else if (game.player2.states.walking_forward) {
                    game.player2.states.attacking = true;
                    if (Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.forwardHeavy = true;
                    }
                    else {
                        game.player2.states.backwardHeavy = true;
                    }
                    game.player2.velocity.set(0, 0);
                } else if (game.player2.states.walking_backward) {
                    game.player2.states.attacking = true;
                    if (Objects.equals(game.player2.side, "Left")) {
                        game.player2.states.backwardHeavy = true;
                    }
                    else {
                        game.player2.states.forwardHeavy = true;
                    }
                    game.player2.velocity.set(0, 0);
                } else if (game.player2.states.idle) {
                    game.player2.states.attacking = true;
                    game.player2.states.standingHeavy = true;
                    game.player2.states.idle = false;
                }
            }
        }

        else if (key == KeyEvent.VK_ESCAPE) {
            game.start = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == game.player1Controls[2]) {
            if (type == 1 && game.player1.velocity.x == -xSpeed && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking) {
                game.player1.velocity.add(xSpeed, 0);
                game.player1.states.walking_backward = false;
                game.player1.frames.walking = 0;
                if (!game.player1.states.crouched) {
                    game.player1.states.idle = true;
                }
            }
        }
        else if (key == game.player1Controls[3]) {
            if (type == 1 && game.player1.velocity.x == xSpeed && !game.player1.states.knockdown && !(game.player1.states.stunned > 0) && !game.player1.states.blocking) {
                game.player1.velocity.subtract(xSpeed, 0);
                game.player1.states.walking_forward = false;
                game.player1.frames.walking = 0;
                if (!game.player1.states.crouched) {
                    game.player1.states.idle = true;
                }
            }
        }
        else if (key == game.player1Controls[1]) {
            if (type == 1 && game.player1.states.crouched && !game.player1.states.attacking) {
                game.player1.states.idle = true;
            }
        }
        else if (key == game.player2Controls[2]) {
            if (type == 2 && game.player2.velocity.x == -xSpeed && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking) {
                game.player2.velocity.add(xSpeed, 0);
                game.player2.states.walking_backward = false;
                game.player2.frames.walking = 0;
                if (!game.player2.states.crouched) {
                    game.player2.states.idle = true;
                }
            }
        }
        else if (key == game.player2Controls[3]) {
            if (type == 2 && game.player2.velocity.x == xSpeed && !game.player2.states.knockdown && !(game.player2.states.stunned > 0) && !game.player2.states.blocking) {
                game.player2.velocity.subtract(xSpeed, 0);
                game.player2.states.walking_forward = false;
                game.player2.frames.walking = 0;
                if (!game.player2.states.crouched) {
                    game.player2.states.idle = true;
                }
            }
        }
        else if (key == game.player2Controls[1]) {
            if (type == 2 && game.player2.states.crouched && !game.player2.states.attacking) {
                game.player2.states.idle = true;
            }
        }
    }
}
