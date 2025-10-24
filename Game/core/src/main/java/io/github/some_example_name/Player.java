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
    public float speed = 8f;
    private float moveX = 0f;
    private float moveY = 0f;

    public Player (Texture texture, Array<Rectangle> enemyRects) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);
    }

    public void setMovement(float dx, float dy) {
        moveX = dx;
        moveY = dy;
    }

    public void update(float frametime, Array<Rectangle> wallRects, Array<Rectangle> enemyRects) {
        tryMove(moveX, 0, wallRects, enemyRects); // Try moving horizontally
        tryMove(0, moveY, wallRects, enemyRects); // Try moving vertically
        //Reset movement by frame
        moveX = 0;
        moveY = 0;
    }

    private void tryMove(float moveX, float moveY, Array<Rectangle> wallRects, Array<Rectangle> enemyRects) {
        if (moveX == 0 && moveY == 0) return;

        Rectangle AllowedArea = sprite.getBoundingRectangle();
        Rectangle NextArea = new Rectangle(AllowedArea.x + moveX, AllowedArea.y + moveY,
                                              AllowedArea.width, AllowedArea.height);

        // Collision detection
        for (Rectangle wall : wallRects) {
            if (NextArea.overlaps(wall)) {
                return; // collided
            }
        }

        //not colliding, keeping character moving
        sprite.translate(moveX, moveY);
    }

    public void draw(SpriteBatch SpriteDrawing) {
        sprite.draw(SpriteDrawing);
    }

    // Speed Getter
    public float getSpeed() {
        return speed;
    }

    // Pos Setter
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    // current pos
    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    public float getX() {return sprite.getX();}

    public float getY() {return sprite.getY();}
}



