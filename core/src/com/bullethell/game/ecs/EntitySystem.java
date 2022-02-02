package com.bullethell.game.ecs;

import com.badlogic.gdx.utils.Array;

public abstract class EntitySystem {
    
    private Engine engine;
    protected Array<GameObject> gameObjects = new Array<>();
    protected Tag tag;

    public Engine getEngine()
    {
        return engine;
    }
    
    public abstract void start();
    public abstract void update();
    public abstract void dispose();
    
    public void addedToEngine(Engine engine)
    {
        this.engine = engine;
    }
    
    public void removedFromEngine(Engine engine)
    {
        this.engine = null; 
    }
}
