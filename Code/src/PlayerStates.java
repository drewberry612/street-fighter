public class PlayerStates {
    public boolean jumping;
    public boolean idle;
    public boolean crouched;
    public boolean walking_forward;
    public boolean walking_backward;
    public boolean turning;

    public boolean attacking;
    public int standingLight;
    public boolean standingMedium;
    public boolean standingHeavy;
    public int jumpingLight;
    public boolean jumpingMedium;
    public boolean jumpingHeavy;
    public boolean crouchedLight;
    public boolean crouchedMedium;
    public boolean crouchedHeavy;
    public boolean forwardLight;
    public boolean forwardMedium;
    public boolean forwardHeavy;
    public boolean backwardLight;
    public boolean backwardMedium;
    public boolean backwardHeavy;
    public boolean crouchedForwardLight;
    public boolean crouchedForwardMedium;
    public boolean crouchedForwardHeavy;

    public int stunned; // 1 for light, 2 for heavy, 3 for crouch, 0 for none
    public boolean knockdown;
    public boolean dead;
    public boolean blocking;
    public boolean landedAttack;

    PlayerStates() {
        resetStates();
        idle = true;
    }

    public void resetAttackStates() {
        standingLight = 0;
        standingMedium = false;
        standingHeavy = false;
        attacking = false;
        jumpingLight = 0;
        jumpingMedium = false;
        jumpingHeavy = false;
        crouchedLight = false;
        crouchedMedium = false;
        crouchedHeavy = false;
        forwardLight = false;
        forwardMedium = false;
        forwardHeavy = false;
        backwardLight = false;
        backwardMedium = false;
        backwardHeavy = false;
        crouchedForwardLight = false;
        crouchedForwardMedium = false;
        crouchedForwardHeavy = false;
        landedAttack = false;
    }

    public void resetStates() {
        idle = false;
        jumping = false;
        crouched = false;
        walking_forward = false;
        walking_backward = false;
        turning = false;
        stunned = 0;
        knockdown = false;
        dead = false;
        blocking = false;
        resetAttackStates();
    }
}
