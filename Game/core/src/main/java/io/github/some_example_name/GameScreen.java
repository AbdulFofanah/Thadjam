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


public class GameScreen implements Screen {
    private final Main game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private OrthographicCamera HUDcamera;

    private Assets gameAssests;
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
    private BitmapFont font;

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

        //Assets
        menuAssets = new MenuAssets();
        gameAssests = new Assets();
        level = new LevelMap(gameAssests);
        player = new Player(gameAssests.playerRunAnimation, gameAssests.playerIdleAnimation, gameAssests.characterTexture);
        player.setPosition(1,1);

        //Events
        flu = new NegativeEvent(gameAssests.enemyTexture_1);
        flu.setPosition(1, 10);

        coffee = new PositiveEvent(gameAssests.benefitTexture_1);
        coffee.setPosition(17, 20);

        hidden_1 = new HiddenEvent(gameAssests.hiddenTexture_1);
        hidden_1.setPosition(26, 26);

        endSquare = new EndEvent(gameAssests.graduationCapTexture);
        endSquare.setPosition(39, 18);

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
                player.setPosition(1, 1);
                game.setScreen(new MenuScreen(game));
            }

        });

        stage.addActor(backButton);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARIALBD.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 128; //bigger number = higher res font when shrunk
        parameter.color = Color.WHITE;
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float frametime) {

        handleInput(frametime); // This calls player.setMovement()
        player.update(frametime, level.getWallRects()); // updates game logic
        ScreenUtils.clear(Color.BLACK);

        camera.update();
        viewport.apply();
        game.SpriteDrawing.setProjectionMatrix(camera.combined);

        //handling collision between player and the end tile
        if (!endSquare.ending_reached &&
            player.getBoundingRectangle().overlaps(endSquare.getBoundingRectangle())) {
            endSquare.ending_reached = true;
            finalTime = time;
            finalScore = (float) ((time) * 3.1415926);
            game.setScreen(new VictoryScreen(game, finalTime, finalScore));
        }

        //handling collision between player and the flu
        if (!flu.negative_collected &&
            player.getBoundingRectangle().overlaps(flu.getBoundingRectangle())) {
            flu.negative_collected = true;
            player.speed = 3f;
        }

        //handling collision between player and the coffee
        if (!coffee.positive_collected &&
            player.getBoundingRectangle().overlaps(coffee.getBoundingRectangle())) {
            coffee.positive_collected = true;
            player.speed = 9f;
        }

        //handling collision between player and the invisible event
        if (!hidden_1.hidden_collected &&
            player.getBoundingRectangle().overlaps(hidden_1.getBoundingRectangle())) {
            hidden_1.hidden_collected = true;
            Vector2 newPosition = getRandomLocation(level);
            player.setPosition(newPosition.x, newPosition.y);
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
        game.SpriteDrawing.end();

        game.SpriteDrawing.setProjectionMatrix(camera.combined);

        stage.draw();

    }


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

    public void resize(int width, int height){
        viewport.update(width, height, true);
        HUDcamera.setToOrtho(false, width, height);
        HUDcamera.update();
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

    public void dispose(){
        gameAssests.dispose();
        menuAssets.dispose();
        font.dispose(); // dispose font
    }
}
