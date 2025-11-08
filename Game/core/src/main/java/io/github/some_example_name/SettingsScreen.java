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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {

    private final Main game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage stage;

    private MenuAssets menuAssets;
    private Assets backgroundAssets;

    public SettingsScreen(Main game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();

        // assets
        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // settings title button
        ImageButton settingsButton = new ImageButton(new TextureRegionDrawable(menuAssets.settingsButton));
        settingsButton.setSize(300, 150);
        settingsButton.setPosition((viewport.getWorldWidth() / 2f - 500), (viewport.getWorldHeight() -500f));
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

        // arrow keys button
        ImageButton arrowButton = new ImageButton(new TextureRegionDrawable(menuAssets.arrowButton));
        arrowButton.setSize(400, 150);
        arrowButton.setPosition((viewport.getWorldWidth() / 2f - 900), (viewport.getWorldHeight() -800f));
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
        wasdButton.setPosition((viewport.getWorldWidth() / 2f - 400), (viewport.getWorldHeight() -800f));
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
