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
 * Victory screen shown when the player wins
 * Displays the final stats and a button to return to the main menu
 */
public class VictoryScreen implements Screen {

    private Stage stage;                 // Handles UI elements like buttons
    private MenuAssets menuAssets;       // Stores menu images and textures
    private Assets backgroundAssets;     // Stores background image
    private final Main game;             // Main game reference
    private BitmapFont font;             // Font used to draw text
    private float finalTime;             // Stores the final time
    private float finalScore;            // Stores the final score
    private int finalCounter;            // Stores how many events were found
    private String[] wonText = {
        "!!!You won and graduated!!!"
    };

    public VictoryScreen(Main game, float finalScore, float finalTime, int finalCounter) {
        this.game = game;
        this.finalTime = finalTime;
        this.finalScore = finalScore;
        this.finalCounter = finalCounter;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // Create home button to go back to main menu
        ImageButton homeButton = new ImageButton(new TextureRegionDrawable(menuAssets.homeButton));
        homeButton.setSize(300f, 150f);
        homeButton.setPosition(50, 25);
        homeButton.getImage().setFillParent(true);

        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game)); // Switch to menu
            }
        });

        stage.addActor(homeButton);

        // Set up font for the victory text
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 240;
        parameter.color = Color.WHITE;
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() {
        // Runs when this screen is shown
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        // Draw background image
        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.graduationTexture, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        // Draw stage (UI elements)
        stage.act(delta);
        stage.draw();

        font.getData().setScale(0.4f);

        // Main victory message
        String text = String.join("\n\n", wonText);
        GlyphLayout layout = new GlyphLayout(font, text);
        float x = Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float y = 700;

        game.SpriteDrawing.begin();
        font.setColor(Color.GREEN);
        font.draw(game.SpriteDrawing, layout, x, y);
        game.SpriteDrawing.end();

        // Show score and time
        String statsText = String.format("Final Time: %.2f Seconds\nFinal Score: %.2f Points\nWell Done!", finalTime, finalScore);
        GlyphLayout statsLayout = new GlyphLayout(font, statsText);
        float statsX = Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float statsY = 600;

        game.SpriteDrawing.begin();
        font.setColor(Color.GREEN);
        font.draw(game.SpriteDrawing, statsLayout, statsX, statsY);
        game.SpriteDrawing.end();

        // Show number of events found
        String countersText = String.format("You found " + finalCounter + " out of 3 events!");
        GlyphLayout countersLayout = new GlyphLayout(font, countersText);
        float countersX = 50;
        float countersY = 250;

        font.getData().setScale(0.4f);
        game.SpriteDrawing.begin();
        font.setColor(Color.GREEN);
        font.draw(game.SpriteDrawing, countersLayout, countersX, countersY);
        game.SpriteDrawing.end();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        // Updates viewport when window size changes
        stage.getViewport().update(screenWidth, screenHeight, true);
    }

    @Override
    public void pause() {
        // Called when the game is paused
    }

    @Override
    public void resume() {
        // Called when the game is resumed
    }

    @Override
    public void hide() {
        // Called when this screen is hidden
    }

    @Override
    public void dispose() {
        // Frees memory when this screen is closed
        stage.dispose();
        menuAssets.dispose();
        backgroundAssets.dispose();
    }
}
