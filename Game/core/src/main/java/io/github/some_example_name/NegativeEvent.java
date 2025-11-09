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
    private Sprite sprite;                 // Sprite for the negative item
    public boolean negative_collected = false; // true if player has hit it

    // Constructor takes a texture to create the sprite
    public NegativeEvent(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f); // set sprite size
    }

    // Get bounding box for collision detection
    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    // Draw the negative item only if it has not been collected
    public void draw(SpriteBatch spriteDrawing) {
        if (!negative_collected) {
            sprite.draw(spriteDrawing);
        }
    }

    // Set position of the item in the world
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
}
