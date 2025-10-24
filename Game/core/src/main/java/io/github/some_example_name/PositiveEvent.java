package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PositiveEvent {
    private Sprite sprite;
    public boolean positive_collected = false;

    public PositiveEvent(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);
    }

    public Rectangle getBoundingRectangle() {return sprite.getBoundingRectangle();}

    public void draw(SpriteBatch SpriteDrawing) {
        if (!positive_collected) {
            sprite.draw(SpriteDrawing);
        }
    }
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
}
