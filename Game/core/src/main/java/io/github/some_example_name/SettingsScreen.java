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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class SettingsScreen implements Screen {

    private Stage stage;
    private MenuAssets menuAssets;
    private Assets backgroundAssets;
    private final Main game;

    public SettingsScreen(Main game) {
        this.game = game;

        // stage for UI
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);

        // camera + viewport
        OrthographicCamera camera = new OrthographicCamera();
        FitViewport viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();

        // assets
        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // settings title button
        ImageButton settingsButton = new ImageButton(new TextureRegionDrawable(menuAssets.settingsButton));
        settingsButton.setSize(400, 200);
        settingsButton.setPosition(viewport.getWorldWidth()/2 - 200, viewport.getWorldHeight() - 250);
        settingsButton.getImage().setFillParent(true);
        stage.addActor(settingsButton);

        // back button
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(menuAssets.backButton));
        backButton.setSize(200, 80);
        backButton.setPosition(50, 50);
        backButton.getImage().setFillParent(true);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(backButton);

        // rrow keys button
        ImageButton arrowButton = new ImageButton(new TextureRegionDrawable(menuAssets.arrowButton));
        arrowButton.setSize(400, 150);
        arrowButton.setPosition(viewport.getWorldWidth()/4 - 200, viewport.getWorldHeight()/2);
        arrowButton.getImage().setFillParent(true);
        arrowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameControlsConfig.useArrowKeys = true;
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(arrowButton);

        // WASD button
        ImageButton wasdButton = new ImageButton(new TextureRegionDrawable(menuAssets.wasdButton));
        wasdButton.setSize(400, 150);
        wasdButton.setPosition(3*viewport.getWorldWidth()/4 - 200, viewport.getWorldHeight()/2);
        wasdButton.getImage().setFillParent(true);
        wasdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameControlsConfig.useArrowKeys = false;
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(wasdButton);
    }

    @Override
    public void render(float delta) {
        // clear screen
        ScreenUtils.clear(Color.BLACK);

        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        // draw background
        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.schoolTexture_1, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        // draw stage buttons
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        stage.getViewport().update(screenWidth, screenHeight, true);
    }

    @Override public void show() {

    }

    @Override public void pause() {

    }

    @Override public void resume() {

    }

    @Override public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        menuAssets.dispose();
        backgroundAssets.dispose();
    }
}
