package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

import java.sql.DataTruncation;

public class Player {
    private Sprite sprite;
    public float speed = 8f;
    private float moveX = 0f;
    private float moveY = 0f;

    private Animation<TextureRegion> playerRunAnimation;
    private Animation<TextureRegion> playerIdleAnimation;
    private float stateTime = 0f;
    private boolean useAnimation = false;
    private boolean moving = false;

    public Player (Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);
    }

    public Player (Animation<TextureRegion> playerRunAnimation, Animation<TextureRegion> playerIdleAnimation, Texture texture) {
        this.sprite = new Sprite(texture);
        this.playerRunAnimation = playerRunAnimation;
        this.playerIdleAnimation = playerIdleAnimation;
        this.sprite.setSize(0.8f, 0.8f);
        this.useAnimation = true;
    }

    public void setMovement(float dx, float dy) {
        moveX = dx;
        moveY = dy;
    }

    public void update(float frametime, Array<Rectangle> wallRects) {
        tryMove(moveX, 0, wallRects); // Try moving horizontally
        tryMove(0, moveY, wallRects); // Try moving vertically

        moving = (moveX != 0 || moveY != 0);

        if (useAnimation) {
            stateTime += frametime;
        }

        //Reset movement by frame
        moveX = 0;
        moveY = 0;
    }

    private void tryMove(float moveX, float moveY, Array<Rectangle> wallRects) {
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
        TextureRegion frame;
        if (useAnimation) {
            if (moving && playerRunAnimation != null) {
                frame = playerRunAnimation.getKeyFrame(stateTime, true);
            } else if (!moving && playerIdleAnimation != null) {
                frame = playerIdleAnimation.getKeyFrame(stateTime, true);
            } else {
                frame = new TextureRegion(sprite.getTexture());
            }

            SpriteDrawing.draw(frame, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        } else {
            sprite.draw(SpriteDrawing);
        }
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



