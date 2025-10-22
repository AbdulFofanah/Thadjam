package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;

public class MenuAssets {

    public Texture startButton, settingsButton, tutorialButton, backButton, background;

    public MenuAssets() {
        startButton = new Texture("start_button.png");
        settingsButton = new Texture("settings_button.png");
        tutorialButton = new Texture("tutorial_button.png");
        backButton = new Texture("back_button.png");
        //background = new Texture("menuBackground.png");
    }

    public void dispose() {
        startButton.dispose();
        settingsButton.dispose();
        tutorialButton.dispose();
        backButton.dispose();
        //background.dispose();
    }

}
