package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class GameScreen implements Screen {
    private final Main game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Assets assets;
    private LevelMap level;
    private Player player;
    private Stage stage;
    private NegativeEvent flu;
    private PositiveEvent coffee;
    private HiddenEvent hidden_1;

    public GameScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        viewport = new FitViewport(60,40, camera);
        assets = new Assets();
        level = new LevelMap(assets);
        player = new Player(assets.characterTexture);
        player.setPosition(1,1);
        flu = new NegativeEvent(assets.enemyTexture_1);
        flu.setPosition(1, 10);
        coffee = new PositiveEvent(assets.benefitTexture_1);
        coffee.setPosition(17, 20);
        hidden_1 = new HiddenEvent(assets.hiddenTexture_1);
        hidden_1.setPosition(26, 26);
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

        //handling collision between player and the flu
        if (!flu.negative_collected && player.getBoundingRectangle().overlaps(flu.getBoundingRectangle())) {
            flu.negative_collected = true;
            player.speed = 3f;
        }

        //handling collision between player and the coffee
        if (!coffee.positive_collected && player.getBoundingRectangle().overlaps(coffee.getBoundingRectangle())) {
            coffee.positive_collected = true;
            player.speed = 9f;
        }

        //handling collision between player and the invisible event
        if (!hidden_1.hidden_collected && player.getBoundingRectangle().overlaps(hidden_1.getBoundingRectangle())) {
            hidden_1.hidden_collected = true;
            Vector2 newPosition = getRandomLocation(level);
            player.setPosition(newPosition.x, newPosition.y);
        }


        game.SpriteDrawing.begin(); // Draw
        level.draw(game.SpriteDrawing);
        player.draw(game.SpriteDrawing);
        flu.draw(game.SpriteDrawing);
        coffee.draw(game.SpriteDrawing);
        hidden_1.draw(game.SpriteDrawing);
        game.SpriteDrawing.end();
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
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) moveY = speed * delta; // Y move
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))moveY = -speed * delta; //  move
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveX = -speed * delta; // X move
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveX = speed * delta; //  X move

        player.setMovement(moveX, moveY);
    }

    public void resize(int width, int height){
        viewport.update(width, height, true);
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
        assets.dispose();
        font.dispose(); // dispose font
    }
}
