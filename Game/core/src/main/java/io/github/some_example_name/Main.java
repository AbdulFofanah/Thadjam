package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.badlogic.gdx.math.Rectangle;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener { //anything under this will declare all the variables
    Texture wallTexture;
    Texture characterTexture;
    Texture floorTexture;
    Music music;

    SpriteBatch spriteBatch;
    FitViewport viewport;

    Sprite characterSprite;

    Array<Rectangle> wallRects;

    //the map, 'W' means a wall, '.' means a floor
    String[] levelMap = {
        "WWWWWWWWWWWWWWWWWWWW",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "W..................W",
        "WWWWWWWWWWWWWWWWWWWW"
    };

    @Override
    public void create() { //anything that is in this method needs adding to the assets with the correct name
        wallTexture = new Texture("brick_brown_0.png");
        characterTexture = new Texture("Run__000.png");
        floorTexture = new Texture("floor_sand_rock_0.png");
        //music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

        spriteBatch = new SpriteBatch();
        // tile amounts, each 1 in width or height is equal to 100x100 pixels
        viewport = new FitViewport(20, 20);

        characterSprite = new Sprite(characterTexture);
        characterSprite.setSize(0.8f, 0.8f);
        characterSprite.setPosition(1, 1);

        //the array will store all the wall pieces for this map
        wallRects = new Array<>();

        //loops through each row, loops through each line in the map, if it finds the 'W' then it'll make it a collision box e.g.the character wont be able to move through it
        for (int row = 0; row < levelMap.length; row++) {
            String line = levelMap[levelMap.length - 1 - row]; // flip Y axis
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) == 'W') {
                    wallRects.add(new Rectangle(col, row, 1, 1));
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    private void input() {
        float speed = 8f;
        float delta = Gdx.graphics.getDeltaTime();

        //will check whether the character is moving left or right
        float moveX = 0;
        float moveY = 0;

        //updates the moving variables based on the input for the character
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveX += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveX -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveY += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveY -= speed * delta;
        }

        //the method will check whether moving will cause a collision into a wall or not
        tryMove(moveX, 0);
        tryMove(0, moveY);
    }

    private void tryMove(float moveX, float moveY) {
        if (moveX == 0 && moveY == 0) return;

        //generates a rectangle for the next square that the character could possibly move into (N, S, E, W from the sprite)
        Rectangle next = new Rectangle(characterSprite.getX() + moveX, characterSprite.getY() + moveY, characterSprite.getWidth(), characterSprite.getHeight());

        //stops the movement cos the wall has been hit e.g.if the rectangles overlap
        for (Rectangle wall : wallRects) {
            if (next.overlaps(wall)) {
                return;
            }
        }

        //not colliding, keeping character moving
        characterSprite.translate(moveX, moveY);
    }

    private void logic() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        float characterWidth = characterSprite.getWidth();
        float characterHeight = characterSprite.getHeight();

        characterSprite.setX(MathUtils.clamp(characterSprite.getX(), 0, worldWidth - characterWidth));
        characterSprite.setY(MathUtils.clamp(characterSprite.getY(), 0, worldHeight - characterHeight));
    }

    private void draw() {//anything in this method will be drawn onto the s creen as long as its between the begin and end
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        //spriteBatch.draw(wallTexture, 0, 0, 1, 1);//make sure the background is always at the top as it is layered
        //spriteBatch.draw(floorTexture, 1, 1, 1, 1);

        //draw floors and walls based on the map e.g. loops through the rows and columns of the map and draws the floors if there is a '.' and a wall if there is a '.'
        for (int row = 0; row < levelMap.length; row++) {
            String line = levelMap[levelMap.length - 1 - row];
            for (int col = 0; col < line.length(); col++) {
                spriteBatch.draw(floorTexture, col, row, 1, 1);
                if (line.charAt(col) == 'W') {
                    spriteBatch.draw(wallTexture, col, row, 1, 1);
                }
            }
        }

        characterSprite.draw(spriteBatch);

        spriteBatch.end();
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
    }
}
