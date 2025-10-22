package io.github.some_example_name;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class LevelMap {
    private final String[] levelMap = {
        "WWWWWWWWWWWWWWWWWWWW",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W....S.............W",
        "W..................W",
        "W..................W",
        "W..................W",
        "WWWWWWWWWWWWWWWWWWWW"
    };

    private Array<Rectangle> wallRects;
    private Array<Rectangle> enemyRects;

    private Texture wallTexture;
    private Texture floorTexture;
    private Texture enemyTexture_1;

    public LevelMap(Assets assets) {
        //the arrays will store all the wall and enemy pieces for this map
        wallRects = new Array<>();
        enemyRects = new Array<>();
        this.wallTexture = assets.wallTexture;
        this.floorTexture = assets.floorTexture;
        this.enemyTexture_1 = assets.enemyTexture_1;
        //loops through each row, loops through each line in the map, if it finds the 'W' then it'll make it a collision box e.g.the character wont be able to move through it
        for (int row = 0; row < levelMap.length; row++) {
            String line = levelMap[levelMap.length - 1 - row]; // flip Y axis
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) == 'W') {
                    wallRects.add(new Rectangle(col, row, 1, 1));
                } else if (line.charAt(col) == 'S') {
                    enemyRects.add(new Rectangle(col, row, 1, 1));
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {
        //draw floors and walls based on the map e.g. loops through the rows and columns of the map and draws the floors if there is a '.' and a wall if there is a '.'
        for (int row = 0; row < levelMap.length; row++) {
            String line = levelMap[levelMap.length - 1 - row];
            for (int col = 0; col < line.length(); col++) {
                batch.draw(floorTexture, col, row, 1, 1);
                if (line.charAt(col) == 'W') {
                    batch.draw(wallTexture, col, row, 1, 1);
                }else if (line.charAt(col) == 'S') {
                    batch.draw(enemyTexture_1, col, row, 1, 1);
                }
            }
        }
    }

    public Array<Rectangle> getWallRects() {
        return wallRects;
    }

    public Array<Rectangle> getEnemyRects() {
        return enemyRects;
    }

}
