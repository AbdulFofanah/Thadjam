package io.github.some_example_name;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class LevelMap {
    private final String[] levelMap = {
        "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
        "W.W.W.....W...................W.............W.............WW",
        "W.W.W.WWW.W.WWWWW.WWWWWWWWWWW.WWWWW.WWWWWWW.W.WWWWWWW.WWW.WW",
        "W.W.W.W...W...W...W.........W.W.....W.....W.W.W.....W.W...WW",
        "W.W.W.W.WWW.WWW.WWW.WWWWWWW.W.W.WWWWWWW.WWW.WWW.WWW.W.W.WWWW",
        "W.W...W.....W...W.W...W.....W...W.......W...W...W...W.W...WW",
        "W.WWW.WWWWWWW.WWW.W.W.W.WWWWWWWWW.WWWWW.W.WWW.WWW.WWW.WWW.WW",
        "W...W.......W.W.....W.W.W.........W...W.W.W...W.W.....W.W.WW",
        "WWW.WWWWWWW.W.WWWWWWW.WWW.WWWWWWW.W.W.W.W.W.WWW.WWWWWWW.W.WW",
        "W.W...W...W.W.......W...........W...W.W.W.W.W.W.......W...WW",
        "W.WWW.W.W.W.WWWWWWW.WWWWWWWWWWW.WWWWW.W.W.W.W.W.WWWWW.W.WWWW",
        "W...W...W.W.....W.W...W.....W.....W...W.W...W...W.W...W.W.WW",
        "W.W.WWWWW.WWWWW.W.WWW.W.WWW.WWWWWWW.WWWWWWWWW.WWW.W.W.W.W.WW",
        "W.W.....W.W.W...W...W.W.W.......W...W.......W.W...W.W.W.W.WW",
        "W.W.WWW.W.W.W.WWW.W.W.W.W.WWWWW.W.WWW.WWWWW.W.WWW.W.WWW.W.WW",
        "W.W.W...W.W.W.....W.W.W.W.W...W.W.W...W...W.W...W.W...W.W.WW",
        "WWW.WWWWW.W.WWWWW.WWW.W.WWW.W.W.W.W.WWW.WWW.WWW.W.WWW.W.W.WW",
        "W...W...W.W...W...W...W.....W.W.W.W.W.....W.W...W...W.W...WW",
        "W.WWW.W.W.W.WWW.WWW.WWW.WWWWW.W.W.W.W.WWW.W.W.WWW.W.W.WWW.WW",
        "W...W.W...W...W...W...W.W.....W.W.W.W.W...W.W.W...W.W...W.WW",
        "W.W.W.WWWWWWW.WWW.WWW.W.W.WWWWW.W.W.WWW.W.W.W.WWW.WWWWW.W.WW",
        "W.W.W.W.....W.....W...W.W.....W...W.....W.W.W...W.W.....W.WW",
        "WWW.W.W.WWW.WWWWWWW.WWW.WWWWW.WWWWWWWWWWW.W.WWW.W.W.WWWWW.WW",
        "W...W...W...W.......W...W...W.W.....W.....W...W.W.W.......WW",
        "W.W.WWWWW.WWW.WWWWWWW.WWW.WWW.W.WWW.W.WWWWW.W.W.W.WWWWWWWWWW",
        "W.W.....W.W...W...W.W.W.....W...W.W...W...W.W.W.W.........WW",
        "W.WWWWW.W.W.WWW.W.W.W.WWWWW.WWWWW.WWWWWWW.W.W.W.WWW.WWWWW.WW",
        "W.W.....W.W.....W.W.W.....W.W.....W.......W.W.W.....W...W.WW",
        "W.WWWWWWW.W.WWWWW.W.WWWWW.W.W.WWW.W.WWW.W.W.W.WWWWWWWWW.W.WW",
        "W.W.....W.W...W...W.......W...W.W.....W.W.W.W.....W.....W.WW",
        "W.W.WWW.W.WWWWW.WWW.WWWWWWW.WWW.WWWWWWW.W.W.WWWWWWW.W.WWW.WW",
        "W.W.W...W.W.....W...W.....W.......W.W...W.W.....W...W.W...WW",
        "W.W.W.WWW.W.WWWWW.WWW.WWW.WWWWWWW.W.W.WWW.WWWWW.W.WWW.W.WWWW",
        "W...W.....W.W...W...W...W...W.W.....W.W.W.....W.W...W.W.W.WW",
        "W.WWWWWWWWW.W.W.WWW.WWW.WWW.W.W.WWWWW.W.WWWWW.W.W.WWW.W.W.WW",
        "W...W.....W.W.W.....W...W...W...W.....W.......W...W...W.W.WW",
        "WWW.W.WWW.W.W.WWWWWWW.WWW.WWWWWWW.WWWWW.WWWWWWWWWWW.WWW.W.WW",
        "W.....W.....W.........W...........W...............W.......WW",
        "W.WWWWWWWWWWWWWWWWWWW....WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
        "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
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
