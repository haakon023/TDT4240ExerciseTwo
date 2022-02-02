package com.bullethell.game.ecs;

import com.bullethell.game.ecs.components.Collider;

public interface ICollisionListener {
    void onCollisionEnter(Collider collider);
    void onCollisionExit(Collider collider);
    void onCollision(Collider collider);
}
