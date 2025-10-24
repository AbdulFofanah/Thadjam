package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;

public class Assets {
    public Texture wallTexture;
    public Texture floorTexture;
    public Texture characterTexture;
    public Texture enemyTexture_1;
    public Music music;

    public Assets() {
        wallTexture = new Texture("brick_brown_0.png");
        characterTexture = new Texture("Run__000.png");
        floorTexture = new Texture("floor_sand_rock_0.png");
        enemyTexture_1 = new Texture("pizza_new.png");
    }

    public void dispose() {
        wallTexture.dispose();
        characterTexture.dispose();
        floorTexture.dispose();
        enemyTexture_1.dispose();
    }
}
