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

/**
 * Settings screen to choose controls and go back to menu
 */
public class SettingsScreen implements Screen {

    private final Main game;              // Reference to main game
    private OrthographicCamera camera;     // Camera for viewport
    private FitViewport viewport;          // Scale screen to fit
    private Stage stage;                   // Handles buttons and UI

    private MenuAssets menuAssets;         // Menu images and buttons
    private Assets backgroundAssets;       // Background images

    /**
     *
     * @param game
     */
    public SettingsScreen(Main game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();

        // Load images for buttons and background
        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // settings title button (visual only)
        ImageButton settingsButton = new ImageButton(new TextureRegionDrawable(menuAssets.settingsButton));
        settingsButton.setSize(300, 150);
        settingsButton.setPosition((viewport.getWorldWidth() / 2f - 500), (viewport.getWorldHeight() - 500f));
        settingsButton.getImage().setFillParent(true);
        stage.addActor(settingsButton);

        // back button to go back to menu
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(menuAssets.backButton));
        backButton.setSize(200, 80);
        backButton.setPosition(50, 50);
        backButton.getImage().setFillParent(true);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game)); // go back to menu
            }
        });
        stage.addActor(backButton);

        // arrow keys button to use arrows
        ImageButton arrowButton = new ImageButton(new TextureRegionDrawable(menuAssets.arrowButton));
        arrowButton.setSize(400, 150);
        arrowButton.setPosition((viewport.getWorldWidth() / 2f - 900), (viewport.getWorldHeight() - 800f));
        arrowButton.getImage().setFillParent(true);
        arrowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameControlsConfig.useArrowKeys = true; // set controls to arrows
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(arrowButton);

        // WASD button to use WASD keys
        ImageButton wasdButton = new ImageButton(new TextureRegionDrawable(menuAssets.wasdButton));
        wasdButton.setSize(400, 150);
        wasdButton.setPosition((viewport.getWorldWidth() / 2f - 400), (viewport.getWorldHeight() - 800f));
        wasdButton.getImage().setFillParent(true);
        wasdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameControlsConfig.useArrowKeys = false; // set controls to WASD
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(wasdButton);
    }

    /**
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Clear screen
        ScreenUtils.clear(Color.BLACK);

        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        // Draw background
        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.schoolTexture_1, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        // Draw buttons
        stage.act(delta);
        stage.draw();
    }

    /**
     *
     * @param screenWidth
     * @param screenHeight
     */
    @Override
    public void resize(int screenWidth, int screenHeight) {
        viewport.update(screenWidth, screenHeight, true);
        stage.getViewport().update(screenWidth, screenHeight, true);
    }

    @Override public void show() {}    // Called when screen is shown
    @Override public void pause() {}   // Called when game is paused
    @Override public void resume() {}  // Called when game resumes
    @Override public void hide() {}    // Called when screen is hidden

    /**
     *
     */
    @Override
    public void dispose() {
        stage.dispose();             // free UI elements
        menuAssets.dispose();        // free menu images
        backgroundAssets.dispose();  // free background images
    }
}
