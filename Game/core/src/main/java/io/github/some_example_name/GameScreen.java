package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public GameScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        viewport = new FitViewport(60,40, camera);
        assets = new Assets();
        level = new LevelMap(assets);
        player = new Player(assets.characterTexture, level.getEnemyRects());
        player.setPosition(1,1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float frametime) {
        handleInput(frametime); // This calls player.setMovement()
        player.update(frametime, level.getWallRects(), level.getEnemyRects()); // updates game logic
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        viewport.apply();
        game.SpriteDrawing.setProjectionMatrix(camera.combined);
        game.SpriteDrawing.begin(); // Draw
        level.draw(game.SpriteDrawing);
        player.draw(game.SpriteDrawing);
        game.SpriteDrawing.end();
    }

    private void handleInput(float delta) {
        float moveX = 0;
        float moveY = 0;
        float speed = player.getSpeed(); // Get speed from player

        // updates the moving variables based on the input for the character
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveY = speed * delta; // Y move
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveY = -speed * delta; //  move
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveX = -speed * delta; // X move
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveX = speed * delta; //  X move
        }


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
    }
}
