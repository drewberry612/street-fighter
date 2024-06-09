package utilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Constants {
    public static final int WINDOW_HEIGHT = 740;
    public static final int WINDOW_WIDTH = 1400;
    public static final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

    public static final int[] PLAYER1_DEFAULT = {KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K};
    public static final int[] PLAYER2_DEFAULT = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6};

    public static Image LOGO;
    public static Image MENU;
    public static ArrayList<Image> BACKGROUND_IMAGES = new ArrayList<>();
    public static ArrayList<BufferedImage> FIGHTER_SHEETS = new ArrayList<>();

    //{x,y,width,height}
    public static final int[][] RYU_SPRITE = {
            /*IDLE*/{0, 0, 90, 120}, {90, 0, 90, 120}, {180, 0, 90, 120}, {270, 0, 90, 120}, {360, 0, 90, 120},
            /*TURN*/{470, 0, 75, 120}, {540, 0, 85, 120}, {612, 0, 85, 120},
            /*FORWARDWALK*/{0, 120, 75, 120}, {75, 120, 80, 120}, {160, 120, 80, 120}, {245, 120, 80, 120}, {340, 120, 80, 120}, {415, 120, 80, 120},
            /*BACKWARDWALK*/{540, 120, 80, 120}, {625, 120, 80, 120}, {710, 120, 80, 120}, {795, 120, 80, 120}, {880, 120, 80, 120}, {975, 120, 80, 120},
            /*STANDINGLIGHT*/{5, 260, 90, 120}, {95, 260, 110, 120}, {235, 260, 90, 120}, {325, 260, 100, 120}, {425, 260, 120, 120},
            /*STANDINGMEDIUM*/{590, 260, 90, 120}, {680, 260, 90, 120}, {770, 260, 130, 120},
            /*STANDINGHEAVY*/{5, 390, 90, 120}, {95, 390, 110, 120}, {210, 390, 140, 120}, {345, 390, 120, 120}, {470, 390, 110, 120},
            /*IDLEJUMP*/{85, 820, 80, 120}, {156, 800, 80, 120}, {234, 795, 80, 120}, {315, 805, 80, 120}, {385, 800, 79, 120}, {455, 820, 80, 120},
            /*MOVINGJUMP*/{575, 820, 90, 120}, {650, 810, 90, 120}, {730, 810, 130, 40}, {860, 780, 60, 140}, {925, 810, 130, 40}, {1055, 800, 90, 120}, {1140, 810, 80, 120},
            /*CROUCH*/{22, 1188, 90, 120}, {108, 1188, 89, 120}, {190, 1188, 90, 120},
            /*CROUCHTURN*/{320, 1188, 75, 120}, {390, 1188, 75, 120}, {460, 1188, 75, 120},
            /*JUMPINGLIGHT*/{5, 940, 90, 120}, {95, 940, 110, 120}, {295, 940, 100, 120}, {385, 940, 120, 120},
            /*JUMPINGMEDIUM*/{510, 940, 80, 120}, {580, 940, 110, 120},
            /*JUMPINGHEAVY*/{730, 940, 80, 120}, {820, 940, 80, 120}, {900, 940, 110, 120}, {1020, 940, 80, 120}, {1100, 940, 80, 120},
            /*CROUCHEDLIGHT*/{10, 1300, 90, 120}, {110, 1300, 110, 120},
            /*CROUCHEDMEDIUM*/{250, 1300, 90, 120}, {350, 1300, 90, 120}, {440, 1300, 110, 120},
            /*CROUCHEDHEAVY*/{580, 1300, 90, 120}, {660, 1300, 90, 120}, {760, 1280, 90, 140},
            /*FORWARDLIGHT*/{0, 500, 90, 120}, {95, 500, 90, 120},
            /*FORWARDMEDIUM*/{230, 500, 90, 120}, {310, 500, 90, 120}, {410, 500, 100, 120}, {500, 500, 90, 120},
            /*FORWARDHEAVY*/{750, 500, 90, 120}, {848, 500, 90, 120}, {940, 500, 90, 120},
            /*BACKWARDLIGHT*/{0, 640, 90, 120}, {75, 640, 90, 120}, {160, 640, 90, 120},
            /*BACKWARDMEDIUM*/{300, 640, 90, 120}, {390, 640, 90, 120}, {480, 640, 90, 120},
            /*BACKWARDHEAVY*/{610, 640, 90, 120}, {700, 640, 90, 120}, {780, 620, 90, 140}, {870, 640, 100, 120}, {980, 640, 90, 120},
            /*CROUCHEDFORWARDLIGHT*/{880, 1300, 90, 120}, {990, 1300, 120, 120},
            /*CROUCHEDFORWARDMEDIUM*/{20, 1410, 90, 120}, {125, 1410, 110, 120}, {240, 1410, 155, 120},
            /*CROUCHEDFORWARDHEAVY*/{430, 1410, 90, 120}, {530, 1410, 140, 120}, {680, 1410, 90, 120}, {770, 1410, 100, 120}, {875, 1410, 90, 120},
            /*LIGHTSTUN*/{390, 2080, 90, 120}, {460, 2080, 90, 120},
            /*HEAVYSTUN*/{200, 2080, 90, 120}, {295, 2080, 90, 120}, {640, 2320, 90, 120},
            /*CROUCHEDSTUN*/{220, 2320, 90, 120}, {295, 2320, 90, 120},
            /*KNOCKDOWN*/{500, 2200, 90, 120}, {350, 2200, 140, 120}, {680, 2200, 140, 120}, {835, 2200, 140, 120},
            {1020, 2080, 120, 120}, {1130, 2080, 90, 120}, {0, 2190, 90, 140}, {100, 2200, 90, 120}, {190, 2200, 90, 120},
            /*DEATH*/{980, 2200, 140, 120},
            /*BLOCK*/{420, 2320, 90, 120}, {510, 2320, 90, 120},
            /*CROUCHEDBLOCK*/{5, 2320, 90, 120}, {95, 2320, 90, 120}};

    public static final int[][] KEN_SPRITE = {
            /*IDLE*/{0, 10, 80, 120}, {75, 10, 70, 120}, {144, 10, 70, 120}, {210, 10, 70, 120}, {276, 10, 70, 120},
            /*TURN*/{440, 10, 70, 120}, {510, 10, 70, 120}, {580, 10, 70, 120},
            /*FORWARDWALK*/{0, 145, 75, 120}, {70, 145, 75, 120}, {140, 145, 75, 120}, {210, 145, 75, 120}, {280, 145, 70, 120}, {345, 145, 75, 120},
            /*BACKWARDWALK*/{435, 145, 75, 120}, {510, 145, 72, 120}, {577, 145, 72, 120}, {642, 145, 72, 120}, {708, 145, 72, 120}, {774, 145, 72, 120},
            /*STANDINGLIGHT*/{0, 280, 85, 120}, {80, 280, 100, 120}, {270, 280, 90, 120}, {360, 280, 90, 120}, {450, 280, 110, 120},
            /*STANDINGMEDIUM*/{0, 410, 80, 120}, {80, 410, 75, 120}, {155, 410, 120, 120},
            /*STANDINGHEAVY*/{450, 410, 80, 120}, {530, 410, 100, 120}, {630, 410, 130, 120}, {760, 410, 110, 120}, {870, 410, 100, 120},
            /*IDLEJUMP*/{75, 1580, 60, 120}, {140, 1540, 60, 120}, {200, 1520, 60, 120}, {255, 1520, 60, 120}, {315, 1520, 60, 120}, {370, 1560, 60, 120},
            /*MOVINGJUMP*/{71, 2240, 70, 120}, {140, 2180, 70, 120}, {208, 2200, 115, 50}, {320, 2150, 60, 140}, {385, 2190, 125, 50}, {515, 2180, 70, 120}, {586, 2190, 70, 120},
            /*CROUCH*/{0, 1110, 75, 120}, {70, 1110, 70, 120}, {135, 1110, 75, 120},
            /*CROUCHTURN*/{250, 1110, 60, 120}, {310, 1110, 60, 120}, {370, 1110, 60, 120},
            /*JUMPINGLIGHT*/{135, 1700, 65, 140}, {195, 1700, 90, 140}, {575, 1700, 83, 140}, {655, 1700, 90, 140},
            /*JUMPINGMEDIUM*/{135, 1880, 65, 120}, {200, 1880, 80, 120},
            /*JUMPINGHEAVY*/{135, 2020, 80, 120}, {215, 2020, 65, 120}, {275, 2020, 105, 120}, {375, 2020, 70, 120}, {445, 2020, 70, 120},
            /*CROUCHEDLIGHT*/{0, 1280, 90, 120}, {90, 1280, 100, 120},
            /*CROUCHEDMEDIUM*/{300, 1280, 80, 120}, {378, 1280, 80, 120}, {455, 1280, 100, 120},
            /*CROUCHEDHEAVY*/{730, 1280, 80, 120}, {810, 1280, 80, 120}, {888, 1250, 70, 140},
            /*FORWARDLIGHT*/{0, 550, 80, 120}, {77, 550, 80, 120},
            /*FORWARDMEDIUM*/{250, 550, 80, 120}, {330, 550, 65, 120}, {390, 550, 100, 120}, {485, 550, 75, 120},
            /*FORWARDHEAVY*/{820, 550, 80, 120}, {900, 550, 90, 120}, {990, 550, 90, 120},
            /*BACKWARDLIGHT*/{0, 710, 80, 120}, {77, 710, 60, 120}, {135, 710, 90, 120},
            /*BACKWARDMEDIUM*/{390, 710, 90, 120}, {477, 710, 75, 120}, {548, 710, 65, 120},
            /*BACKWARDHEAVY*/{810, 710, 70, 120}, {880, 710, 65, 120}, {939, 700, 65, 140}, {1000, 710, 100, 120}, {1097, 710, 64, 120},
            /*CROUCHEDFORWARDLIGHT*/{0, 1380, 90, 120}, {90, 1380, 120, 120},
            /*CROUCHEDFORWARDMEDIUM*/{320, 1380, 90, 120}, {405, 1380, 110, 120}, {510, 1380, 155, 120},
            /*CROUCHEDFORWARDHEAVY*/{880, 1380, 70, 120}, {950, 1380, 130, 120}, {1075, 1380, 70, 120}, {1145, 1380, 70, 120}, {1220, 1380, 90, 120},
            /*LIGHTSTUN*/{95, 3230, 90, 120}, {180, 3230, 90, 120},
            /*HEAVYSTUN*/{285, 3230, 90, 120}, {377, 3230, 90, 120}, {470, 3230, 90, 120},
            /*CROUCHEDSTUN*/{105, 3370, 90, 120}, {195, 3370, 90, 120},
            /*KNOCKDOWN*/{0, 3520, 90, 120}, {90, 3520, 140, 120}, {230, 3520, 120, 120}, {350, 3520, 130, 120},
            {660, 3520, 100, 120}, {760, 3520, 70, 120}, {825, 3520, 60, 120}, {880, 3520, 90, 120}, {970, 3520, 90, 120},
            /*DEATH*/{480, 3520, 140, 120},
            /*BLOCK*/{0, 3090, 80, 120}, {79, 3090, 80, 120},
            /*CROUCHEDBLOCK*/{250, 3090, 80, 120}, {328, 3090, 70, 120}};


    // {width, height, x, y, width, height}
    public static final int[][] RYU_LEFT = {
            /*IDLE 0-4*/{300, 400, 0, 0, 300, 400}, {300, 400, 0, 0, 300, 400}, {300, 400, 0, 0, 300, 400}, {300, 400, 0, 0, 300, 400}, {300, 400, 0, 0, 300, 400},
            /*TURN 5-7*/{260, 400, -10, 0, 260, 400}, {300, 400, -10, 0, 300, 400}, {300, 400, -20, 0, 300, 400},
            /*FORWARDWALK 8-13*/{280, 410, 0, 4, 280, 410}, {280, 410, 0, 4, 280, 410}, {280, 410, 0, 4, 280, 410}, {280, 410, 0, 4, 280, 410}, {280, 410, 0, 4, 280, 410}, {280, 410, 0, 4, 280, 410},
            /*BACKWARDWALK 14-19*/{280, 410, 0, 6, 280, 410}, {280, 410, 0, 6, 280, 410}, {280, 410, 0, 6, 280, 410}, {280, 410, 0, 6, 280, 410}, {280, 410, 0, 6, 280, 410}, {280, 410, 0, 6, 280, 410},
            /*STANDINGLIGHT 20-24*/{320, 410, -30, -3, 320, 410}, {380, 410, -30, -3, 380, 410}, {320, 410, -30, -3, 320, 410}, {320, 410, -30, -3, 320, 410}, {390, 410, -30, -3, 390, 410},
            /*STANDINGMEDIUM 25-27*/{320, 410, -20, -5, 320, 410}, {320, 410, -30, 10, 320, 410}, {400, 410, -75, 10, 400, 410},
            /*STANDINGHEAVY 28-32*/{320, 410, -20, 5, 320, 410}, {320, 410, 0, 5, 320, 410}, {380, 410, 0, 5, 380, 410}, {380, 410, -20, 5, 380, 410}, {380, 410, -20, 5, 380, 410},
            /*IDLEJUMP 33-38*/{290, 400, 0, -10, 290, 400}, {290, 400, 0, -10, 290, 400}, {290, 400, 0, -10, 290, 400}, {290, 400, 0, -10, 290, 400}, {290, 400, 0, -10, 290, 400}, {290, 400, 0, -10, 290, 400},
            /*MOVINGJUMP 39-45*/{300, 400, 0, 0, 300, 400}, {300, 400, 0, 0, 300, 400}, {400, 160, -100, 0, 400, 160}, {240, 480, 0, -80, 200, 500}, {400, 160, -40, 0, 400, 160}, {300, 400, 0, 0, 300, 400}, {300, 400, 0, 0, 300, 400},
            /*CROUCH 46-48*/{300, 400, -10, -12, 300, 400}, {300, 400, -10, -12, 300, 400}, {300, 400, -10, -12, 300, 400},
            /*CROUCHTURN 49-51*/{260, 400, -40, -10, 260, 400}, {260, 400, -40, -10, 260, 400}, {260, 400, -40, -10, 260, 400},
            /*JUMPINGLIGHT 52-55*/{320, 410, -40, -33, 320, 410}, {380, 410, 20, -33, 380, 410}, {320, 410, -40, -33, 320, 410}, {390, 410, -15, -63, 390, 410},
            /*JUMPINGMEDIUM 56-57*/{310, 410, -20, -30, 310, 410}, {380, 410, -40, -30, 380, 410},
            /*JUMPINGHEAVY 58-62*/{310, 410, 0, -30, 310, 410}, {310, 410, 20, -30, 310, 410}, {390, 410, 30, -30, 390, 410}, {310, 410, 40, -30, 310, 410}, {310, 410, 30, -30, 310, 410},
            /*CROUCHEDLIGHT 63-64*/{300, 400, -20, -5, 300, 400}, {370, 400, 0, -5, 370, 400},
            /*CROUCHEDMEDIUM 65-67*/{300, 400, -20, -5, 300, 400}, {300, 400, 0, -5, 300, 400}, {370, 400, 0, -5, 370, 400},
            /*CROUCHEDHEAVY 68-70*/{300, 400, 20, -5, 300, 400}, {300, 400, 30, -25, 300, 400}, {300, 450, 32, -60, 300, 450},
            /*FORWARDLIGHT 71-72*/{300, 400, -30, -30, 300, 400}, {300, 400, 15, -30, 300, 400},
            /*FORWARDMEDIUM 73-76*/{300, 400, 10, -30, 300, 400}, {300, 400, -5, -30, 300, 400}, {300, 400, 27, -30, 300, 400}, {300, 400, -20, -28, 300, 400},
            /*FORWARDHEAVY 77-79*/{300, 400, -5, -30, 300, 400}, {300, 400, 30, -30, 300, 400}, {300, 400, 0, -30, 300, 400},
            /*BACKWARDLIGHT 80-82*/{300, 400, 10, -22, 300, 400}, {300, 400, 20, -22, 300, 400}, {300, 400, 90, -22, 300, 400},
            /*BACKWARDMEDIUM 83-85*/{300, 400, 20, -22, 300, 400}, {300, 400, 50, -22, 300, 400}, {300, 400, 115, -22, 300, 400},
            /*BACKWARDHEAVY 86-90*/{300, 400, -30, -22, 300, 400}, {300, 400, 20, -22, 300, 400}, {300, 450, 40, -78, 300, 450}, {320, 400, 100, -22, 320, 400}, {300, 400, 40, -22, 300, 400},
            /*CROUCHEDFORWARDLIGHT 91-92*/{300, 400, 0, -4, 300, 400}, {360, 400, 40, -4, 360, 400},
            /*CROUCHEDFORWARDMEDIUM 93-95*/{300, 400, 0, 20, 300, 400}, {370, 400, 50, 20, 370, 400}, {460, 400, 20, 20, 460, 400},
            /*CROUCHEDFORWARDHEAVY 96-100*/{300, 400, -10, 20, 300, 400}, {440, 400, 30, 20, 440, 400}, {300, 400, 50, 20, 300, 400}, {320, 400, 20, 20, 320, 400}, {300, 400, 50, 20, 300, 400},
            /*LIGHTSTUN 101-102*/{300, 400, 55, 18, 300, 400}, {300, 400, 15, 18, 300, 400},
            /*HEAVYSTUN 103-105*/{300, 400, 60, 18, 300, 400}, {300, 400, 65, 18, 300, 400}, {300, 400, 60, -4, 300, 400},
            /*CROUCHEDSTUN 106-107*/{300, 400, 50, -14, 300, 400}, {300, 400, 0, -14, 300, 400},
            /*KNOCKDOWN 108-116*/{300, 400, -30, 420, 300, -400}, {440, 400, -50, 80, 440, 400}, {440, 400, -60, 40, 440, 400}, {440, 400, -80, 60, 440, 400},
            {420, 400, -60, 20, 420, 400}, {300, 400, -20, 20, 300, 400}, {300, 400, -90, 0, 300, 400}, {300, 400, -80, -20, 300, 400}, {300, 400, -50, -60, 300, 400},
            /*DEATH 117*/{440, 400, -30, 28, 440, 400},
            /*BLOCK 118-119*/{300, 400, 10, -10, 300, 400}, {300, 400, 30, -10, 300, 400},
            /*CROUCHEDBLOCK 120-121*/{300, 400, 0, -14, 300, 400}, {300, 400, 20, -14, 300, 400}};
    public static final int[][] RYU_RIGHT = {
            /*IDLE 0-4*/{300, 400, 240, 0, -300, 400}, {300, 400, 240, 0, -300, 400}, {300, 400, 240, 0, -300, 400}, {300, 400, 240, 0, -300, 400}, {300, 400, 240, 0, -300, 400},
            /*TURN 5-7*/{260, 400, 240, 0, -260, 400}, {300, 400, 240, 0, -300, 400}, {300, 400, 250, 0, -300, 400},
            /*FORWARDWALK 8-13*/{280, 410, 220, 4, -280, 410}, {280, 410, 220, 4, -280, 410}, {280, 410, 220, 4, -280, 410}, {280, 410, 220, 4, -280, 410}, {280, 410, 220, 4, -280, 410}, {280, 410, 220, 4, -280, 410},
            /*BACKWARDWALK 14-19*/{280, 410, 220, 6, -280, 410}, {280, 410, 220, 6, -280, 410}, {280, 410, 220, 6, -280, 410}, {280, 410, 220, 6, -280, 410}, {280, 410, 220, 6, -280, 410}, {280, 410, 220, 6, -280, 410},
            /*STANDINGLIGHT 20-24*/{320, 410, 264, -3, -320, 410}, {380, 410, 260, -3, -380, 410}, {320, 410, 264, -3, -320, 410}, {320, 410, 260, -3, -320, 410}, {390, 410, 260, -3, -390, 410},
            /*STANDINGMEDIUM 25-27*/{320, 410, 250, -5, -320, 410}, {320, 410, 230, 10, -320, 410}, {400, 410, 280, 10, -400, 410},
            /*STANDINGHEAVY 28-32*/{320, 410, 300, 5, -320, 410}, {320, 410, 260, 5, -320, 410}, {380, 410, 270, 5, -380, 410}, {380, 410, 270, 5, -380, 410}, {380, 410, 280, 5, -380, 410},
            /*IDLEJUMP 33-38*/{290, 400, 240, -10, -290, 400}, {290, 400, 240, -10, -290, 400}, {290, 400, 240, -10, -290, 400}, {290, 400, 240, -10, -290, 400}, {290, 400, 240, -10, -290, 400}, {290, 400, 240, -10, -290, 400},
            /*MOVINGJUMP 39-45*/{300, 400, 240, 0, -300, 400}, {300, 400, 240, 0, -300, 400}, {400, 160, 240, 0, -400, 160}, {240, 480, 180, -80, -200, 500}, {400, 160, 300, 0, -400, 160}, {300, 400, 240, 0, -300, 400}, {300, 400, 240, 0, -300, 400},
            /*CROUCH 46-48*/{300, 400, 240, -12, -300, 400}, {300, 400, 240, -12, -300, 400}, {300, 400, 240, -12, -300, 400},
            /*CROUCHTURN 49-51*/{260, 400, 260, -10, -260, 400}, {260, 400, 260, -10, -260, 400}, {260, 400, 260, -10, -260, 400},
            /*JUMPINGLIGHT 52-55*/{320, 410, 264, -33, -320, 410}, {380, 410, 205, -33, -380, 410}, {320, 410, 245, -33, -320, 410}, {390, 410, 240, -63, -390, 410},
            /*JUMPINGMEDIUM 56-57*/{310, 410, 245, -30, -310, 410}, {380, 410, 260, -30, -380, 410},
            /*JUMPINGHEAVY 58-62*/{310, 410, 240, -30, -310, 410}, {310, 410, 230, -30, -310, 410}, {390, 410, 220, -30, -390, 410}, {310, 410, 210, -30, -310, 410}, {310, 410, 220, -30, -310, 410},
            /*CROUCHEDLIGHT 63-64*/{300, 400, 240, -5, -300, 400}, {370, 400, 220, -5, -370, 400},
            /*CROUCHEDMEDIUM 65-67*/{300, 400, 245, -5, -300, 400}, {300, 400, 225, -5, -300, 400}, {370, 400, 225, -5, -370, 400},
            /*CROUCHEDHEAVY 68-70*/{300, 400, 205, -5, -300, 400}, {300, 400, 215, -5, -300, 400}, {300, 450, 193, -60, -300, 450},
            /*FORWARDLIGHT 71-72*/{300, 400, 265, -30, -300, 400}, {300, 400, 225, -30, -300, 400},
            /*FORWARDMEDIUM 73-76*/{300, 400, 220, -30, -300, 400}, {300, 400, 240, -30, -300, 400}, {300, 400, 208, -30, -300, 400}, {300, 400, 255, -28, -300, 400},
            /*FORWARDHEAVY 77-79*/{300, 400, 240, -30, -300, 400}, {300, 400, 200, -30, -300, 400}, {300, 400, 230, -30, -300, 400},
            /*BACKWARDLIGHT 80-82*/{300, 400, 220, -22, -300, 400}, {300, 400, 210, -22, -300, 400}, {300, 400, 140, -22, -300, 400},
            /*BACKWARDMEDIUM 83-85*/{300, 400, 215, -22, -300, 400}, {300, 400, 185, -22, -300, 400}, {300, 400, 120, -22, -300, 400},
            /*BACKWARDHEAVY 86-90*/{300, 400, 250, -22, -300, 400}, {300, 400, 200, -22, -300, 400}, {300, 450, 180, -78, -300, 450}, {320, 400, 120, -22, -320, 400}, {300, 400, 180, -22, -300, 400},
            /*CROUCHEDFORWARDLIGHT 91-92*/{300, 400, 230, -4, -300, 400}, {380, 400, 190, -4, -380, 400},
            /*CROUCHEDFORWARDMEDIUM 93-95*/{300, 400, 220, 20, -300, 400}, {370, 400, 170, 20, -370, 400}, {460, 400, 200, 20, -460, 400},
            /*CROUCHEDFORWARDHEAVY 96-100*/{300, 400, 240, 20, -300, 400}, {440, 400, 200, 20, -440, 400}, {300, 400, 180, 20, -300, 400}, {320, 400, 210, 20, -320, 400}, {300, 400, 180, 20, -300, 400},
            /*LIGHTSTUN 101-102*/{300, 400, 190, 18, -300, 400}, {300, 400, 240, 18, -300, 400},
            /*HEAVYSTUN 103-105*/{300, 400, 180, 18, -300, 400}, {300, 400, 175, 18, -300, 400}, {300, 400, 180, -4, -300, 400},
            /*CROUCHEDSTUN 106-107*/{300, 400, 190, -14, -300, 400}, {300, 400, 240, -14, -300, 400},
            /*KNOCKDOWN 108-116*/{300, 400, 270, 420, -300, -400}, {440, 400, 290, 80, -440, 400}, {440, 400, 300, 40, -440, 400}, {440, 400, 320, 60, -440, 400},
            {420, 400, 300, 20, -420, 400}, {300, 400, 260, 20, -300, 400}, {300, 400, 330, 0, -300, 400}, {300, 400, 320, -20, -300, 400}, {300, 400, 320, -60, -300, 400},
            /*DEATH 117*/{440, 400, 270, 28, -440, 400},
            /*BLOCK 118-119*/{300, 400, 230, -10, -300, 400}, {300, 400, 210, -10, -300, 400},
            /*CROUCHEDBLOCK 120-121*/{300, 400, 240, -14, -300, 400}, {300, 400, 220, -14, -300, 400}};

    public static final int[][] KEN_LEFT = {
            /*IDLE 0-4*/{260, 400, -20, 0, 260, 400}, {230, 400, 0, 0, 230, 400}, {230, 400, 0, 0, 230, 400}, {230, 400, 0, 0, 230, 400}, {230, 400, 0, 0, 230, 400},
            /*TURN 5-7*/{220, 385, 0, 15, 220, 385}, {220, 385, 0, 15, 220, 385}, {220, 385, 0, 15, 220, 385},
            /*FORWARDWALK 8-13*/{270, 410, 0, 0, 270, 410}, {270, 410, 5, 0, 270, 410}, {270, 410, 10, 0, 270, 410}, {270, 410, 15, 0, 270, 410}, {260, 410, 20, 0, 260, 410}, {270, 410, 25, 0, 270, 410},
            /*BACKWARDWALK 14-19*/{270, 410, -15, 0, 270, 410}, {265, 410, -5, 0, 265, 410}, {265, 410, -5, 0, 265, 410}, {265, 410, 0, 0, 265, 410}, {265, 410, 0, 0, 265, 410}, {265, 410, 0, 0, 265, 410},
            /*STANDINGLIGHT 20-24*/{295, 400, -40, 12, 295, 400}, {350, 400, -20, 12, 350, 400}, {295, 400, -40, 12, 295, 400}, {290, 400, -20, 12, 290, 400}, {350, 410, 20, 12, 350, 400},
            /*STANDINGMEDIUM 25-27*/{280, 380, -20, 13, 280, 380}, {280, 380, -25, 13, 280, 380}, {420, 400, -85, 0, 420, 400},
            /*STANDINGHEAVY 28-32*/{300, 410, -40, -5, 300, 410}, {310, 410, -20, -5, 310, 410}, {370, 410, -20, -5, 370, 410}, {370, 410, -20, -5, 370, 410}, {370, 410, -20, -5, 370, 410},
            /*IDLEJUMP 33-38*/{210, 400, 40, -10, 210, 400}, {210, 400, 40, -10, 210, 400}, {210, 400, 40, -10, 210, 400}, {210, 400, 40, -10, 210, 400}, {210, 400, 40, -10, 210, 400}, {210, 400, 40, -10, 210, 400},
            /*MOVINGJUMP 39-45*/{260, 400, 0, 0, 260, 400}, {260, 400, 0, 0, 260, 400}, {400, 180, -100, 0, 400, 180}, {200, 540, 0, -80, 200, 540}, {400, 180, -40, 0, 400, 180}, {260, 400, 0, 0, 260, 400}, {260, 400, 0, 0, 260, 400},
            /*CROUCH 46-48*/{240, 400, -15, 0, 240, 400}, {240, 400, 0, 0, 240, 400}, {240, 400, 0, 0, 240, 400},
            /*CROUCHTURN 49-51*/{210, 400, 10, 0, 210, 400}, {210, 400, 10, 0, 210, 400}, {210, 400, 10, 0, 210, 400},
            /*JUMPINGLIGHT 52-55*/{240, 430, 0, -55, 245, 430}, {350, 430, -10, -50, 350, 430}, {290, 430, -10, -50, 290, 430}, {330, 430, 30, -80, 330, 430},
            /*JUMPINGMEDIUM 56-57*/{275, 410, -5, 12, 275, 410}, {340, 400, 0, 12, 340, 400},
            /*JUMPINGHEAVY 58-62*/{310, 410, -35, 12, 310, 410}, {275, 410, 10, -30, 295, 410}, {400, 410, 10, -10, 400, 410}, {275, 410, 10, -20, 295, 410}, {275, 410, 10, -20, 295, 410},
            /*CROUCHEDLIGHT 63-64*/{300, 410, -25, 4, 300, 410}, {340, 410, 15, 4, 340, 410},
            /*CROUCHEDMEDIUM 65-67*/{300, 410, -40, 4, 300, 410}, {300, 410, -25, 4, 300, 410}, {370, 410, -10, 4, 370, 410},
            /*CROUCHEDHEAVY 68-70*/{300, 410, 0, 5, 300, 410}, {300, 410, 45, 5, 300, 410}, {270, 460, 35, -82, 270, 460},
            /*FORWARDLIGHT 71-72*/{300, 410, -35, -50, 300, 410}, {300, 410, 30, -60, 300, 410},
            /*FORWARDMEDIUM 73-76*/{300, 410, -10, -50, 300, 410}, {250, 410, 40, -50, 250, 410}, {320, 410, 25, -55, 320, 410}, {280, 410, 20, -53, 280, 410},
            /*FORWARDHEAVY 77-79*/{280, 410, 5, -50, 280, 410}, {300, 410, 45, -50, 300, 410}, {300, 410, 25, -50, 300, 410},
            /*BACKWARDLIGHT 80-82*/{280, 410, -10, -50, 280, 410}, {230, 410, 35, -50, 230, 410}, {310, 410, 85, -50, 310, 410},
            /*BACKWARDMEDIUM 83-85*/{300, 410, 30, -60, 300, 410}, {270, 410, 75, -60, 270, 410}, {250, 410, 125, -60, 250, 410},
            /*BACKWARDHEAVY 86-90*/{260, 410, 20, -60, 260, 410}, {250, 410, 65, -60, 250, 410}, {250, 460, 85, -78, 250, 460}, {320, 410, 140, -60, 320, 410}, {250, 410, 45, -60, 250, 410},
            /*CROUCHEDFORWARDLIGHT 91-92*/{300, 410, -20, -4, 300, 410}, {390, 410, 20, -4, 390, 410},
            /*CROUCHEDFORWARDMEDIUM 93-95*/{300, 410, 0, -4, 300, 410}, {370, 410, 50, -4, 370, 410}, {460, 410, 20, -4, 460, 410},
            /*CROUCHEDFORWARDHEAVY 96-100*/{250, 410, 30, -4, 250, 410}, {440, 410, 20, -4, 440, 410}, {250, 410, 60, -4, 250, 410}, {250, 410, 10, -4, 250, 410}, {300, 410, 50, -4, 300, 410},
            /*LIGHTSTUN 101-102*/{300, 410, -40, -10, 300, 410}, {300, 410, 10, -10, 300, 410},
            /*HEAVYSTUN 103-105*/{300, 410, 10, -10, 300, 410}, {300, 410, 40, -10, 300, 410}, {300, 410, 70, -10, 300, 410},
            /*CROUCHEDSTUN 106-107*/{300, 410, 0, -40, 300, 410}, {300, 410, 40, -40, 300, 410},
            /*KNOCKDOWN 108-116*/{300, 400, -50, -35, 300, 400}, {440, 400, -40, -45, 440, 400}, {420, 400, -20, -45, 420, 400}, {430, 400, -50, -45, 430, 400},
            {320, 400, -20, -40, 320, 400}, {260, 400, 0, -40, 260, 400}, {220, 400, -10, -40, 220, 400}, {300, 400, -70, -40, 300, 400}, {300, 400, 10, -70, 300, 400},
            /*DEATH 117*/{440, 400, -20, -37, 440, 400},
            /*BLOCK 118-119*/{280, 400, 30, -26, 280, 400}, {280, 400, 50, -26, 280, 400},
            /*CROUCHEDBLOCK 120-121*/{280, 400, 30, -26, 280, 400}, {250, 400, 50, -26, 250, 400}};
    public static final int[][] KEN_RIGHT = {
            /*IDLE 0-4*/{260, 400, 260, 0, -260, 400}, {230, 400, 240, 0, -230, 400}, {230, 400, 240, 0, -230, 400}, {230, 400, 240, 0, -230, 400}, {230, 400, 240, 0, -230, 400},
            /*TURN 5-7*/{220, 385, 240, 15, -220, 385}, {220, 385, 240, 15, -220, 385}, {220, 385, 240, 15, -220, 385},
            /*FORWARDWALK 8-13*/{270, 410, 280, 0, -270, 410}, {270, 410, 275, 0, -270, 410}, {270, 410, 270, 0, -270, 410}, {270, 410, 265, 0, -270, 410}, {260, 410, 260, 0, -260, 410}, {270, 410, 255, 0, -270, 410},
            /*BACKWARDWALK 14-19*/{270, 410, 280, 0, -270, 410}, {265, 410, 275, 0, -265, 410}, {265, 410, 275, 0, -265, 410}, {265, 410, 270, 0, -265, 410}, {265, 410, 270, 0, -265, 410}, {265, 410, 270, 0, -265, 410},
            /*STANDINGLIGHT 20-24*/{295, 400, 280, 12, -295, 400}, {350, 400, 250, 12, -350, 400}, {295, 400, 280, 12, -295, 400}, {290, 400, 260, 12, -290, 400}, {350, 410, 235, 12, -350, 400},
            /*STANDINGMEDIUM 25-27*/{280, 380, 260, 13, -280, 380}, {280, 380, 260, 13, -280, 380}, {420, 400, 320, 0, -420, 400},
            /*STANDINGHEAVY 28-32*/{300, 410, 300, -5, -300, 410}, {310, 410, 260, -5, -310, 410}, {370, 410, 270, -5, -370, 410}, {370, 410, 270, -5, -370, 410}, {370, 410, 270, -5, -370, 410},
            /*IDLEJUMP 33-38*/{210, 400, 230, -10, -210, 400}, {210, 400, 230, -10, -210, 400}, {210, 400, 230, -10, -210, 400}, {210, 400, 230, -10, -210, 400}, {210, 400, 230, -10, -210, 400}, {210, 400, 230, -10, -210, 400},
            /*MOVINGJUMP 39-45*/{260, 400, 260, 0, -260, 400}, {260, 400, 260, 0, -260, 400}, {400, 180, 260, 0, -400, 180}, {200, 540, 200, -80, -200, 540}, {400, 180, 320, 0, -400, 180}, {260, 400, 260, 0, -260, 400}, {260, 400, 260, 0, -260, 400},
            /*CROUCH 46-48*/{240, 400, 260, 0, -240, 400}, {240, 400, 240, 0, -240, 400}, {240, 400, 240, 0, -240, 400},
            /*CROUCHTURN 49-51*/{210, 400, 220, 0, -210, 400}, {210, 400, 220, 0, -210, 400}, {210, 400, 220, 0, -210, 400},
            /*JUMPINGLIGHT 52-55*/{240, 430, 240, -55, -245, 430}, {350, 430, 250, -50, -350, 430}, {290, 430, 260, -50, -290, 430}, {330, 430, 235, -80, -330, 430},
            /*JUMPINGMEDIUM 56-57*/{275, 410, 240, 12, -275, 410}, {340, 400, 240, 12, -340, 400},
            /*JUMPINGHEAVY 58-62*/{310, 410, 270, 12, -310, 410}, {275, 410, 230, -30, -295, 410}, {400, 410, 230, -10, -400, 410}, {275, 410, 230, -20, -295, 410}, {275, 410, 230, -20, -295, 410},
            /*CROUCHEDLIGHT 63-64*/{300, 410, 245, 4, -300, 410}, {340, 410, 210, 4, -340, 410},
            /*CROUCHEDMEDIUM 65-67*/{300, 410, 260, 4, -300, 410}, {300, 410, 245, 4, -300, 410}, {370, 410, 230, 4, -370, 410},
            /*CROUCHEDHEAVY 68-70*/{300, 410, 220, 5, -300, 410}, {300, 410, 180, 5, -300, 410}, {270, 460, 180, -82, -270, 460},
            /*FORWARDLIGHT 71-72*/{300, 410, 240, -50, -300, 410}, {300, 410, 200, -50, -300, 410},
            /*FORWARDMEDIUM 73-76*/{300, 410, 240, -50, -300, 410}, {250, 410, 200, -50, -250, 410}, {320, 410, 210, -55, -320, 410}, {280, 410, 220, -53, -280, 410},
            /*FORWARDHEAVY 77-79*/{280, 410, 230, -50, -280, 410}, {300, 410, 190, -50, -300, 410}, {300, 410, 200, -50, -300, 410},
            /*BACKWARDLIGHT 80-82*/{280, 410, 230, -50, -280, 410}, {230, 410, 190, -50, -230, 410}, {310, 410, 150, -50, -310, 410},
            /*BACKWARDMEDIUM 83-85*/{300, 410, 200, -60, -300, 410}, {270, 410, 160, -60, -270, 410}, {250, 410, 120, -60, -250, 410},
            /*BACKWARDHEAVY 86-90*/{260, 410, 200, -60, -260, 410}, {250, 410, 155, -60, -250, 410}, {250, 460, 135, -78, -250, 460}, {320, 410, 80, -60, -320, 410}, {250, 410, 175, -60, -250, 410},
            /*CROUCHEDFORWARDLIGHT 91-92*/{300, 410, 250, -4, -300, 410}, {390, 410, 210, -4, -390, 410},
            /*CROUCHEDFORWARDMEDIUM 93-95*/{300, 410, 230, -4, -300, 410}, {370, 410, 180, -4, -370, 410}, {460, 410, 210, -4, -460, 410},
            /*CROUCHEDFORWARDHEAVY 96-100*/{250, 410, 200, -4, -250, 410}, {440, 410, 210, -4, -440, 410}, {250, 410, 170, -4, -250, 410}, {250, 410, 220, -4, -250, 410}, {300, 410, 180, -4, -300, 410},
            /*LIGHTSTUN 101-102*/{300, 410, 280, -10, -300, 410}, {300, 410, 230, -10, -300, 410},
            /*HEAVYSTUN 103-105*/{300, 410, 240, -10, -300, 410}, {300, 410, 210, -10, -300, 410}, {300, 410, 180, -10, -300, 410},
            /*CROUCHEDSTUN 106-107*/{300, 410, 240, -40, -300, 410}, {300, 410, 200, -40, -300, 410},
            /*KNOCKDOWN 108-116*/{300, 400, 290, -35, -300, 400}, {440, 400, 280, -45, -440, 400}, {420, 400, 260, -45, -420, 400}, {430, 400, 290, -45, -430, 400},
            {320, 400, 300, -40, -320, 400}, {260, 400, 230, -40, -260, 400}, {220, 400, 240, -40, -220, 400}, {300, 400, 300, -40, -300, 400}, {300, 400, 220, -70, -300, 400},
            /*DEATH 117*/{440, 400, 260, -37, -440, 400},
            /*BLOCK 118-119*/{280, 400, 210, -26, -280, 400}, {280, 400, 190, -26, -280, 400},
            /*CROUCHEDBLOCK 120-121*/{280, 400, 230, -26, -280, 400}, {250, 400, 210, -26, -250, 400}};

    public Constants() {
        try {
            LOGO = ImageManager.loadImage("logo");
            MENU = ImageManager.loadImage("menu");

            BACKGROUND_IMAGES.add(ImageManager.loadImage("background1"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background2"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background3"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background4"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background5"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background6"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background7"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background8"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background9"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background10"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background11"));
            BACKGROUND_IMAGES.add(ImageManager.loadImage("background12"));

            FIGHTER_SHEETS.add(ImageManager.loadImage("ryu"));
            FIGHTER_SHEETS.add(ImageManager.loadImage("ken"));
            FIGHTER_SHEETS.add(ImageManager.loadImage("chunli"));
            FIGHTER_SHEETS.add(ImageManager.loadImage("guile"));
            FIGHTER_SHEETS.add(ImageManager.loadImage("mbison"));
            FIGHTER_SHEETS.add(ImageManager.loadImage("zangief"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}