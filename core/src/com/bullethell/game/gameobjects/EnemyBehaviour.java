package com.bullethell.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.components.Behaviour;
import com.bullethell.game.ecs.components.BoxCollider;
import com.bullethell.game.ecs.components.Health;
import com.bullethell.game.ecs.components.SpriteRenderer;
import com.bullethell.game.ecs.components.Transform;
import com.bullethell.game.time.Time;

public class EnemyBehaviour extends Behaviour{

    private SpriteRenderer spriteRenderer;
    private Transform transform;
    private Texture texture;
    private Health health;
    private BoxCollider collider;
    private Movement movement;
    private GameObject player;
    
    private EnemyDied enemyDied;
    
    private final Vector3 direction = new Vector3().setZero();

    public EnemyBehaviour()
    {
    }
    
    @Override
    public void onStart() {
        spriteRenderer = getGameObject().getComponent(SpriteRenderer.class);
        transform = getGameObject().getTransform();
        transform.setScale(new Vector3(64, 64, 0));
        texture = new Texture("Enemy.png");
        spriteRenderer.setTexture(texture);
        health = getGameObject().getComponent(Health.class);
        health.setHealth(50);
        collider = getGameObject().getComponent(BoxCollider.class);
        collider.setRectangle(new Rectangle(transform.getPosition().x, transform.getPosition().y, transform.getScale().x, transform.getScale().y));

        movement = new Movement(transform);

        player = GameObject.find("player");

    }

    @Override
    public void onUpdate() {
        if(player != null) {
            Vector3 test = new Vector3(
                    player.getTransform().getPosition().x - transform.getPosition().x,
                    player.getTransform().getPosition().y - transform.getPosition().y,
                    player.getTransform().getPosition().y - transform.getPosition().z
            );
            
            movement.move(test.nor(), 100, Time.deltaTime());
        }
    }

    @Override
    public void dispose() {
        enemyDied.onEnemyDied(getGameObject());
    }

    public void Register(EnemyDied enemyDied)
    {
        this.enemyDied = enemyDied;
    }
    
  
}
aaa