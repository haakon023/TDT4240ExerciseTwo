package com.bullethell.game.ecs.Systems;

import com.bullethell.game.ecs.Engine;
import com.bullethell.game.ecs.IEntityListener;
import com.bullethell.game.ecs.EntitySystem;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.time.Time;

public abstract class IntervalSystem extends EntitySystem {
    
    private final float TIME_STEP = 0.5f;
    private float currentTime = 0;
    private final float interval;

    public IntervalSystem(float interval)
    {
        this.interval = interval;
    }

    protected abstract void processGameObject(GameObject o);
    
    @Override
    public void update() {
        currentTime += Time.deltaTime();

        while (currentTime >= interval) {
            currentTime -= interval;
            updateInterval();
        }
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
    
    public abstract void updateInterval();
}
