package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;

public class MenuAssets {

    public Texture startButton, settingsButton, background;

    public MenuAssets() {
        startButton = new Texture("startButton.png");
        settingsButton = new Texture("settingsButton.png");
        //background = new Texture("menuBackground.png");
    }

    public void dispose() {
        startButton.dispose();
        settingsButton.dispose();
        //background.dispose();
    }

}
