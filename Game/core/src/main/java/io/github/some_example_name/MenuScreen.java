package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {

    private Stage stage; // holds buttons and input
    private MenuAssets menuAssets; // buttons and icons
    private Assets backgroundAssets; // background images
    private BitmapFont font; // font for title
    private final Main game; // reference to main game
    private String[] titleText = { // menu title text
        "!!!THADJAM's Escape the Maze Game!!!"
    };

    float startX = (Gdx.graphics.getWidth() - 300f) / 2f; // X position for buttons
    float starty = (Gdx.graphics.getHeight() /2f + 50f); // starting Y position for first button
    float gap = 400f; // space between buttons

    public MenuScreen(Main game) {
        this.game = game;

        stage = new Stage(new ScreenViewport()); // make a stage with viewport
        Gdx.input.setInputProcessor(stage); // stage handles input

        menuAssets = new MenuAssets(); // load buttons and icons
        backgroundAssets = new Assets(); // load background images

        // Start Button
        ImageButton startButton = new ImageButton(new TextureRegionDrawable(menuAssets.startButton));
        startButton.setSize(300f, 150f);
        startButton.setPosition(startX, starty);
        startButton.getImage().setFillParent(true);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game)); // go to game screen
            }
        });
        stage.addActor(startButton);

        // Tutorial Button
        ImageButton tutorialButton = new ImageButton(new TextureRegionDrawable(menuAssets.tutorialButton));
        tutorialButton.setSize(300f, 150f);
        tutorialButton.setPosition(startX, starty - gap);
        tutorialButton.getImage().setFillParent(true);
        tutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TutorialScreen(game)); // go to tutorial
            }
        });
        stage.addActor(tutorialButton);

        // Settings Button
        ImageButton settingsButton = new ImageButton(new TextureRegionDrawable(menuAssets.settingsButton));
        settingsButton.setSize(300f, 150f);
        settingsButton.setPosition(startX, starty - 200F);
        settingsButton.getImage().setFillParent(true);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game)); // go to settings
            }
        });
        stage.addActor(settingsButton);

        // Exit Button
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(menuAssets.exitButton));
        exitButton.setSize(300f, 150f);
        exitButton.setPosition(50, 50);
        exitButton.getImage().setFillParent(true);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit(); // close the game
            }
        });
        stage.addActor(exitButton);

        // Font setup for title
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64; // font size
        parameter.color = Color.WHITE; // base color
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        font = generator.generateFont(parameter); // generate font
        generator.dispose(); // free memory
    }

    @Override
    public void show() {
        // called when screen is shown
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK); // clear screen to black

        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        // draw background
        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.classroomTexture_1, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        // draw buttons
        stage.act(delta); // update stage
        stage.draw(); // draw stage actors

        // draw title
        String text = String.join("\n\n", titleText);
        GlyphLayout layout = new GlyphLayout(font, text);

        float x = Gdx.graphics.getWidth() / 2f - layout.width / 2f; // center X
        float y = 700; // fixed Y position

        game.SpriteDrawing.begin();
        font.setColor(Color.RED); // title color
        font.draw(game.SpriteDrawing, layout, x, y);
        game.SpriteDrawing.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true); // update viewport
    }

    @Override public void pause() {}   // Called when game is paused
    @Override public void resume() {}  // Called when game resumes
    @Override public void hide() {}    // Called when screen is hidden

    @Override
    public void dispose() {
        stage.dispose(); // free stage resources
        menuAssets.dispose(); // free menu images
        backgroundAssets.dispose(); // free backgrounds
        font.dispose(); // free font
    }
}
