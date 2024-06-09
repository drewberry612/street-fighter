import java.util.Objects;

public class AI extends Player {
    public AI() {
        super("Right");
    }

    public void update() {
        if (!states.dead) {
            chooseAction();
        }
        super.update();
    }

    private void chooseAction() {
        double i = Math.random();

        if (i < 0.2) {
            double j = Math.random();
            // jump
            if (j <= 0.1 && !states.jumping && !states.crouched && !states.attacking && !states.knockdown && !(states.stunned > 0) && !states.blocking) {
                velocity.subtract(0, 940);
                states.jumping = true;
            }
            // crouch
            else if (j <= 0.2 && !states.crouched && !states.jumping && !states.attacking && !states.knockdown && !(states.stunned > 0) && !states.blocking) {
                states.crouched = true;
                states.walking_forward = false;
                states.walking_backward = false;
                frames.walking = 0;
                velocity.set(0,0);
                states.idle = false;
            }
            // stop crouch
            else if (j <= 0.3 && states.crouched && (frames.crouched == 18) && !states.attacking) {
                states.idle = true;
            }
            // walk forward
            else if (j <= 0.4 && velocity.x == 0 && !states.crouched && !states.attacking && !states.knockdown && !(states.stunned > 0) && !states.blocking) {
                velocity.add(490, 0);
                states.walking_forward = true;
                states.idle = false;
            }
            // walk backward
            else if (j <= 0.6 && velocity.x == 0 && !states.crouched && !states.attacking && !states.knockdown && !(states.stunned > 0) && !states.blocking) {
                velocity.subtract(490, 0);
                states.walking_backward = true;
                states.idle = false;
            }
            // stop walking
            else if (j <= 0.7 && (velocity.x == 490 || velocity.x == -490) && !states.attacking && !states.knockdown && !(states.stunned > 0) && !states.blocking) {
                velocity.set(0, velocity.y);
                states.walking_backward = false;
                states.walking_forward = false;
                frames.walking = 0;
                states.idle = true;
            }
            // attack
            else if (j <= 0.9 && !states.attacking && !states.knockdown && !(states.stunned > 0) && !states.blocking && !(states.idle && states.crouched) && !(frames.crouched < 18 && states.crouched)) {
                double k = Math.random();
                states.attacking = true;
                if (states.idle && !states.jumping && !(states.walking_forward || states.walking_backward)) {
                    states.idle = false;
                    if (k < 0.15) {
                        states.standingLight = 1;
                    }
                    else if (k < 0.3) {
                        states.standingLight = 2;
                    }
                    else if (k < 0.6) {
                        states.standingMedium = true;
                    }
                    else {
                        states.standingHeavy = true;
                    }
                }
                else if (states.crouched) {
                    if (states.walking_forward && Objects.equals(side, "Left")) {
                        if (k < 0.3) {
                            states.crouchedLight = true;
                        }
                        else if (k < 0.6) {
                            states.crouchedForwardMedium = true;
                        }
                        else {
                            states.crouchedForwardHeavy = true;
                        }
                    } else if (states.walking_backward && Objects.equals(side, "Right")) {
                        if (k < 0.3) {
                            states.crouchedForwardLight = true;
                        }
                        else if (k < 0.6) {
                            states.crouchedForwardMedium = true;
                        }
                        else {
                            states.crouchedForwardHeavy = true;
                        }
                    } else {
                        if (k < 0.3) {
                            states.crouchedLight = true;
                        }
                        else if (k < 0.6) {
                            states.crouchedMedium = true;
                        }
                        else {
                            states.crouchedHeavy = true;
                        }
                    }
                }
                else if (states.jumping) {
                    if (k < 0.15) {
                        states.jumpingLight = 1;
                    }
                    else if (k < 0.3) {
                        states.jumpingLight = 2;
                    }
                    else if (k < 0.6) {
                        states.jumpingMedium = true;
                    }
                    else {
                        states.jumpingHeavy = true;
                    }
                }
                else if (states.walking_forward) {
                    states.idle = false;
                    velocity.set(0,0);
                    if (k < 0.3) {
                        if (Objects.equals(side, "Left")) {
                            states.forwardLight = true;
                        }
                        else {
                            states.backwardLight = true;
                        }
                    }
                    else if (k < 0.6) {
                        if (Objects.equals(side, "Left")) {
                            states.forwardMedium = true;
                        }
                        else {
                            states.backwardMedium = true;
                        }
                    }
                    else {
                        if (Objects.equals(side, "Left")) {
                            states.forwardHeavy = true;
                        }
                        else {
                            states.backwardHeavy = true;
                        }
                    }
                }
                else if (states.walking_backward) {
                    states.idle = false;
                    velocity.set(0,0);
                    if (k < 0.3) {
                        if (Objects.equals(side, "Left")) {
                            states.backwardLight = true;
                        }
                        else {
                            states.forwardLight = true;
                        }
                    }
                    else if (k < 0.6) {
                        if (Objects.equals(side, "Left")) {
                            states.backwardMedium = true;
                        }
                        else {
                            states.forwardMedium = true;
                        }
                    }
                    else {
                        if (Objects.equals(side, "Left")) {
                            states.backwardHeavy = true;
                        }
                        else {
                            states.forwardHeavy = true;
                        }
                    }
                }
                else {
                    states.resetStates();
                    frames.resetFrames();
                }
            }
        }
    }
}
