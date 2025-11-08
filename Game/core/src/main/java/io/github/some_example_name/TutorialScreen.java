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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TutorialScreen implements Screen {

    private final Main game;
    private Stage stage;
    private MenuAssets menuAssets;
    private Assets backgroundAssets;
    private BitmapFont font;
    private String[] tutorialText = {
        "Welcome to THADJAM's Escape the Maze Game!",
        "Use the arrow keys or WASD to move around the maze.",
        "(Configure in settings)",
        "Avoid traps and get boosts",
        "Reach the end (graduation cap) to finish. Good luck!"
    };

    public TutorialScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // Tutorial button
        ImageButton tutorialButton = new ImageButton(new TextureRegionDrawable(menuAssets.tutorialButton));
        tutorialButton.setSize(600f, 300f);
        tutorialButton.setPosition(
            1200,
            1500);
        tutorialButton.getImage().setFillParent(true);
        stage.addActor(tutorialButton);

        // Back button
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

        //font setup
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 240;
        parameter.color = Color.WHITE;  // make font white so color can be changed at draw time
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.oldSchoolMazeTexture_1, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();
        //draw buttons
        stage.act(delta);
        stage.draw();

        font.getData().setScale(0.4f);

        String text = String.join("\n\n", tutorialText);
        GlyphLayout layout = new GlyphLayout(font, text);

        //text location
        float x = 100; //Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float y = 1500; //Gdx.graphics.getHeight() - 200;

        game.SpriteDrawing.begin();

        font.setColor(Color.RED);
        font.draw(game.SpriteDrawing, layout, x, y);

        game.SpriteDrawing.end();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        font.dispose();
        backgroundAssets.dispose();
    }
}
