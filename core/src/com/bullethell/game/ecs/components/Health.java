package com.bullethell.game.ecs.components;

public class Health extends Behaviour{
    
    private float health = 100;

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void onStart() {
        
    }

    @Override
    public void onUpdate() {
        if(health < 0)
            destroy(this.getGameObject());  
    }

    @Override
    public void dispose() {

    }

    public void takeDamage(float amount) {
        health -= amount;
    }
}
