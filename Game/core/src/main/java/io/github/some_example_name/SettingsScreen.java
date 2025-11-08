package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {

    private Stage stage;
    private MenuAssets menuAssets;
    private Assets backgroundAssets;
    private final Main game;

    public SettingsScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        ImageButton settingsButton = new ImageButton(new TextureRegionDrawable(menuAssets.settingsButton));
        settingsButton.setSize(600f, 300f);
        settingsButton.setPosition(
            1200,
            1500);
        settingsButton.getImage().setFillParent(true);

        stage.addActor(settingsButton);

        // Create Settings Button
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(menuAssets.backButton));
        backButton.setSize(600f, 300f);
        backButton.setPosition(
            50,
            50);
        backButton.getImage().setFillParent(true);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }

        });

        stage.addActor(backButton);

        ImageButton arrowButton = new ImageButton(new TextureRegionDrawable(menuAssets.arrowButton));
        arrowButton.setSize(1200f, 600f);
        arrowButton.setPosition(
            -300,
            500);
        arrowButton.getImage().setFillParent(true);

        arrowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameControlsConfig.useArrowKeys = true; // Set default to arrow keys thus making wasd false implicitlu
                game.setScreen(new MenuScreen(game)); // Go back to menu
            }

        });

        stage.addActor(arrowButton);

        ImageButton wasdButton = new ImageButton(new TextureRegionDrawable(menuAssets.wasdButton));
        wasdButton.setSize(1200f, 600f);
        wasdButton.setPosition(
            1200,
            500);
        wasdButton.getImage().setFillParent(true);

        wasdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameControlsConfig.useArrowKeys = false; // Set arrow keys to false thus making wasd true implicitlu
                game.setScreen(new MenuScreen(game)); // Go back to menu
            }

        });

        stage.addActor(wasdButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.schoolTexture_1, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        stage.getViewport().update(screenWidth, screenHeight, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        menuAssets.dispose();
        backgroundAssets.dispose();
    }
}
