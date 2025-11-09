package io.github.some_example_name;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

import static com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnShape.line;

public class LevelMap {
    /**
     * Defining map
     */
    private final String[] levelMap = {
        "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
        "W.W.W.....W...................W.............W..............W",
        "W.W.W.WWW.W.WWWWW.WWWWWWWWWWW.WWWWW.WWWWWWW.W.WWWWWWW.WWWW.W",
        "W.W.W.W...W...W...W.........W.W.....W.....W.W.W.....W.W....W",
        "W.W.W.W.WWW.WWW.WWW.WWWWWWW.W.W.WWWWWWW.WWW.WWW.WWW.W.W.WWWW",
        "W.W...W.....W...W.W...W.....W...W.......W...W...W...W.W....W",
        "W.WWW.WWWWWWW.WWW.W.W.W.WWWWWWWWW.WWWWW.W.WWW.WWW.WWW.WWWW.W",
        "W...W.......W.W.....W.W.W.........W...W.W.W...W.W.....W....W",
        "WWW.WWWWWWW.W.WWWWWWW.WWW.WWWWWWW.W.W.W.W.W.WWW.WWWWWWW.WWWW",
        "W.W...W...W.W.......W...........W...W.W.W.W.W.W.......W....W",
        "W.WWW.W.W.W.WWWWWWW.WWWWWWWWWWW.WWWWW.W.W.W.W.W.WWWWW.WWWW.W",
        "W...W...W.W.....W.W...W.....W.....W...W.W...W...W.W...W....W",
        "W.W.WWWWW.WWWWW.W.WWW.W.WWW.WWWWWWW.WWWWWWWWW.WWW.W.W.WWWW.W",
        "W.W.....W.W.W...W...W.W.W.......W...W.......W.W...W.W.W..W.W",
        "W.W.WWW.W.W.W.WWW.W.W.W.W.WWWWW.W.WWW.WWWWW.W.WWW.W.WWW.WW.W",
        "W.W.W...W.W.W.....W.W.W.W.W...W.W.W...W...W.W...W.W...W....W",
        "WWW.WWWWW.W.WWWWW.WWW.W.WWW.W.W.W.W.WWW.WWW.WWW.W.WWW.W.WWWW",
        "W...W...W.W...W...W...W.....W.W.W.W.W.....W.W...W...W.W....W",
        "W.WWW.W.W.W.WWW.WWW.WWW.WWWWW.W.W.W.W.WWW.W.W.WWW.W.W.WWWW.W",
        "W...W.W...W...W...W...W.W.....W.W.W.W.W...W.W.W...W.W....W.W",
        "W.W.W.WWWWWWW.WWW.WWW.W.W.WWWWW.W.W.WWW.W.W.W.WWW.WWWWWW.W.W",
        "W.W.W.W.....W.....W...W.W.....W...W.....W.W.W...W.W......W.W",
        "WWW.W.W.WWW.WWWWWWW.WWW.WWWWW.WWWWWWWWWWW.W.WWW.W.W.WWWWWW.W",
        "W...W...W...W.......W...W...W.W.....W.....W...W.W.W........W",
        "W.W.WWWWW.WWW.WWWWWWW.WWW.WWW.W.WWW.W.WWWWW.W.W.W.WWWWWWWWWW",
        "W.W.....W.W...W...W.W.W.....W...W.W...W...W.W.W.W..........W",
        "W.WWWWW.W.W.WWW.W.W.W.WWWWW.WWWWW.WWWWWWW.W.W.W.WWW.WWWWWW.W",
        "W.W.....W.W.....W.W.W.....W.W.....W.......W.W.W.....W....W.W",
        "W.WWWWWWW.W.WWWWW.W.WWWWW.W.W.WWW.W.WWW.W.W.W.WWWWWWWWWW.W.W",
        "W.W.....W.W...W...W.......W...W.W.....W.W.W.W.....W......W.W",
        "W.W.WWW.W.WWWWW.WWW.WWWWWWW.WWW.WWWWWWW.W.W.WWWWWWW.WWWWWW.W",
        "W.W.W...W.W.....W...W.....W.......W.W...W.W.....W...W......W",
        "W.W.W.WWW.W.WWWWW.WWW.WWW.WWWWWWW.W.W.WWW.WWWWW.W.WWW.WWWWWW",
        "W...W.....W.W...W.W.W...W...W.W.....W.W.W.W.W.W.W.....W....W",
        "W.WWWWWWWWW.W.W.W...WWW.WWW.W.W.WWWWW.W.W.....W.W.WWW.W.WW.W",
        "W...W.....W.W.W.WWW.W...W...W.W.W...W.W.WWWWW.W.W.W.W.W.W..W",
        "WWW.W.W.W.W.W.W.....W.WWW.W.W...W.W...W.......W...W.W...WW.W",
        "W...W.W.WWW.W.WWWWWWW.W.W.WWWWWWW.WWWWW.WWWWWWWWWWW.WWWWW..W",
        "W.W...W.....W.........W...........W...............W.......WW",
        "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
    };

    private Array<Rectangle> wallRects;

    private Texture wallTexture;
    private Texture floorTexture;

    /**
     *
     * @param assets
     */
    public LevelMap(Assets assets) {
        //the arrays will store all the wall and enemy pieces for this map
        wallRects = new Array<>();
        this.wallTexture = assets.wallTexture;
        this.floorTexture = assets.floorTexture;
        //loops through each row, loops through each line in the map, if it finds the 'W' then it'll make it a collision box e.g.the character wont be able to move through it
        for (int row = 0; row < levelMap.length; row++) {
            String line = levelMap[levelMap.length - 1 - row]; // flip Y axis
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) == 'W') {
                    wallRects.add(new Rectangle(col, row, 1, 1));
                }
            }
        }
    }

    /**
     *
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        //draw floors and walls based on the map e.g. loops through the rows and columns of the map and draws the floors if there is a '.' and a wall if there is a '.'
        for (int row = 0; row < levelMap.length; row++) {
            String line = levelMap[levelMap.length - 1 - row];
            for (int col = 0; col < line.length(); col++) {
                batch.draw(floorTexture, col, row, 1, 1);
                if (line.charAt(col) == 'W') {
                    batch.draw(wallTexture, col, row, 1, 1);
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public Array<Rectangle> getWallRects() {
        return wallRects;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isWalkable(int x, int y) {
        // Check movement isnt blocked by wall
        if (y < 0 || y >= levelMap.length || x < 0 || x >= levelMap[0].length()) {
            return false; // outside the map
        }
        // Check if the tile is not a wall
        return levelMap[y].charAt(x) != 'W';
    }
}
