package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;

public class MenuAssets {

    public Texture startButton, settingsButton, tutorialButton,
                   backButton, arrowButton, wasdButton, exitButton, homeButton;

    public MenuAssets() {
        startButton = new Texture("start_button.png");
        settingsButton = new Texture("settings_button.png");
        tutorialButton = new Texture("tutorial_button.png");
        backButton = new Texture("back_button.png");
        arrowButton = new Texture("arrow_keys.png");
        wasdButton = new Texture("wasd_keys.png");
        exitButton = new Texture("exit_game_button.png");
        homeButton = new Texture("home_button.png");
        //background = new Texture("menuBackground.png");
    }

    public void dispose() {
        startButton.dispose();
        settingsButton.dispose();
        tutorialButton.dispose();
        backButton.dispose();
        arrowButton.dispose();
        wasdButton.dispose();
        exitButton.dispose();
        homeButton.dispose();
        //background.dispose();
    }

}
