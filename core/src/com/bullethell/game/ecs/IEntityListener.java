package com.bullethell.game.ecs;

public interface IEntityListener {
    void gameObjectAdded(GameObject gameObject);
    void gameObjectRemoved(GameObject gameObject);
}
