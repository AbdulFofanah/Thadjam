package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener { //anything under this will declare all the variables
    private Screen currentScreen;

    @Override
    public void create() { //anything that is in this method needs adding to the assets with the correct name
        currentScreen = new MenuScreen();

    }
    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        currentScreen.resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
        currentScreen.dispose();
    }
}
