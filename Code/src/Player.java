import utilities.SoundManager;
import utilities.Vector;

import java.util.Objects;

import static utilities.SoundManager.*;

public class Player {
    public Player other;
    public String side;
    public PlayerStates states;
    public PlayerFrames frames;
    public int initialY;
    public int health;
    public int radius;
    public int hitboxWidth;
    public int hitboxHeight;
    public int attackHitboxSize;

    public Vector position;
    public Vector velocity;

    public Player(String side) {
        this.side = side;
        states = new PlayerStates();
        frames = new PlayerFrames();
        position = Objects.equals(side, "Left") ? new Vector(80, 380) : new Vector(1080, 380);
        initialY = 380;
        velocity = new Vector(0, 0);
        health = 100;
        radius = 100;
        hitboxWidth = 220;
        hitboxHeight = 320;
        attackHitboxSize = 50;
        frames.resetFrames();
    }

    public void setOther(Player other) {
        this.other = other;
    }

    public void update() {
        position.addScaled(velocity, 0.02);

        if (states.jumping || (states.knockdown && frames.knockdown < 24) || (states.dead && frames.death < 24)) {
            velocity.add(0, 40);
        }

        if (position.y > initialY && states.jumping) {
            states.jumping = false;
            frames.resetFrames();
            states.resetAttackStates();
            position.y = initialY;
            velocity.set(velocity.x, 0);
            if (states.turning) {
                states.turning = false;
                side = Objects.equals(side, "Left") ? "Right" : "Left";
            }
        }

        if (position.y > initialY && (states.knockdown || states.dead)) {
            position.y = initialY;
        }

        // so that players don't go off-screen
        if (position.x <= 20) {
            position.x = 20;
        } else if (position.x >= 1120) {
            position.x = 1120;
        }

        // body collision
        if (!(position.y > other.position.y + (other.radius / 2)) && position.dist(other.position) < (radius + other.radius)) {
            position.x += Objects.equals(side, "Left") ? -10 : 10;
        }

        detectSideSwap();
        adjustFrames();
    }

    protected void adjustFrames() {
        if (states.dead) {
            if (frames.death < 32) {
                frames.death += 1;
            }
            else if (frames.death == 32) {
                if (position.y == initialY) {
                    velocity.set(0,0);
                    frames.death++;
                }
            }
        }
        else if (states.stunned > 0) {
            frames.stun += 1;
            if (states.stunned == 1) {
                if (frames.stun == 12) {
                    if (Objects.equals(side, "Left")) {
                        velocity.add(160, 0);
                    }
                    else {
                        velocity.subtract(160, 0);
                    }
                }
                if (frames.stun == 24) {
                    velocity.set(0, 0);
                    frames.stun = 0;
                    states.resetStates();
                    states.idle = true;
                }
            }
            else if (states.stunned == 2) {
                if (frames.stun == 24) {
                    if (Objects.equals(side, "Left")) {
                        velocity.add(190, 0);
                    }
                    else {
                        velocity.subtract(190, 0);
                    }
                }
                if (frames.stun == 36) {
                    velocity.set(0, 0);
                    frames.stun = 0;
                    states.resetStates();
                    states.idle = true;
                }
            }
            else if (states.stunned == 3) {
                if (frames.stun == 12) {
                    if (Objects.equals(side, "Left")) {
                        velocity.add(160, 0);
                    }
                    else {
                        velocity.subtract(160, 0);
                    }
                }
                if (frames.stun == 24) {
                    velocity.set(0, 0);
                    frames.stun = 0;
                    states.resetStates();
                    states.crouched = true;
                    frames.crouched = 18;
                }
            }
        }
        else if (states.knockdown) {
            if (frames.knockdown < 24) {
                frames.knockdown += 1;
            }
            else if (frames.knockdown == 24) {
                if (position.y == initialY) {
                    velocity.set(0,0);
                    frames.knockdown += 1;
                }
            }
            else {
                frames.knockdown += 1;
            }

            if (frames.knockdown == 72) {
                frames.knockdown = 0;
                states.resetStates();
                states.idle = true;
            }
        }
        else if (states.idle) {
            if (states.crouched) {
                if (frames.crouched == 18) {
                    frames.crouched -= 6;
                }
                else {
                    frames.crouched -= 1;
                    if (frames.crouched == 0) {
                        states.crouched = false;
                    }
                }
            }
            else if (states.turning) {
                frames.turning += 1;
                if (frames.turning == 18) {
                    states.turning = false;
                    frames.turning = 0;
                    side = Objects.equals(side, "Left") ? "Right" : "Left";
                }
            }
            else if (states.jumping) {
                if (states.jumpingLight >= 1) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    // first hit
                    decideHit(6, 12, 300, 120, 55, 6, 1);
                    // second hit
                    decideHit(18, 24, 300, 120, 55, 6, 1);
                    if (frames.attacking == 12 && states.jumpingLight == 1) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                    if (frames.attacking == 24 && states.jumpingLight == 2) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }
                else if (states.jumpingMedium) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 300, 120, 55, 8, 1);
                    if (frames.attacking == 12) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }
                else if (states.jumpingHeavy) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 300, 120, 55, 12, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }

                if (frames.jumping < 36) {
                    frames.jumping += 1;
                }
            }
            else {
                frames.idle += 1;
                if (frames.idle == 30) {
                    frames.idle = 0;
                }
            }
        }
        else if (states.blocking) {
            frames.block += 1;
            if (frames.block == 12) {
                frames.block = 0;
                if (states.crouched) {
                    states.resetStates();
                    states.crouched = true;
                }
                else {
                    states.resetStates();
                    states.idle = true;
                }
            }
        }
        else if (states.crouched) {
            if (frames.crouched < 18) {
                frames.crouched += 1;
            }

            if (frames.crouched == 18 && states.attacking) {
                if (states.crouchedLight) {
                    frames.attacking += 1;
                    if (frames.attacking >= 6 && frames.attacking < 12 && !states.landedAttack) {
                        if (Objects.equals(side, "Left")) {
                            detectHit(300, 55, 6, 1);
                        }
                        else {
                            detectHit(120, 55, 6, 1);
                        }
                    }
                    if (frames.attacking == 18) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }
                else if (states.crouchedMedium) {
                    frames.attacking += 1;
                    if (frames.attacking >= 12 && frames.attacking < 18 && !states.landedAttack) {
                        if (Objects.equals(side, "Left")) {
                            detectHit(300, 55, 8, 1);
                        }
                        else {
                            detectHit(120, 55, 8, 1);
                        }
                    }
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }
                else if (states.crouchedHeavy) {
                    frames.attacking += 1;
                    if (frames.attacking >= 12 && frames.attacking < 18 && !states.landedAttack) {
                        if (Objects.equals(side, "Left")) {
                            detectHit(300, 55, 12, 3);
                        }
                        else {
                            detectHit(120, 55, 12, 3);
                        }
                    }
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }
                else if (states.crouchedForwardLight) {
                    frames.attacking += 1;
                    if (frames.attacking >= 6 && frames.attacking < 12 && !states.landedAttack) {
                        if (Objects.equals(side, "Left")) {
                            detectHit(300, 55, 6, 3);
                        }
                        else {
                            detectHit(120, 55, 6, 3);
                        }
                    }
                    if (frames.attacking == 18) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.walking_forward = false;
                    }
                }
                else if (states.crouchedForwardMedium) {
                    frames.attacking += 1;
                    if (frames.attacking >= 12 && frames.attacking < 18 && !states.landedAttack) {
                        if (Objects.equals(side, "Left")) {
                            detectHit(300, 55, 8, 3);
                        }
                        else {
                            detectHit(120, 55, 8, 3);
                        }
                    }
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.walking_forward = false;
                    }
                }
                else if (states.crouchedForwardHeavy) {
                    frames.attacking += 1;
                    if (frames.attacking >= 12 && frames.attacking < 18 && !states.landedAttack) {
                        if (Objects.equals(side, "Left")) {
                            detectHit(300, 55, 12, 3);
                        }
                        else {
                            detectHit(120, 55, 12, 3);
                        }
                    }
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.walking_forward = false;
                    }
                }
            }

            if (states.turning && frames.crouched <= 18) {
                frames.turning += 1;
                if (frames.turning == 18) {
                    states.turning = false;
                    frames.turning = 0;
                    side = Objects.equals(side, "Left") ? "Right" : "Left";
                }
                frames.crouched -= 1;
            }
        }
        else if (states.walking_forward || states.walking_backward) {
            if (states.jumping) {
                if (states.jumpingLight >= 1) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    // first hit
                    decideHit(6, 12, 300, 120, 55, 6, 1);
                    //second hit
                    decideHit(18, 24, 300, 120, 55, 6, 1);

                    if (frames.attacking == 12 && states.jumpingLight == 1) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                    if (frames.attacking == 24 && states.jumpingLight == 2) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }
                else if (states.jumpingMedium) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 300, 120, 55, 8, 2);
                    if (frames.attacking == 12) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }
                else if (states.jumpingHeavy) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 300, 120, 55, 12, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }

                if (frames.jumping < 36) {
                    frames.jumping += 1;
                }
            }
            else if (states.attacking) {
                if (states.forwardLight) {
                    frames.attacking += 1;
                    decideHit(6, 12, 300, 120, 55, 6, 1);
                    if (frames.attacking == 18) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_forward = false;
                        states.idle = true;
                    }
                }
                else if (states.forwardMedium) {
                    frames.attacking += 1;
                    decideHit(12, 18, 300, 120, 55, 8, 2);
                    if (frames.attacking == 36) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_forward = false;
                        states.idle = true;
                    }
                }
                else if (states.forwardHeavy) {
                    frames.attacking += 1;
                    decideHit(12, 18, 300, 120, 55, 12, 3);
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_forward = false;
                        states.idle = true;
                    }
                }
                else if (states.backwardLight) {
                    frames.attacking += 1;
                    decideHit(12, 18, 300, 120, 55, 6, 1);
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.idle = true;
                    }
                }
                else if (states.backwardMedium) {
                    frames.attacking += 1;
                    decideHit(12, 18, 300, 120, 55, 8, 2);
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.idle = true;
                    }
                }
                else if (states.backwardHeavy) {
                    frames.attacking += 1;
                    decideHit(18, 24, 300, 120, 55, 12, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.idle = true;
                    }
                }
                else {
                    states.resetStates();
                    frames.resetFrames();
                }
            }
            else {
                frames.walking += 1;
                if (frames.walking == 36) {
                    frames.walking = 0;
                }
            }
        }
        else if (states.attacking) {
            if (states.standingLight >= 1) {
                frames.attacking += 1;
                // first hit
                decideHit(6, 12, 290, 100, 55, 6, 1);
                // second hit
                decideHit(24, 30, 300, 110, 55, 6, 1);
                if (frames.attacking == 18 && states.standingLight == 1) {
                    frames.resetFrames();
                    states.resetAttackStates();
                    states.idle = true;
                }
                if (frames.attacking == 12 && states.standingLight == 2) {
                    states.landedAttack = false;
                }
                if (frames.attacking == 42 && states.standingLight == 2) {
                    frames.resetFrames();
                    states.resetAttackStates();
                    states.idle = true;
                }
            }
            else if (states.standingMedium) {
                frames.attacking += 1;
                decideHit(12, 18, 270, 80, 25, 8, 2);
                if (frames.attacking == 30) {
                    frames.resetFrames();
                    states.resetAttackStates();
                    states.idle = true;
                }
            }
            else if (states.standingHeavy) {
                frames.attacking += 1;
                decideHit(12, 18, 280, 70, 55, 12, 3);
                if (frames.attacking == 30) {
                    frames.resetFrames();
                    states.resetAttackStates();
                    states.idle = true;
                }
            }
        }
    }

    protected void decideHit(int lower, int upper, int xShiftLeft, int xShiftRight, int yShift, int damage, int stun) {
        if (frames.attacking >= lower && frames.attacking < upper && !states.landedAttack) {
            if (Objects.equals(side, "Left") && !other.states.knockdown) {
                detectHit(xShiftLeft, yShift, damage, stun);
            }
            else if (Objects.equals(side, "Right") && !other.states.knockdown) {
                detectHit(xShiftRight, yShift, damage, stun);
            }
        }
    }

    protected void detectHit(int xShift, int yShift, int damage, int stun) {
        int otherY = (int) other.position.y;
        if (other.states.crouched) {
            otherY += 100;
        }
        if (Objects.equals(side, "Left")) {
            for (int i = (int) (position.x + xShift); i<position.x + xShift + attackHitboxSize; i++) {
                for (int j = (int) (position.y + yShift); j<position.y + yShift + attackHitboxSize; j++) {
                    if (other.position.x <= i && otherY <= j && otherY + hitboxHeight >= j) {
                        states.landedAttack = true;
                        int num = (int) (Math.random() * 1);
                        SoundManager.playClip(HIT.get(num));
                        if (other.states.walking_forward && !other.states.attacking && !other.states.jumping) {
                            other.health -= 1;
                            other.states.blocking = true;
                            other.states.walking_forward = false;
                            other.velocity.set(0,0);
                        } else {
                            other.health -= damage;
                            other.velocity.set(0,0);
                            if (stun == 3) {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                other.states.knockdown = true;
                                SoundManager.playClip(KNOCKDOWN);
                                other.velocity.add(480, -580);
                            } else if (other.states.crouched) {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                other.states.stunned = 3;
                                other.states.crouched = true;
                                num = (int) (Math.random() * 3);
                                SoundManager.playClip(STUN.get(num));
                                other.velocity.add(320, 0);
                            } else if (other.states.jumping) {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                other.states.knockdown = true;
                                SoundManager.playClip(KNOCKDOWN);
                                other.velocity.add(480, -80);
                            } else {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                switch (stun) {
                                    case 1 -> {
                                        other.states.stunned = 1;
                                        num = (int) (Math.random() * 3);
                                        SoundManager.playClip(STUN.get(num));
                                        other.velocity.add(320, 0);
                                    }
                                    case 2 -> {
                                        other.states.stunned = 2;
                                        SoundManager.playClip(HEAVYSTUN);
                                        other.velocity.add(380, 0);
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                if (states.landedAttack) {
                    break;
                }
            }
        }
        else {
            for (int i = (int) (position.x - xShift); i<position.x - xShift + attackHitboxSize; i++) {
                for (int j = (int) (position.y + yShift); j<position.y + yShift + attackHitboxSize; j++) {
                    if (other.position.x + hitboxWidth >= i && other.position.y <= j && other.position.y + hitboxHeight >= j) {
                        states.landedAttack = true;
                        int num = (int) (Math.random() * 1);
                        SoundManager.playClip(HIT.get(num));
                        if (other.states.walking_backward && !other.states.attacking && !other.states.jumping) {
                            other.health -= 1;
                            other.states.blocking = true;
                            other.states.walking_backward = false;
                            other.velocity.set(0,0);
                        } else {
                            other.health -= damage;
                            other.velocity.set(0,0);
                            if (stun == 3) {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                other.states.knockdown = true;
                                SoundManager.playClip(KNOCKDOWN);
                                other.velocity.add(-480, -580);
                            } else if (other.states.crouched) {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                other.states.stunned = 3;
                                other.states.crouched = true;
                                num = (int) (Math.random() * 3);
                                SoundManager.playClip(STUN.get(num));
                                other.velocity.add(-320, 0);
                            } else if (other.states.jumping) {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                other.states.knockdown = true;
                                SoundManager.playClip(KNOCKDOWN);
                                other.velocity.add(-480, -80);
                            } else {
                                other.states.resetStates();
                                other.frames.resetFrames();
                                switch (stun) {
                                    case 1 -> {
                                        other.states.stunned = 1;
                                        num = (int) (Math.random() * 3);
                                        SoundManager.playClip(STUN.get(num));
                                        other.velocity.add(-320, 0);
                                    }
                                    case 2 -> {
                                        other.states.stunned = 2;
                                        SoundManager.playClip(HEAVYSTUN);
                                        other.velocity.add(-380, 0);
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                if (states.landedAttack) {
                    break;
                }
            }
        }
    }

    protected void detectSideSwap() {
        if (Objects.equals(side, "Left")) {
            if (position.x > other.position.x) {
                states.turning = true;
            }
        } else {
            if (position.x < other.position.x) {
                states.turning = true;
            }
        }
    }
}
