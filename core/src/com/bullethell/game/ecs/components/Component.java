package com.bullethell.game.ecs.components;

import com.bullethell.game.ecs.GameObject;

public abstract class Component extends EntityObject {

    private GameObject gameObject;
    public void setGameObject(GameObject gameObject)
    {
        this.gameObject = gameObject;
    }
    public GameObject getGameObject()
    {
        return gameObject;
    }
    
}