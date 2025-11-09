package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

/**
 * Player class handles movement, animation and collision
 */
public class Player {
    private Sprite sprite;
    public float speed = 8f;
    private float moveX = 0f;
    private float moveY = 0f;

    private Animation<TextureRegion> playerRunAnimation;  // Animation when player is running
    private Animation<TextureRegion> playerIdleAnimation; // Animation when player is idle
    private float stateTime = 0f;
    private boolean useAnimation = false;
    private boolean moving = false;

    /**
     * Constructor for simple player with single texture
     *
     * @param texture
     */
    public Player(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);  // Set sprite size
    }

    /**
     * Constructor for animated player
     *
     * @param playerRunAnimation
     * @param playerIdleAnimation
     * @param texture
     */
    public Player(Animation<TextureRegion> playerRunAnimation, Animation<TextureRegion> playerIdleAnimation, Texture texture) {
        this.sprite = new Sprite(texture);
        this.playerRunAnimation = playerRunAnimation;
        this.playerIdleAnimation = playerIdleAnimation;
        this.sprite.setSize(0.8f, 0.8f);
        this.useAnimation = true;  // Enable animation
    }

    /**
     * Set how much player wants to move this frame
     *
     * @param dx
     * @param dy
     */
    public void setMovement(float dx, float dy) {
        moveX = dx;
        moveY = dy;
    }

    /**
     * Update player position and animation
     *
     * @param frametime
     * @param wallRects
     */
    public void update(float frametime, Array<Rectangle> wallRects) {
        tryMove(moveX, 0, wallRects); // move horizontally
        tryMove(0, moveY, wallRects); // move vertically

        moving = (moveX != 0 || moveY != 0); // update moving state

        if (useAnimation) {
            stateTime += frametime; // advance animation timer
        }

        // Reset movement after applying it
        moveX = 0;
        moveY = 0;
    }

    /**
     * Try moving player and check collisions
     *
     * @param moveX
     * @param moveY
     * @param wallRects
     */
    private void tryMove(float moveX, float moveY, Array<Rectangle> wallRects) {
        if (moveX == 0 && moveY == 0) return; // no movement

        Rectangle allowedArea = sprite.getBoundingRectangle();
        Rectangle nextArea = new Rectangle(allowedArea.x + moveX, allowedArea.y + moveY,
            allowedArea.width, allowedArea.height);

        // Check collision with walls
        for (Rectangle wall : wallRects) {
            if (nextArea.overlaps(wall)) {
                return; // collision found, stop movement
            }
        }

        // No collision, move player
        sprite.translate(moveX, moveY);
    }

    /**
     * Draw player with sprite or animation
     *
     * @param spriteDrawing
     */
    public void draw(SpriteBatch spriteDrawing) {
        TextureRegion frame;
        if (useAnimation) {
            if (moving && playerRunAnimation != null) {
                frame = playerRunAnimation.getKeyFrame(stateTime, true); // running animation
            } else if (!moving && playerIdleAnimation != null) {
                frame = playerIdleAnimation.getKeyFrame(stateTime, true); // idle animation
            } else {
                frame = new TextureRegion(sprite.getTexture()); // fallback
            }

            spriteDrawing.draw(frame, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        } else {
            sprite.draw(spriteDrawing); // draw plain sprite
        }
    }

    /**
     *
     * @return
     */
    public float getSpeed() {
        // Getter for speed
        return speed;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        // Set position
        sprite.setPosition(x, y);
    }

    /**
     *
      * @return
     */
    public Rectangle getBoundingRectangle() {
        // Get X position
        return sprite.getBoundingRectangle();
    }

    /**
     *
     * @return
     */
    public float getX() {
        // Get X position
        return sprite.getX();
    }

    /**
     *
     * @return
     */
    public float getY() {
        // Get Y position
        return sprite.getY();
    }
}
