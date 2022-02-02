package com.bullethell.game.gameobjects;

import com.badlogic.gdx.math.Vector3;
import com.bullethell.game.ecs.components.Transform;

public class Movement {
    private final Transform transform;

    public Movement(Transform transform)
    {
        this.transform = transform;
    }
    
    public void move(Vector3 direction, float speed, float deltaTime)
    {
        transform.getPosition().mulAdd(direction, speed * deltaTime);
    }
}
