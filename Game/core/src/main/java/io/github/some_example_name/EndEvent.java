package io.github.some_example_name;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Initialises the ending for the game
 */
public class EndEvent {
    private Sprite sprite;
    public boolean ending_reached = false;

    /**
     * Sets the texture and sizing for the ending sprite
     *
     * @param texture the sprite that will end the game
     */
    public EndEvent(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);
    }

    /**
     * Find the rectangle of the ending so that it can be checked whether it has been collided with
     *
     * @return the rectangle for the final event
     */
    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    /**
     * Draws the sprite onto the map if the ending hasn't been hit yet
     *
     * @param SpriteDrawing the sprite of the ending event
     */
    public void draw(SpriteBatch SpriteDrawing) {
        if (!ending_reached) {
            sprite.draw(SpriteDrawing);
        }
    }

    /**
     * Takes an x and a y coordinate and sets the sprite to the position of it
     *
     * @param x the x coord of the sprite
     * @param y the y coord of the sprite
     */
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
}

