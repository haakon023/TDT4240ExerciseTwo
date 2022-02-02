package com.bullethell.game.ecs.Systems;

import com.badlogic.gdx.utils.Array;
import com.bullethell.game.ecs.IEntityListener;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.Tag;
import com.bullethell.game.ecs.components.Collider;


public class PhysicsSystem extends IntervalSystem implements IEntityListener {

    private final Tag tag;
    
    public PhysicsSystem(float interval, Tag tag) {
        super(interval);
        gameObjects = new Array<>();
        this.tag = tag;
    }

    @Override
    public void start() {
    }

    @Override
    protected void processGameObject(GameObject o) {
        Collider collider = o.getComponent(Collider.class);
        for(GameObject gameObject : gameObjects) {
            if(gameObject.equals(o))
                continue;
            
            if(collider.checkCollision(gameObject)) {
                Collider otherCollider = gameObject.getComponent(Collider.class);
                collider.onCollision(otherCollider);
                otherCollider.onCollision(collider);
            }
        }
    }

    @Override
    public IEntityListener getEntityListener() {
        return this;
    }

    @Override
    public void updateInterval()
    {
        for(int i= 0; i < gameObjects.size; i++) {
            processGameObject(gameObjects.get(i));
        }
    }
    
    

    @Override
    public void dispose() {

    }

    public void gameObjectAdded(GameObject gameObject) {
        if(gameObjects == null)
            gameObjects = new Array<GameObject>();
        if(!tag.isMemberOf(gameObject))
            return;
        gameObjects.add(gameObject);
    }

    public void gameObjectRemoved(final GameObject gameObject) {
        System.out.println(gameObject);
        gameObjects.removeValue(gameObject, true);
    }
}
