package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.awt.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener { //anything under this will declare all the variables
    Texture wallTexture;
    Texture characterTexture;
    Texture floorTexture;
    Music music;

    SpriteBatch spriteBatch;
    FitViewport viewport;

    Sprite characterSprite;

    @Override
    public void create() { //anything that is in this method needs adding to the assets with the correct name
        wallTexture = new Texture("brick_brown_0.png");
        characterTexture = new Texture("Run__000.png");
        floorTexture = new Texture("floor_sand_rock_0.png");
        //music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

        spriteBatch = new SpriteBatch();
        // tile amounts, each 1 in width or height is equal to 100x100 pixels
        viewport = new FitViewport(20, 20);

        characterSprite = new Sprite(characterTexture);
        characterSprite.setSize(1, 1);
        characterSprite.setPosition(1, 1);
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;
        viewport.update(width, height, true);

    }

    @Override
    public void render() {
        // Draw your application here.
        input();
        logic();
        draw();
    }

    private void input() {
        float speed = 6f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            characterSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            characterSprite.translateX(-speed * delta);
        }  else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            characterSprite.translateY(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            characterSprite.translateY(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            characterSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            characterSprite.translateX(-speed * delta);
        }  else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            characterSprite.translateY(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            characterSprite.translateY(-speed * delta);
        }
    }

    private void logic() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        float characterWidth = characterSprite.getWidth();
        float characterHeight = characterSprite.getHeight();

        characterSprite.setX(MathUtils.clamp(characterSprite.getX(), 0, worldWidth - characterWidth));
        characterSprite.setY(MathUtils.clamp(characterSprite.getY(), 0, worldHeight - characterHeight));
    }

    private void draw() {//anything in this method will be drawn onto the s creen as long as its between the begin and end
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(wallTexture, 0, 0, 1, 1);//make sure the background is always at the top as it is layered
        spriteBatch.draw(floorTexture, 1, 1, 1, 1);

        characterSprite.draw(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }
}
