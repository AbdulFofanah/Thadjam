package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class NegativeEvent {
    private Sprite sprite;
    public boolean collected = false;

    public NegativeEvent(Texture texture) {
        this.sprite = new Sprite(texture);
        this.sprite.setSize(0.8f, 0.8f);
    }

    public Rectangle getBoundingRectangle() {return sprite.getBoundingRectangle();}

    public void draw(SpriteBatch SpriteDrawing) {
        if (!collected) {
            sprite.draw(SpriteDrawing);
        }
    }
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
}
