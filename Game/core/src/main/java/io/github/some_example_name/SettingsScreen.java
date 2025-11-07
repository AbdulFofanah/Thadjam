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
    private MenuAssets assets;
    private final Main game;

    public SettingsScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        assets = new MenuAssets();

        ImageButton settingsButton = new ImageButton(new TextureRegionDrawable(assets.settingsButton));
        settingsButton.setSize(200f, 80f);
        settingsButton.setPosition(
            Gdx.graphics.getWidth() / 2f - settingsButton.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f + 250
        );

        stage.addActor(settingsButton);

        // Create Settings Button
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(assets.backButton));
        backButton.setSize(200f, 80f);
        backButton.setPosition(
            100,
            100);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }

        });

        stage.addActor(backButton);

        ImageButton arrowButton = new ImageButton(new TextureRegionDrawable(assets.arrowButton));
        arrowButton.setSize(400f, 160f);
        arrowButton.setPosition(
            300,
            500);

        arrowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameControlsConfig.useArrowKeys = true; // Set default to arrow keys thus making wasd false implicitlu
                game.setScreen(new MenuScreen(game)); // Go back to menu
            }

        });

        stage.addActor(arrowButton);

        ImageButton wasdButton = new ImageButton(new TextureRegionDrawable(assets.wasdButton));
        wasdButton.setSize(400f, 160f);
        wasdButton.setPosition(
            400,
            500);

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
        ScreenUtils.clear(Color.BLUE);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        assets.dispose();
    }
}
