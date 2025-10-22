package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Sprite sprite;
    private float speed = 8f;
    private Array<Rectangle> enemyRects;

    public Player (Texture texture, Array<Rectangle> enemyRects) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);
        this.sprite.setPosition(1, 1);
        this.enemyRects = enemyRects;
    }

    public void handleInput(float worldWidth, float worldHeight, Array<Rectangle> wallRects) {
        float delta = Gdx.graphics.getDeltaTime();
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

        tryMove(moveX, 0, wallRects);
        tryMove(0, moveY, wallRects);
    }
    private void tryMove(float moveX, float moveY, Array<Rectangle> wallRects) {
        if (moveX == 0 && moveY == 0) return;

        //generates a rectangle for the next square that the character could possibly move into (N, S, E, W from the sprite)
        Rectangle next = new Rectangle(sprite.getX() + moveX, sprite.getY() + moveY, sprite.getWidth(), sprite.getHeight());

        //stops the movement cos the wall has been hit e.g.if the rectangles overlap
        for (Rectangle wall : wallRects) {
            if (next.overlaps(wall)) {
                return;
            }
        }

        Rectangle futureX = new Rectangle(sprite.getX() + moveX, sprite.getY(), sprite.getWidth(), sprite.getHeight());
        Rectangle futureY = new Rectangle(sprite.getX(), sprite.getY() + moveY, sprite.getWidth(), sprite.getHeight());

        for (Rectangle enemy : enemyRects) {
            if (futureX.overlaps(enemy)) {
                return;
            }
        }

        for (Rectangle enemy : enemyRects) {
            if (futureY.overlaps(enemy)) {
                return;
            }
        }

        //not colliding, keeping character moving
        sprite.translate(moveX, moveY);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
