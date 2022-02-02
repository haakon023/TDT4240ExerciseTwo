package com.bullethell.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.ICollisionListener;
import com.bullethell.game.ecs.components.Behaviour;
import com.bullethell.game.ecs.components.Collider;
import com.bullethell.game.ecs.components.Health;
import com.bullethell.game.ecs.components.SpriteRenderer;
import com.bullethell.game.ecs.components.Transform;
import com.bullethell.game.time.Time;

public class BulletBehaviour extends Behaviour implements ICollisionListener {
    private Transform transform;
    private SpriteRenderer renderer;
    private Vector3 direction = new Vector3().setZero();
    private Collider collider;

    private final float speed = 400;
    private final float timeToDestroy = 1;
    private float currentTimer = 0;
    private final float damage = 25;
    
    private Movement movement;

    @Override
    public void onStart() {
        transform = getGameObject().getTransform();
        transform.setScale(new Vector3(16, 16,0));
        renderer = getGameObject().getComponent(SpriteRenderer.class);
        renderer.setTexture(new Texture("bullet.png"));
        collider = getGameObject().getComponent(Collider.class);
        collider.setCollisionListener(this);
        getGameObject().setName("bullet");
        
        movement = new Movement(transform);
    }

    public void fireBullet(Vector3 direction)
    {
        this.direction = direction;
    }
    
    @Override
    public void onUpdate() {
        currentTimer += Time.deltaTime();
        if(currentTimer >= timeToDestroy)
            destroy(getGameObject());
        
        movement.move(direction, speed, Time.deltaTime());
    }

    @Override
    public void dispose() {
        
    }


    @Override
    public void onCollisionEnter(Collider collider) {
        
    }

    @Override
    public void onCollisionExit(Collider collider) {

    }

    @Override
    public void onCollision(Collider collider) {
        Health health = collider.getGameObject().getComponent(Health.class);
      if(health == null)
            return;
        health.takeDamage(damage);
        destroy(getGameObject());
    }
}
