package com.bullethell.game.ecs.Systems;

import com.badlogic.gdx.utils.Array;
import com.bullethell.game.ecs.Engine;
import com.bullethell.game.ecs.components.Behaviour;
import com.bullethell.game.ecs.IEntityListener;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.Tag;
import com.bullethell.game.ecs.components.Component;


public class BehaviourSystem extends IteratingSystem implements IEntityListener {
    
    
    public BehaviourSystem(Tag tag)
    {
        this.tag = tag;
    }

    @Override
    public void start() {
        for(int i = 0; i < gameObjects.size; i++) {
            for(int y = 0; y < gameObjects.get(i).getComponentList().size(); y++) {
                Component component = gameObjects.get(i).getComponentList().get(y);
                if(Behaviour.class.isAssignableFrom(component.getClass()))
                    ((Behaviour) component).onStart();
            }
        }
    }

    @Override
    protected void processGameObject(GameObject o) {
        for(int i = 0; i < o.getComponentList().size(); i++) {
            Component component = o.getComponentList().get(i); 
            if(Behaviour.class.isAssignableFrom(component.getClass()))
                ((Behaviour) component).onUpdate();
               
        }
        
    }

    @Override
    public IEntityListener getEntityListener() {
        return this;
    }

    public void gameObjectAdded(GameObject gameObject) {
        if (gameObjects == null)
            gameObjects = new Array<GameObject>();
        if(!tag.isMemberOf(gameObject))
            return;
        if(Engine.getInstance().hasStarted) startGameObjectComponents(gameObject);
        gameObjects.add(gameObject);
    }

    private void startGameObjectComponents(GameObject gameObject)
    {
        for(Component c : gameObject.getComponentList()) {
            if(Behaviour.class.isAssignableFrom(c.getClass()))
                ((Behaviour) c).onStart();
        }
    }

    public void gameObjectRemoved(GameObject gameObject) {
        gameObjects.removeValue(gameObject, true);
    }
}
