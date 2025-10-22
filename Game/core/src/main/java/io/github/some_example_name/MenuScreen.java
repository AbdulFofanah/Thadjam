package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {

    private Stage stage;
    private MenuAssets assets;
    private SpriteBatch batch;

    public MenuScreen() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        assets = new MenuAssets();

        // Create Start Button
        ImageButton startButton = new ImageButton(
            new TextureRegionDrawable(assets.startButton)
        );
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f + 50);
        stage.addActor(startButton);

        // Create Settings Button
        ImageButton settingsButton = new ImageButton(
            new TextureRegionDrawable(assets.settingsButton)
        );
        settingsButton.setPosition(Gdx.graphics.getWidth() / 2f - settingsButton.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - 50);
        stage.addActor(settingsButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Clear screen with a color
        ScreenUtils.clear(Color.DARK_GRAY);

        stage.act(delta);
        stage.draw();
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
        batch.dispose();
        assets.dispose();
    }
}
