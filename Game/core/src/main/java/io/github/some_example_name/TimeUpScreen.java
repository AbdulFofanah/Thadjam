package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Screen shown when the player runs out of time
 * Displays failure message and back button to menu
 */
public class TimeUpScreen implements Screen {
    private Stage stage;                 // Handles UI elements like buttons
    private MenuAssets menuAssets;       // Menu images and buttons
    private Assets backgroundAssets;     // Background images
    private final Main game;             // Main game reference
    private BitmapFont font;             // Font for text

    // Messages to show when time is up
    private String[] lostText = {
        "!!The timer passed 5 minutes (300 Seconds)!!!",
        "!!!You Failed!!!"
    };

    /**
     *
     * @param game
     */
    public TimeUpScreen(Main game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load assets
        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // Home button to go back to menu
        ImageButton homeButton = new ImageButton(new TextureRegionDrawable(menuAssets.homeButton));
        homeButton.setSize(300f, 150f);
        homeButton.setPosition(50, 25);
        homeButton.getImage().setFillParent(true);

        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game)); // Go back to menu
            }
        });

        stage.addActor(homeButton);

        // Font setup for failure message
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 128;              // Size for viewport
        parameter.color = Color.WHITE;     // Make font white (color can change when drawing)
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();               // Free font generator
    }

    @Override
    public void show() {
        // Called when this screen is shown
    }

    /**
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        // Draw background
        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.failureTexture, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        // Draw buttons
        stage.act(delta);
        stage.draw();

        // Draw failure message
        font.getData().setScale(0.4f);
        String text = String.join("\n\n", lostText);
        GlyphLayout layout = new GlyphLayout(font, text);

        float x = Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float y = 700;

        game.SpriteDrawing.begin();
        font.setColor(Color.RED);
        font.draw(game.SpriteDrawing, layout, x, y);
        game.SpriteDrawing.end();
    }

    /**
     *
     * @param screenWidth
     * @param screenHeight
     */
    @Override
    public void resize(int screenWidth, int screenHeight) {
        stage.getViewport().update(screenWidth, screenHeight, true);
    }

    @Override public void pause() {}   // Called when game is paused
    @Override public void resume() {}  // Called when game resumes
    @Override public void hide() {}    // Called when screen is hidden

    /**
     *
     */
    @Override
    public void dispose() {
        stage.dispose();             // Free UI elements
        menuAssets.dispose();        // Free menu images
        backgroundAssets.dispose();  // Free background images
    }
}
