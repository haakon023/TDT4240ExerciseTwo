package com.bullethell.game.ecs.Systems;

import com.badlogic.gdx.utils.Array;
import com.bullethell.game.ecs.Engine;
import com.bullethell.game.ecs.IEntityListener;
import com.bullethell.game.ecs.EntitySystem;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.components.Behaviour;

public abstract class IteratingSystem extends EntitySystem {
    

    public IteratingSystem()
    {
        gameObjects = new Array<>();
    }

    @Override
    public void start() {
        for (GameObject obj : gameObjects) {
            obj.getComponent(Behaviour.class).onStart();
        }
    }

    @Override
    public void update() {
       for (GameObject o : gameObjects) {
            processGameObject(o);
       }
    }

    protected abstract void processGameObject(GameObject o);

    @Override
    public void dispose() {

    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addListener(getEntityListener());
        gameObjects = engine.getGameObjectsOfType(tag);
    }

    @Override
    public void removedFromEngine(Engine engine) {

        engine.removedFromSystem(this);
        engine.removeListener(getEntityListener());
    }
    
    public abstract IEntityListener getEntityListener();
  
}
