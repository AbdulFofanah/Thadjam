package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Draws all of the assets into the gamescreen, handles some collisions for events, sets the cameras for each screen,
 * sets up the buttons for each screen, handles user inputs, and the sets the sizing for some assets
 */
public class GameScreen implements Screen {
    private final Main game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private OrthographicCamera HUDcamera;

    private Assets gameAssets;
    private MenuAssets menuAssets;
    private LevelMap level;
    private Player player;
    private Stage stage;
    private NegativeEvent flu;
    private PositiveEvent coffee;
    private HiddenEvent hidden_1;
    private EndEvent endSquare;

    private float time;
    private float finalTime;
    public float finalScore;
    private int negativeCount;
    private int positiveCount;
    private int hiddenCount;
    public int finalCounter;
    private BitmapFont font;

    /**
     *
     * @param game
     */
    public GameScreen(Main game) {
        this.game = game;
        this.finalTime = finalTime;
        this.finalScore = finalScore;


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Main camera
        camera = new OrthographicCamera();
        viewport = new FitViewport(65,40, camera);

        //Hud camera
        HUDcamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        HUDcamera.setToOrtho(false); // y=0 bottom, y=height top
        HUDcamera.update();

        //gameAssets/Assets
        menuAssets = new MenuAssets();
        gameAssets = new Assets();
        level = new LevelMap(gameAssets);
        player = new Player(gameAssets.playerRunAnimation, gameAssets.playerIdleAnimation, gameAssets.characterTexture);
        player.setPosition(1,1);

        //Events
        flu = new NegativeEvent(gameAssets.enemyTexture_1);
        flu.setPosition(1, 10);
        //lu.setPosition(1, 8);

        coffee = new PositiveEvent(gameAssets.benefitTexture_1);
        coffee.setPosition(17, 20);
        //coffee.setPosition(1, 9);

        hidden_1 = new HiddenEvent(gameAssets.hiddenTexture_1);
        hidden_1.setPosition(26, 26);
        //hidden_1.setPosition(1, 10);

        endSquare = new EndEvent(gameAssets.graduationCapTexture);
        endSquare.setPosition(39, 18);
        //endSquare.setPosition(1, 11);

        //back button
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(menuAssets.backButton));
        backButton.setSize(100f, 25f);
        backButton.setPosition(
            -20,
            -5);
        backButton.getImage().setFillParent(true);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                time = 0f;
                player.setPosition(10, 30);
                game.setScreen(new MenuScreen(game));
            }

        });

        stage.addActor(backButton);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64; //bigger number = higher res font when shrunk
        parameter.color = Color.WHITE;
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     *
     */
    @Override
    public void show() {
    }

    /**
     *
     * @param frametime The time in seconds since the last render.
     */
    @Override
    public void render(float frametime) {

        handleInput(frametime); // This calls player.setMovement()
        player.update(frametime, level.getWallRects()); // updates game logic
        ScreenUtils.clear(Color.BLACK);

        camera.update();
        viewport.apply();
        game.SpriteDrawing.setProjectionMatrix(camera.combined);

        if (time >= 300f) { // 5 minutes (300 seconds)
            game.setScreen(new TimeUpScreen(game));
            return;
        }

        //handling collision between player and the end tile
        if (!endSquare.ending_reached &&
            player.getBoundingRectangle().overlaps(endSquare.getBoundingRectangle())) {
            endSquare.ending_reached = true;
            finalTime = time;
            finalScore = (float) ((3.1415926 / (time)) * 1500);
            game.setScreen(new VictoryScreen(game, finalScore, finalTime, finalCounter));
        }

        //handling collision between player and the flu
        if (!flu.negative_collected &&
            player.getBoundingRectangle().overlaps(flu.getBoundingRectangle())) {
            flu.negative_collected = true;
            player.speed = 3f;
            negativeCount++;
            finalCounter++;
        }

        //handling collision between player and the coffee
        if (!coffee.positive_collected &&
            player.getBoundingRectangle().overlaps(coffee.getBoundingRectangle())) {
            coffee.positive_collected = true;
            player.speed = 9f;
            positiveCount++;
            finalCounter++;
        }

        //handling collision between player and the invisible event
        if (!hidden_1.hidden_collected &&
            player.getBoundingRectangle().overlaps(hidden_1.getBoundingRectangle())) {
            hidden_1.hidden_collected = true;
            Vector2 newPosition = getRandomLocation(level);
            player.setPosition(newPosition.x, newPosition.y);
            hiddenCount++;
            finalCounter++;
        }

        time += frametime;

        // Draw the world
        game.SpriteDrawing.begin();
        level.draw(game.SpriteDrawing);
        player.draw(game.SpriteDrawing);
        flu.draw(game.SpriteDrawing);
        coffee.draw(game.SpriteDrawing);
        //hidden_1.draw(game.SpriteDrawing);
        endSquare.draw(game.SpriteDrawing);
        game.SpriteDrawing.end();

        game.SpriteDrawing.begin();
        game.SpriteDrawing.setProjectionMatrix(HUDcamera.combined);

        //font size
        font.getData().setScale(0.6f);

        String timeText = "Time: " + (int) time + "s";
        GlyphLayout layout = new GlyphLayout(font, timeText);

        //timer location
        float x = Gdx.graphics.getWidth() / 2f - layout.width / 2f;
        float y = Gdx.graphics.getHeight() - 20;

        font.draw(game.SpriteDrawing, layout, x, y);


        String countersText = String.format("Negative Events: %d / 1\nPositive Events: %d / 1" +
                                            "\nHidden Events: %d / 1 \nTotal events %d" ,
                                            negativeCount, positiveCount, hiddenCount, finalCounter);
        GlyphLayout countersLayout = new GlyphLayout(font, countersText);
        float countersX = (Gdx.graphics.getWidth() / 2f - layout.width / 2f) + 250;
        float countersY = Gdx.graphics.getHeight() - 20;
        font.getData().setScale(0.4f);
        font.draw(game.SpriteDrawing, countersLayout, countersX, countersY);

        game.SpriteDrawing.end();

        game.SpriteDrawing.setProjectionMatrix(camera.combined);

        stage.draw();
    }

    /**
     *
     * @param level
     * @return
     */
    private Vector2 getRandomLocation(LevelMap level) {
        int rows = viewport.getScreenWidth();
        int columns = viewport.getScreenHeight();

        while(true) {
            int x = MathUtils.random(0, columns - 1);
            int y = MathUtils.random(0, rows - 1);

            if (level.isWalkable(x, y)) {
                return new Vector2(x, y);
            }
        }
    }

    /**
     *
     * @param delta
     */
    private void handleInput(float delta) {
        float moveX = 0;
        float moveY = 0;
        float speed = player.getSpeed(); // Get speed from player

        // updates the moving variables based on the input for the character
        // checks the setting to see if arrows keys is selected, else its wasd keys
        if (GameControlsConfig.useArrowKeys) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP))
                moveY = speed * delta; // Y move
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
                moveY = -speed * delta; //  move
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                moveX = -speed * delta; // X move
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                moveX = speed * delta; //  X move
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.W))
                moveY = speed * delta; // Y move
            if (Gdx.input.isKeyPressed(Input.Keys.S))
                moveY = -speed * delta; //  move
            if (Gdx.input.isKeyPressed(Input.Keys.A))
                moveX = -speed * delta; // X move
            if (Gdx.input.isKeyPressed(Input.Keys.D))
                moveX = speed * delta; //  X move
        }
        player.setMovement(moveX, moveY);
    }

    /**
     *
     * @param width
     * @param height
     */
    public void resize(int width, int height){
        viewport.update(width, height, true);
        HUDcamera.setToOrtho(false, width, height);
        HUDcamera.update();
    }

    /**
     *
     */
    @Override
    public void pause() {
    }

    /**
     *
     */
    @Override
    public void resume() {
    }

    /**
     *
     */
    @Override
    public void hide() {
    }

    /**
     *
     */
    public void dispose(){
        gameAssets.dispose();
        menuAssets.dispose();
        font.dispose(); // dispose font
    }
}
