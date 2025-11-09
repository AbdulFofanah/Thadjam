package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Tutorial screen to show instructions to the player
 * Explains controls, goals and tips for the game
 */
public class TutorialScreen implements Screen {

    private final Main game;               // Main game reference
    private OrthographicCamera camera;      // Camera for the viewport
    private FitViewport viewport;           // Viewport to scale the screen
    private Stage stage;                    // Handles buttons and UI

    private MenuAssets menuAssets;          // Menu images and buttons
    private Assets backgroundAssets;        // Background images
    private BitmapFont font;                // Font for text

    // Tutorial messages
    private String[] tutorialText = {
        "Welcome to THADJAM's Escape the Maze Game!",
        "Use the arrow keys or WASD to move around the maze",
        "(Configure in settings) (You have 5 minutes - 300 seconds)",
        "Avoid traps and get boosts",
        "Reach the end by finding the graduation cap to finish Good luck"
    };

    public TutorialScreen(Main game) {
        this.game = game;

        // Set up stage for buttons
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Set up camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();

        // Load assets
        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // Tutorial title button (visual only)
        ImageButton tutorialButton = new ImageButton(new TextureRegionDrawable(menuAssets.tutorialButton));
        tutorialButton.setSize(300, 150);
        tutorialButton.setPosition((viewport.getWorldWidth() / 2f - 500), (viewport.getWorldHeight() - 500f));
        tutorialButton.getImage().setFillParent(true);
        stage.addActor(tutorialButton);

        // Back button to go to main menu
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(menuAssets.backButton));
        backButton.setSize(200, 80);
        backButton.setPosition(50, 50);
        backButton.getImage().setFillParent(true);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game)); // Go back to menu
            }
        });
        stage.addActor(backButton);

        // Set up font for tutorial text
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72; // appropriate for viewport
        parameter.color = Color.WHITE;
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void render(float delta) {

        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        // Update camera
        camera.update();
        viewport.apply();

        // Draw background
        game.SpriteDrawing.setProjectionMatrix(camera.combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.oldSchoolMazeTexture_1, 0, 0,
            viewport.getWorldWidth(), viewport.getWorldHeight());
        game.SpriteDrawing.end();

        // Draw buttons
        stage.act(delta);
        stage.draw();

        // Draw tutorial text
        font.getData().setScale(0.6f);
        GlyphLayout layout = new GlyphLayout(font, String.join("\n\n", tutorialText));
        float x = viewport.getWorldWidth() / 2 - layout.width / 2;
        float y = viewport.getWorldHeight() - 250;

        game.SpriteDrawing.begin();
        font.setColor(Color.RED);
        font.draw(game.SpriteDrawing, layout, x, y);
        game.SpriteDrawing.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
    }


    @Override
    public void dispose() {
        stage.dispose();        // Free UI resources
        menuAssets.dispose();   // Free menu images
        font.dispose();         // Free font
        backgroundAssets.dispose(); // Free background images
    }
}
