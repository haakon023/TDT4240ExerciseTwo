package com.bullethell.game.gameobjects;

import com.badlogic.gdx.math.Vector3;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.components.BoxCollider;
import com.bullethell.game.ecs.components.Health;
import com.bullethell.game.ecs.components.SpriteRenderer;

public class GameObjectContainer {
    
    public static GameObject createBullet()
    {
        GameObject bullet = new GameObject();
        bullet.addComponent(SpriteRenderer.class);
        bullet.addComponent(BulletBehaviour.class);
        bullet.addComponent(BoxCollider.class);
        bullet.getTransform().setScale(new Vector3(0.5f, 0.5f, 0.5f));
        bullet.setName("Bullet");
        return bullet;
    }
    
    public static GameObject createEnemy()
    {
        GameObject enemy = new GameObject();
        enemy.addComponent(SpriteRenderer.class);
        enemy.addComponent(Health.class);
        enemy.addComponent(EnemyBehaviour.class);
        enemy.addComponent(BoxCollider.class);
        enemy.setName("Enemy");

        return enemy;
    }
    
    public static GameObject EnemyManager()
    {
        GameObject manager = new GameObject();
        manager.addComponent(EnemyManager.class);
        manager.setName("EnemyManager");
        
        return manager;
    }
}
