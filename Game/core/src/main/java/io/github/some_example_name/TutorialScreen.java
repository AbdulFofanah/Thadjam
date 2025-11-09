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

public class TutorialScreen implements Screen {

    private final Main game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage stage;

    private MenuAssets menuAssets;
    private Assets backgroundAssets;
    private BitmapFont font;

    private String[] tutorialText = {
        "Welcome to THADJAM's Escape the Maze Game!",
        "Use the arrow keys or WASD to move around the maze.",
        "(Configure in settings) (You have 5 minutes - 300 seconds)",
        "Avoid traps and get boosts",
        "Reach the end by finding the graduation cap to finish. Good luck!"
    };

    public TutorialScreen(Main game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();

        // load assets
        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();

        // tutorial title
        ImageButton tutorialButton = new ImageButton(new TextureRegionDrawable(menuAssets.tutorialButton));
        tutorialButton.setSize(300, 150); // smaller relative to viewport
        tutorialButton.setPosition((viewport.getWorldWidth() / 2f - 500), (viewport.getWorldHeight() -500f));
        tutorialButton.getImage().setFillParent(true);
        stage.addActor(tutorialButton);
        //

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

        // font setup
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

        // clear screen
        ScreenUtils.clear(Color.BLACK);

        // update camera
        camera.update();
        viewport.apply();

        // draw background
        game.SpriteDrawing.setProjectionMatrix(camera.combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.oldSchoolMazeTexture_1, 0, 0,
            viewport.getWorldWidth(), viewport.getWorldHeight());
        game.SpriteDrawing.end();

        // draw buttons
        stage.act(delta);
        stage.draw();

        font.getData().setScale(0.6f);

        // draw tutorial text
        GlyphLayout layout = new GlyphLayout(font, String.join("\n\n", tutorialText));
        float x = viewport.getWorldWidth() / 2 - layout.width / 2 ;
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

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        menuAssets.dispose();
        font.dispose();
        backgroundAssets.dispose();
    }
}
