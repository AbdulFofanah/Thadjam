package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * NegativeEvent represents a harmful item in the game
 * Player can collide with it and it disappears
 */
public class NegativeEvent {
    private Sprite sprite;
    public boolean negative_collected = false;


    /**
     * Constructor takes a texture to create the sprite
     *
     * @param texture
     */
    public NegativeEvent(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f); // set sprite size
    }

    /**
     * Get bounding box for collision detection
     *
     * @return
     */
    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    /**
     * Draw the negative item only if it has not been collected
     *
     * @param spriteDrawing
     */
    public void draw(SpriteBatch spriteDrawing) {
        if (!negative_collected) {
            sprite.draw(spriteDrawing);
        }
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        // Set position of the item in the world
        sprite.setPosition(x, y);
    }
}
