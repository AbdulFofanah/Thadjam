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

    // Constructor takes a texture to create the sprite
    public PositiveEvent(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f); // set sprite size
    }

    // Get bounding box for collision detection
    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    // Draw the item only if it has not been collected
    public void draw(SpriteBatch spriteDrawing) {
        if (!positive_collected) {
            sprite.draw(spriteDrawing);
        }
    }

    // Set position of the item in the world
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
}
