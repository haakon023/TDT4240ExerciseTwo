package com.bullethell.game.ecs.components;

import com.badlogic.gdx.math.Shape2D;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.ICollisionListener;

public abstract class Collider extends Component{
    public abstract boolean checkCollision(GameObject gameObject);
    public abstract Shape2D getBounds();
    ICollisionListener collisionListener;

    public void onCollision(Collider otherCollider)
    {
        if(collisionListener == null)
            return;
        collisionListener.onCollision(otherCollider);
    }

    public void setCollisionListener(ICollisionListener collisionListener) {
        this.collisionListener = collisionListener;
    }
}
