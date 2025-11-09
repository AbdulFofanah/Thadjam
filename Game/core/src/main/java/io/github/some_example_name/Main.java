package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main class for the game
 * Handles screens and shared game resources
 */
public class Main extends Game {

    /** Draws images and sprites */
    public SpriteBatch SpriteDrawing;

    /** Draws text */
    public BitmapFont TextFont;

    /**
     * Runs once when the game starts
     * Sets up things the game will use
     */
    @Override
    public void create() {
        SpriteDrawing = new SpriteBatch();
        TextFont = new BitmapFont();

        // Open the main menu screen
        this.setScreen(new MenuScreen(this));
    }

    /**
     * Runs every frame of the game
     * Calls the render method of the current screen
     */
    @Override
    public void render() {
        super.render(); // Renders the active screen
    }

    /**
     * Runs when the game is closed
     * Cleans up and frees memory
     */
    @Override
    public void dispose() {
        SpriteDrawing.dispose();
        TextFont.dispose();

        if (screen != null) {
            screen.dispose();
        }
    }
}
