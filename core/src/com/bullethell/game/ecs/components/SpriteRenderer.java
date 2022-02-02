package com.bullethell.game.ecs.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteRenderer extends Component {

    private Sprite sprite;
   
    public Sprite getSprite() {
        return sprite;
    }

    public void setTexture(Texture texture) {
        this.sprite = new Sprite(texture);
    }

}
