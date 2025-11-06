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
    private MenuAssets assets;
    private BitmapFont font;
    private String[] tutorialText = {
        "Welcome to THADJAM!",
        "Use the arrow keys or WASD to move around the maze. (Configure in settings)",
        "Avoid traps and get boosts",
        "Reach the end to finish. Good luck!"
    };

    public TutorialScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        assets = new MenuAssets();

        // Tutorial button
        ImageButton tutorialButton = new ImageButton(new TextureRegionDrawable(assets.tutorialButton));
        tutorialButton.setPosition(
            Gdx.graphics.getWidth() / 2f - tutorialButton.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f + 250
        );
        stage.addActor(tutorialButton);

        // Back button
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(assets.backButton));
        backButton.setPosition(100, 100);
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
        parameter.size = 64;
        parameter.color = Color.WHITE;  // make font white so color can be changed at draw time
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.GRAY);

        //draw buttons
        stage.act(delta);
        stage.draw();

        font.getData().setScale(0.4f);

        String text = String.join("\n\n", tutorialText);
        GlyphLayout layout = new GlyphLayout(font, text);

        //text location
        float x = Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float y = Gdx.graphics.getHeight() - 200;

        game.SpriteDrawing.begin();

        font.setColor(Color.BLUE);
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
        assets.dispose();
        font.dispose();
    }
}
