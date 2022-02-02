package com.bullethell.game.gameobjects;

import com.badlogic.gdx.utils.Array;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.components.Behaviour;
import com.bullethell.game.time.Time;

public class EnemyManager extends Behaviour implements EnemyDied{
    
    private Array<GameObject> enemyBehaviours;
    private float timeBetweenSpawn =  3f;
    private float currentTime = 0;
    
    @Override
    public void onStart() {
        enemyBehaviours = new Array<>();
        
    }

    @Override
    public void onUpdate() {
        currentTime += Time.deltaTime();
        if(currentTime > timeBetweenSpawn) {
            if(canSpawnEnemy())
                spawnEnemy();
            currentTime = 0;
        }
    }

    private boolean canSpawnEnemy() {
        return enemyBehaviours.size < 5;
    }

    private void spawnEnemy()
    {
        GameObject enemy = GameObjectContainer.createEnemy();
        enemy.getComponent(EnemyBehaviour.class).Register(this);

        GameObject.instantiate(enemy);
        enemyBehaviours.add(enemy);
    }
    
    @Override
    public void dispose() {

    }

    @Override
    public void onEnemyDied(GameObject enemy) {
        enemyBehaviours.removeValue(enemy, true);
    }
}
