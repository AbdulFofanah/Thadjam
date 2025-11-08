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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {

    private Stage stage;
    private MenuAssets menuAssets;
    private Assets backgroundAssets;
    private BitmapFont font;
    private final Main game;
    private String[] titleText = {
        "!!!THADJAM's Escape the Maze Game!!!"
    };

    float startX = (Gdx.graphics.getWidth() - 300f) / 2f;
    float starty = (Gdx.graphics.getHeight() /2f + 150f);
    float gap = 400f;


    public MenuScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        float centerX = (Gdx.graphics.getWidth()) / 2f;
        float startY = Gdx.graphics.getHeight() / 2f;

        // Create Start Button
        ImageButton startButton = new ImageButton(new TextureRegionDrawable(menuAssets.startButton));
        startButton.setSize(300f, 150f);
        startButton.setPosition(startX, starty);
        startButton.getImage().setFillParent(true);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               game.setScreen(new GameScreen(game));
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
                game.setScreen(new TutorialScreen(game));
            }
        });
        stage.addActor(tutorialButton);

        // Create Settings Button
        ImageButton settingsButton = new ImageButton(new TextureRegionDrawable(menuAssets.settingsButton));
        settingsButton.setSize(300f, 150f);
        settingsButton.setPosition(startX, starty - 200F);
        settingsButton.getImage().setFillParent(true);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game));
            }
        });

        stage.addActor(settingsButton);

        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(menuAssets.exitButton));
        exitButton.setSize(300f, 150f);
        exitButton.setPosition(
            50,
            50);
        exitButton.getImage().setFillParent(true);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(exitButton);

        //font setup
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 128;
        parameter.color = Color.WHITE;  // make font white so color can be changed at draw time
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Clear screen with a color
        ScreenUtils.clear(Color.BLACK);
        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.classroomTexture_1, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        stage.act(delta);
        stage.draw();

        String text = String.join("\n\n", titleText);
        GlyphLayout layout = new GlyphLayout(font, text);

        //text location
        float x = Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float y = 1800; //Gdx.graphics.getHeight() - 200;

        game.SpriteDrawing.begin();

        font.setColor(Color.RED);
        font.draw(game.SpriteDrawing, layout, x, y);

        game.SpriteDrawing.end();

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
        menuAssets.dispose();
        backgroundAssets.dispose();
        font.dispose();
    }
}
