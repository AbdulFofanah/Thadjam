package io.github.some_example_name;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    private FitViewport viewport;
    private SpriteBatch batch;
    private Assets assets;
    private LevelMap level;
    private Player player;

    public GameScreen() {
        batch = new SpriteBatch();
        viewport = new FitViewport(20, 20);
        assets = new Assets();
        level = new LevelMap(assets);
        player = new Player(assets.characterTexture, level.getEnemyRects());
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen
    }

    @Override
    public void render(float delta) {
        player.handleInput(viewport.getWorldWidth(), viewport.getWorldHeight(), level.getWallRects());

        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        level.draw(batch);
        player.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        assets.dispose();
    }
}
