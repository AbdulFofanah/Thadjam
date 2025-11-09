package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * HiddenEvent represents a invisible collectible item in the game
 * Player can pick it up which activates event
 */
public class HiddenEvent {
    private Sprite sprite;
    public boolean hidden_collected = false;

    /**
     *
     * @param texture
     */
    public HiddenEvent(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);
    }

    /**
     *
     * @return
     */
    public Rectangle getBoundingRectangle() {return sprite.getBoundingRectangle();}

    /**
     *
     * @param SpriteDrawing
     */
    public void draw(SpriteBatch SpriteDrawing) {
        // Draw the item only if it has not been collected
        if (!hidden_collected) {
            sprite.draw(SpriteDrawing);
        }
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        // Set position of item in the world
        sprite.setPosition(x, y);
    }
}
