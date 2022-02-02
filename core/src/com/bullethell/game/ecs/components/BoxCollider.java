package com.bullethell.game.ecs.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.bullethell.game.ecs.GameObject;

public class BoxCollider extends Collider {
    
    private Shape2D bounds;
    
    public BoxCollider()
    {
        bounds = new Rectangle();
    }
    
    @Override
    public Shape2D getBounds() {
        return bounds;
    }

    public void setRectangle(Rectangle bounds) {
        this.bounds = bounds;
    }

    @Override
    public boolean checkCollision(GameObject gameObject) {
        Transform transform = gameObject.getTransform();
        Transform thisTransform = getGameObject().getTransform();
        ((Rectangle) bounds).setPosition(thisTransform.getPosition().x, thisTransform.getPosition().y);
        Rectangle rectangle = new Rectangle(transform.getPosition().x, transform.getPosition().y, transform.getScale().x, transform.getScale().y);
        return ((Rectangle)bounds).overlaps(rectangle);
    }
}
