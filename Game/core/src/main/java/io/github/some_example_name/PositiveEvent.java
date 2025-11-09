package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * PositiveEvent represents a collectible item in the game
 * Player can pick it up and it disappears
 */
public class PositiveEvent {
    private Sprite sprite;              // Sprite for the item
    public boolean positive_collected = false; // true if player has collected it


    /**
     *
     * @param texture
     */
    public PositiveEvent(Texture texture) {
        // Constructor takes a texture to create the sprite
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f); // set sprite size
    }

    /**
     *
     * @return
     */
    public Rectangle getBoundingRectangle() {
        // Get bounding box for collision detection
        return sprite.getBoundingRectangle();
    }

    /**
     *
     * @param spriteDrawing
     */
    public void draw(SpriteBatch spriteDrawing) {
        // Draw the item only if it has not been collected
        if (!positive_collected) {
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
