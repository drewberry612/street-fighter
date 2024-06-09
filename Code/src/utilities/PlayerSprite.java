package utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static utilities.Constants.*;

public class PlayerSprite {
    public ArrayList<Image> sprite;

    private int[][] sheetConfig;
    private int[][] leftConfig;
    private int[][] rightConfig;

    public Image portrait;
    public Image name;

    public PlayerSprite(int character) throws IOException {
        sprite = new ArrayList<>();
        BufferedImage spriteSheet = FIGHTER_SHEETS.get(character - 1);

        switch (character) {
            case 1 -> {// Ryu
                sheetConfig = RYU_SPRITE;
                leftConfig = RYU_LEFT;
                rightConfig = RYU_RIGHT;
                portrait = spriteSheet.getSubimage(150, 2580, 100, 100);
                name = ImageManager.loadImage("ryuName");
            }
            case 2 -> {// Ken
                sheetConfig = KEN_SPRITE;
                leftConfig = KEN_LEFT;
                rightConfig = KEN_RIGHT;
                portrait = spriteSheet.getSubimage(680, 10, 100, 100);
                name = spriteSheet.getSubimage(900, 100, 60, 30);
            }
            case 3 -> {// Chun Li
                sheetConfig = RYU_SPRITE;
                leftConfig = RYU_LEFT;
                rightConfig = RYU_RIGHT;
                portrait = spriteSheet.getSubimage(680, 10, 100, 100);
                name = spriteSheet.getSubimage(905, 100, 100, 30);
            }
            case 4 -> {// Guile
                sheetConfig = RYU_SPRITE;
                leftConfig = RYU_LEFT;
                rightConfig = RYU_RIGHT;
                portrait = spriteSheet.getSubimage(1020, 70, 120, 100);
                name = spriteSheet.getSubimage(1130, 70, 100, 30);
            }
            case 5 -> {// M Bison
                sheetConfig = RYU_SPRITE;
                leftConfig = RYU_LEFT;
                rightConfig = RYU_RIGHT;
                portrait = spriteSheet.getSubimage(420, 70, 100, 100);
                name = spriteSheet.getSubimage(940, 150, 100, 30);
            }
            case 6 -> {// Zangief
                sheetConfig = RYU_SPRITE;
                leftConfig = RYU_LEFT;
                rightConfig = RYU_RIGHT;
                portrait = spriteSheet.getSubimage(980, 20, 100, 100);
                name = spriteSheet.getSubimage(1205, 110, 100, 30);
            }
        }

        for (int i=0; i<122; i++) {
            sprite.add(spriteSheet.getSubimage(sheetConfig[i][0], sheetConfig[i][1], sheetConfig[i][2], sheetConfig[i][3]));
        }
    }

    public void drawLeftSprite(int x, int y, int configIndex, Graphics2D g) {
        Image temp = sprite.get(configIndex).getScaledInstance(leftConfig[configIndex][0], leftConfig[configIndex][1], 0);
        g.drawImage(temp, x + leftConfig[configIndex][2], y + leftConfig[configIndex][3] - 20, leftConfig[configIndex][4], leftConfig[configIndex][5], null);
    }

    public void drawRightSprite(int x, int y, int configIndex, Graphics2D g) {
        Image temp = sprite.get(configIndex).getScaledInstance(rightConfig[configIndex][0], rightConfig[configIndex][1], 0);
        g.drawImage(temp, x + rightConfig[configIndex][2], y + rightConfig[configIndex][3] - 20, rightConfig[configIndex][4], rightConfig[configIndex][5], null);
    }
}
