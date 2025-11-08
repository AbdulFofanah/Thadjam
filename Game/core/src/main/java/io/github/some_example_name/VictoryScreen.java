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

public class VictoryScreen implements Screen {

    private Stage stage;
    private MenuAssets menuAssets;
    private Assets backgroundAssets;
    private final Main game;
    private BitmapFont font;
    private float finalTime;
    private float finalScore;
    private String[] wonText = {
        "!!!You won and graduated!!!"
    };

    public VictoryScreen(Main game, float finalScore,  float finalTime) {
        this.game = game;
        this.finalTime = finalTime;
        this.finalScore = finalScore;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuAssets = new MenuAssets();
        backgroundAssets = new Assets();


        // Create Settings Button
        ImageButton homeButton = new ImageButton(new TextureRegionDrawable(menuAssets.homeButton));
        homeButton.setSize(300f, 150f);
        homeButton.setPosition(
            50,
            50);
        homeButton.getImage().setFillParent(true);

        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }

        });

        stage.addActor(homeButton);

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
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        game.SpriteDrawing.setProjectionMatrix(stage.getCamera().combined);
        game.SpriteDrawing.begin();
        game.SpriteDrawing.draw(backgroundAssets.graduationTexture, 0, 0, screenWidth, screenHeight);
        game.SpriteDrawing.end();

        stage.act(delta);
        stage.draw();

        font.getData().setScale(0.4f);

        String text = String.join("\n\n", wonText);
        GlyphLayout layout = new GlyphLayout(font, text);


        //text location
        float x = Gdx.graphics.getWidth() / 2f - layout.width / 2f; //Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float y = 700; //Gdx.graphics.getHeight() - 200;

        game.SpriteDrawing.begin();

        font.setColor(Color.GREEN);
        font.draw(game.SpriteDrawing, layout, x, y);

        game.SpriteDrawing.end();
        String statsText = String.format("Final Time: " + (float) (finalTime)%.2f
                         + "\nFinal Score: " + (float) (finalScore)%.2f
                         + "\nWell Done!", finalTime, finalScore);
        GlyphLayout statsLayout = new GlyphLayout(font, statsText);

        //text location
        float statsX = Gdx.graphics.getWidth() / 2f - layout.width / 2f; //Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float statsY = 600; //Gdx.graphics.getHeight() - 200;

        game.SpriteDrawing.begin();

        font.setColor(Color.GREEN);
        font.draw(game.SpriteDrawing, statsLayout, statsX, statsY);

        game.SpriteDrawing.end();
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        stage.getViewport().update(screenWidth, screenHeight, true);
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
    }
}
