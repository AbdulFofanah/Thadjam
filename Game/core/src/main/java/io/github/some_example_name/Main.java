package io.github.some_example_name;

import com.badlogic.gdx.Game; // Imported the game class here
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** {@link com.badlogic.gdx.Game} implementation shared by all platforms. */
public class Main extends Game { //anything under this will declare all the variables

    public SpriteBatch SpriteDrawing; // draw the sprites to the screem
    public BitmapFont TextFont; // draw text on the screen

    @Override
    public void create() {
        //Reuseable resources
        SpriteDrawing = new SpriteBatch();
        TextFont = new BitmapFont();
        //the main instance is passed through to the game screen
        this.setScreen(new GameScreen(this)); //When the menu screen is implemented, use menu screen here
    }

    @Override
    public void render() {
        // using super.render makes sure the render that being called is the active screen
        super.render();
    }

    @Override
    public void dispose() {
        // stop everything
        SpriteDrawing.dispose();
        TextFont.dispose();

        if (screen != null) {
            screen.dispose();
        }

    }
}

