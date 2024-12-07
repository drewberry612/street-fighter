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
    public int facing; // 1 for right, -1 for left
    public Vector position;
    public Vector velocity;

    private static final int ATTACK_X_OFFSET  = 1 ;
    public Player(String side) {
        this.side = side;
        this.facing = Objects.equals(side, "Left") ? 1 : -1; // Initialize facing direction
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
        // Update current player's position based on velocity and delta time
        position.addScaled(velocity, 0.02); // Assuming a delta time of 0.02 seconds

        // Apply gravity if jumping, knocked down, or dead within certain frames
        if (states.jumping || (states.knockdown && frames.knockdown < 24) || (states.dead && frames.death < 24)) {
            velocity.add(0, 40); // Gravity effect
//            System.out.println(side + " Player: Applied gravity.");
        }

        // Handle landing after jumping
        if (position.y > initialY && states.jumping) {
            states.jumping = false;
            frames.resetFrames();
            states.resetAttackStates();
            position.y = initialY;
            velocity.set(velocity.x, 0);
//            System.out.println(side + " Player: Landed from jump.");
        }

        // Handle landing after knockdown or death
        if (position.y > initialY && (states.knockdown || states.dead)) {
            position.y = initialY;
//            System.out.println(side + " Player: Landed after knockdown/death.");
        }

        // Prevent players from moving off-screen
        if (position.x <= 20) {
            position.x = 20;
//            System.out.println(side + " Player: Reached left screen boundary.");
        } else if (position.x >= 1120) {
            position.x = 1120;
//            System.out.println(side + " Player: Reached right screen boundary.");
        }

        // Body collision detection and mutual position adjustment
        if (!(position.y > other.position.y + (other.radius / 2)) && position.dist(other.position) < (radius + other.radius)) {
            int pushAmount = 10; // Total push to separate players
            if (position.x < other.position.x) {
                // Current player is to the left of the other player
                position.x -= pushAmount / 2;
                other.position.x += pushAmount / 2;
//                System.out.println(side + " Player: Collided with opponent. Pushing both players apart to the sides.");
            } else {
                // Current player is to the right of the other player
                position.x += pushAmount / 2;
                other.position.x -= pushAmount / 2;
//                System.out.println(side + " Player: Collided with opponent. Pushing both players apart to the sides.");
            }

            // Ensure that both players are still within screen bounds after adjustment
            if (position.x < 20) position.x = 20;
            if (position.x > 1120) position.x = 1120;
            if (other.position.x < 20) other.position.x = 20;
            if (other.position.x > 1120) other.position.x = 1120;
        }

        // Detect and update side (Left or Right) based on current positions
        detectSideSwap();

        // Adjust frames based on current state
        adjustFrames();
    }


    protected void adjustFrames() {
        if (states.dead) {
            if (frames.death < 32) {
                frames.death += 1;
            } else if (frames.death == 32) {
                if (position.y == initialY) {
                    velocity.set(0, 0);
                    frames.death++;
                }
            }
        } else if (states.stunned > 0) {
            frames.stun += 1;
            if (states.stunned == 1) {
                if (frames.stun == 12) {
                    if (Objects.equals(side, "Left")) {
                        velocity.add(160, 0);
                    } else {
                        velocity.subtract(160, 0);
                    }
                }
                if (frames.stun == 24) {
                    velocity.set(0, 0);
                    frames.stun = 0;
                    states.resetStates();
                    states.idle = true;
                }
            } else if (states.stunned == 2) {
                if (frames.stun == 24) {
                    if (Objects.equals(side, "Left")) {
                        velocity.add(190, 0);
                    } else {
                        velocity.subtract(190, 0);
                    }
                }
                if (frames.stun == 36) {
                    velocity.set(0, 0);
                    frames.stun = 0;
                    states.resetStates();
                    states.idle = true;
                }
            } else if (states.stunned == 3) {
                if (frames.stun == 12) {
                    if (Objects.equals(side, "Left")) {
                        velocity.add(160, 0);
                    } else {
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
        } else if (states.knockdown) {
            if (frames.knockdown < 24) {
                frames.knockdown += 1;
            } else if (frames.knockdown == 24) {
                if (position.y == initialY) {
                    velocity.set(0, 0);
                    frames.knockdown += 1;
                }
            } else {
                frames.knockdown += 1;
            }

            if (frames.knockdown == 72) {
                frames.knockdown = 0;
                states.resetStates();
                states.idle = true;
            }
        } else if (states.idle) {
            if (states.crouched) {
                if (frames.crouched == 18) {
                    frames.crouched -= 6;
                } else {
                    frames.crouched -= 1;
                    if (frames.crouched == 0) {
                        states.crouched = false;
                    }
                }
            } else if (states.jumping) {
                if (states.jumpingLight >= 1) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    // First hit
                    decideHit(6, 12, 200, 55, 6, 1);
                    // Second hit (if applicable)
                    if (states.jumpingLight == 2) {
                        decideHit(18, 24, 200, 55, 6, 1);
                    }
                    if (frames.attacking == 12 && states.jumpingLight == 1) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                    if (frames.attacking == 24 && states.jumpingLight == 2) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                } else if (states.jumpingMedium) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 200, 55, 8, 1);
                    if (frames.attacking == 12) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                } else if (states.jumpingHeavy) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 200, 55, 12, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }

                if (frames.jumping < 36) {
                    frames.jumping += 1;
                }
            } else {
                frames.idle += 1;
                if (frames.idle == 30) {
                    frames.idle = 0;
                }
            }
        } else if (states.blocking) {
            frames.block += 1;
            if (frames.block == 12) {
                frames.block = 0;
                if (states.crouched) {
                    states.resetStates();
                    states.crouched = true;
                } else {
                    states.resetStates();
                    states.idle = true;
                }
            }
        } else if (states.crouched) {
            if (frames.crouched < 18) {
                frames.crouched += 1;
            }

            if (frames.crouched == 18 && states.attacking) {
                if (states.crouchedLight) {
                    frames.attacking += 1;
                    decideHit(6, 12, 200, 55, 6, 1);
                    if (frames.attacking == 18) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                } else if (states.crouchedMedium) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 8, 1);
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                } else if (states.crouchedHeavy) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 12, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                } else if (states.crouchedForwardLight) {
                    frames.attacking += 1;
                    decideHit(6, 12, 200, 55, 6, 3);
                    if (frames.attacking == 18) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.walking_forward = false;
                    }
                } else if (states.crouchedForwardMedium) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 8, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.walking_forward = false;
                    }
                } else if (states.crouchedForwardHeavy) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 12, 3);
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
                    // Side will be updated in detectSideSwap()
                }
                frames.crouched -= 1;
            }
        } else if (states.walking_forward || states.walking_backward) {
            if (states.jumping) {
                if (states.jumpingLight >= 1) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    // First hit
                    decideHit(6, 12, 200, 55, 6, 1);
                    // Second hit (if applicable)
                    if (states.jumpingLight == 2) {
                        decideHit(18, 24, 200, 55, 6, 1);
                    }

                    if (frames.attacking == 12 && states.jumpingLight == 1) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                    if (frames.attacking == 24 && states.jumpingLight == 2) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                } else if (states.jumpingMedium) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 200, 55, 8, 2);
                    if (frames.attacking == 12) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                } else if (states.jumpingHeavy) {
                    frames.attacking += 1;
                    frames.jumping += 1;
                    decideHit(6, 12, 200, 55, 12, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                    }
                }

                if (frames.jumping < 36) {
                    frames.jumping += 1;
                }
            } else if (states.attacking) {
                if (states.forwardLight) {
                    frames.attacking += 1;
                    decideHit(6, 12, 200, 55, 6, 1);
                    if (frames.attacking == 18) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_forward = false;
                        states.idle = true;
                    }
                } else if (states.forwardMedium) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 8, 2);
                    if (frames.attacking == 36) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_forward = false;
                        states.idle = true;
                    }
                } else if (states.forwardHeavy) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 12, 3);
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_forward = false;
                        states.idle = true;
                    }
                } else if (states.backwardLight) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 6, 1);
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.idle = true;
                    }
                } else if (states.backwardMedium) {
                    frames.attacking += 1;
                    decideHit(12, 18, 200, 55, 8, 2);
                    if (frames.attacking == 24) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.idle = true;
                    }
                } else if (states.backwardHeavy) {
                    frames.attacking += 1;
                    decideHit(18, 24, 200, 55, 12, 3);
                    if (frames.attacking == 30) {
                        frames.resetAttackFrames();
                        states.resetAttackStates();
                        states.walking_backward = false;
                        states.idle = true;
                    }
                } else {
                    states.resetStates();
                    frames.resetFrames();
                }
            } else {
                frames.walking += 1;
                if (frames.walking == 36) {
                    frames.walking = 0;
                }
            }
        } else if (states.attacking) {
            if (states.standingLight >= 1) {
                frames.attacking += 1;
                // First hit
                decideHit(6, 12, 200, 55, 6, 1);
                // Second hit (if applicable)
                if (states.standingLight == 2) {
                    decideHit(24, 30, 200, 55, 6, 1);
                }
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
            } else if (states.standingMedium) {
                frames.attacking += 1;
                decideHit(12, 18, 200, 55, 8, 2);
                if (frames.attacking == 30) {
                    frames.resetFrames();
                    states.resetAttackStates();
                    states.idle = true;
                }
            } else if (states.standingHeavy) {
                frames.attacking += 1;
                decideHit(12, 18, 200, 55, 12, 3);
                if (frames.attacking == 30) {
                    frames.resetFrames();
                    states.resetAttackStates();
                    states.idle = true;
                }
            }
        }
    }


    protected void decideHit(int lower, int upper, int xShift, int yShift, int damage, int stun) {
        if (frames.attacking >= lower && frames.attacking < upper && !states.landedAttack) {
            if (!other.states.knockdown) {
                detectHit(xShift, yShift, damage, stun);
            }
        }
    }
    protected void detectHit(int xShift, int yShift, int damage, int stun) {
        int otherY = (int) other.position.y;
        int otherHeight = other.hitboxHeight;
        if (other.states.crouched) {
            otherY += 100; // Adjust Y for crouching opponent
            otherHeight -= 100; // Reduce height for crouching opponent
        }

        // Adjust xShift based on the player's facing direction and apply the offset
        int xShiftAdjusted = xShift * facing + (facing == 1 ? -ATTACK_X_OFFSET : ATTACK_X_OFFSET);
//        System.out.println("Attack X Shift Adjusted: " + xShiftAdjusted);

        // Calculate attack hitbox position
        int attackXStart = (int) (position.x + xShiftAdjusted);
        int attackXEnd = attackXStart + attackHitboxSize;
//        System.out.println("Attack Hitbox X Range: " + attackXStart + " to " + attackXEnd);

        int attackYStart = (int) (position.y + yShift);
        int attackYEnd = attackYStart + attackHitboxSize;
//        System.out.println("Attack Hitbox Y Range: " + attackYStart + " to " + attackYEnd);

        int otherXStart = (int) other.position.x;
        // Define opponent hitbox adjustment based on facing direction
        if (facing == -1) {
            otherXStart = (int) other.position.x + 30;
        } else {
            otherXStart = (int) other.position.x - 30;
        }

        int otherXEnd = otherXStart + other.hitboxWidth;
        int otherYEnd = otherY + otherHeight;

        // Calculate horizontal distance between players
        double horizontalDistance = Math.abs(this.position.x - other.position.x);
//        System.out.println("Horizontal Distance Between Players: " + horizontalDistance);

        // Define distance threshold
        int distanceThreshold = 300;

        // Check for collision and distance condition
        boolean xOverlap = attackXStart < otherXEnd && attackXEnd > otherXStart;
        boolean yOverlap = attackYStart < otherYEnd && attackYEnd > otherY;
        boolean withinDistance = horizontalDistance < distanceThreshold;

//        System.out.println("xOverlap: " + xOverlap + ", yOverlap: " + yOverlap + ", withinDistance: " + withinDistance);
//        System.out.println("otherXStart: " + otherXStart + " attackXStart: " + attackXStart);
//        System.out.println("otherXEnd: " + otherXEnd + " attackXEnd: " + attackXEnd);

        if ((withinDistance && yOverlap) ) {
//            System.out.println("Hit Detected!");
            states.landedAttack = true;
            handleAttack(damage, stun);
        }
    }




    private void handleAttack(int damage, int stun) {
        if (HIT.isEmpty()) {
            // No hit sounds available; optionally log or handle this case
            return;
        }
        int num = (int) (Math.random() * HIT.size());
        SoundManager.playClip(HIT.get(num));

        // Determine if opponent is holding away from attacker
        boolean isHoldingAway = ((other.position.x > position.x) && other.states.walking_backward) ||
                                ((other.position.x < position.x) && other.states.walking_forward);

        boolean isBlocking = isHoldingAway && !other.states.attacking && !other.states.jumping;

        if (isBlocking) {
            // Handle blocking (chip damage)
            other.health -= 1;
            other.states.blocking = true;
            other.velocity.set(0, 0);
        } else {
            // Handle taking damage
            other.health -= damage;
            other.velocity.set(0, 0);
            applyStunOrKnockdown(stun);
        }
    }

    private void applyStunOrKnockdown(int stun) {
        if (stun == 3) {
            other.states.resetStates();
            other.frames.resetFrames();
            other.states.knockdown = true;
            SoundManager.playClip(KNOCKDOWN);
            other.velocity.add(facing * 480, -580);
        } else if (other.states.crouched) {
            other.states.resetStates();
            other.frames.resetFrames();
            other.states.stunned = 3;
            other.states.crouched = true;
            if (STUN.isEmpty()) {
                // Handle empty STUN list
                return;
            }
            int num = (int) (Math.random() * STUN.size());
            SoundManager.playClip(STUN.get(num));
            other.velocity.add(facing * 320, 0);
        } else if (other.states.jumping) {
            other.states.resetStates();
            other.frames.resetFrames();
            other.states.knockdown = true;
            SoundManager.playClip(KNOCKDOWN);
            other.velocity.add(facing * 480, -80);
        } else {
            other.states.resetStates();
            other.frames.resetFrames();
            switch (stun) {
                case 1 -> {
                    other.states.stunned = 1;
                    if (STUN.isEmpty()) {
                        // Handle empty STUN list
                        break;
                    }
                    int num = (int) (Math.random() * STUN.size());
                    SoundManager.playClip(STUN.get(num));
                    other.velocity.add(facing * 320, 0);
                }
                case 2 -> {
                    other.states.stunned = 2;
                    SoundManager.playClip(HEAVYSTUN);
                    other.velocity.add(facing * 380, 0);
                }
            }
        }
    }

    protected void detectSideSwap() {
        if (position.x < other.position.x) {
            side = "Left";
            facing = 1; // Facing right
        } else {
            side = "Right";
            facing = -1; // Facing left
        }
    }

    
}
