package com.bullethell.game.ecs.components;

import com.bullethell.game.ecs.Engine;
import com.bullethell.game.ecs.GameObject;

public class EntityObject {
    protected final void destroy (GameObject gameObject)
    {
        Engine.getInstance().removeGameObject(gameObject);
    }
    
    
}
