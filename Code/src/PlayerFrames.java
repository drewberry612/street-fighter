public class PlayerFrames {
    public int jumping;
    public int idle;
    public int crouched;
    public int walking;
    public int attacking;
    public int turning;
    public int stun;
    public int knockdown;
    public int death;
    public int block;

    PlayerFrames() {
        resetFrames();
    }

    public void resetAttackFrames() {
        attacking = 0;
    }

    public void resetFrames() {
        idle = 0;
        walking = 0;
        crouched = 0;
        jumping = 0;
        turning = 0;
        stun = 0;
        knockdown = 0;
        death = 0;
        block = 0;
        resetAttackFrames();
    }
}
