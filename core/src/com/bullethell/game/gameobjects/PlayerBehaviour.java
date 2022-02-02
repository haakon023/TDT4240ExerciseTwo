package com.bullethell.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.Systems.RenderingSystem;
import com.bullethell.game.ecs.components.Behaviour;
import com.bullethell.game.ecs.components.BoxCollider;
import com.bullethell.game.ecs.components.SpriteRenderer;
import com.bullethell.game.ecs.components.Transform;
import com.bullethell.game.time.Time;

import jdk.javadoc.internal.doclets.toolkit.EnumConstantWriter;

public class PlayerBehaviour extends Behaviour {
    
    private Transform transform;
    private SpriteRenderer renderer;
    private BoxCollider collider;
    private Movement movement;
    private final float movementSpeed = 250;
        
    @Override
    public void onStart() {
        transform = getGameObject().getTransform();
        transform.setScale(new Vector3(64, 64, 0));
        renderer = getGameObject().getComponent(SpriteRenderer.class);
        Texture temp = new Texture("Player.png");
        renderer.setTexture(temp);
        getGameObject().setName("player");
        collider = getGameObject().getComponent(BoxCollider.class);
        collider.setRectangle(new Rectangle(transform.getPosition().x, transform.getPosition().y, transform.getScale().x, transform.getScale().y));
        
        this.movement = new Movement(transform);
    }

    @Override
    public void onUpdate() {
        shoot();
        handleInput();
    }

    private void handleInput() {
        Vector3 direction = new Vector3();

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            direction.y = 1;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            direction.y = -1;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            direction.x = -1;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            direction.x = 1;
        
        movement.move(direction, movementSpeed, Time.deltaTime());
    }

    private void shoot()
    {
        if(Gdx.input.justTouched()) {
            GameObject bullet = GameObject.instantiate(GameObjectContainer.createBullet());
            bullet.getTransform().setPosition(new Vector3(transform.getPosition().x, transform.getPosition().y, transform.getPosition().z));
            Vector3 mousePos = RenderingSystem.Main().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            Vector3 newDirection = new Vector3(mousePos.x - transform.getPosition().x, (mousePos.y - transform.getPosition().y), 0).nor();
            bullet.getComponent(BulletBehaviour.class).fireBullet(newDirection);
        }
    }
    
    @Override
    public void dispose() {
        renderer.getSprite().getTexture().dispose();
    }
}
