package com.bullethell.game.ecs;

import com.badlogic.gdx.utils.Array;
import com.bullethell.game.ecs.components.Behaviour;
import com.bullethell.game.ecs.components.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManager {

    private final IEntityListener listener;
    private final List<GameObject> gameObjects;
    private final Map<Tag, Array<GameObject>> tagMap;
    
    public EntityManager(IEntityListener listener)
    {
        this.listener = listener;
        gameObjects = new ArrayList<>();
        tagMap = new HashMap<>();
    }
    
    public void addGameObject(GameObject gameObject)
    {
        gameObjects.add(gameObject);
        listener.gameObjectAdded(gameObject);
    }
    
    public void removeGameObject(GameObject gameObject)
    {
        for (int i = 0; i < gameObject.getComponentList().size(); i++) {
            if(Behaviour.class.isAssignableFrom( gameObject.getComponentList().get(i).getClass()))
                ((Behaviour) gameObject.getComponentList().get(i)).dispose();
        }
        gameObjects.remove(gameObject);
        listener.gameObjectRemoved(gameObject);
    }
    
    
    public void removeAllGameObjects()
    {
        gameObjects.clear();
    }
    
    public List<GameObject> getGameObjects()
    {
        return gameObjects;
    }
    
    public Array<GameObject> getComponentsFromTag(Tag tag)
    {
        Array<GameObject> gameObjects = tagMap.get(tag);
        if(gameObjects == null) {
            Array<GameObject> newList = new Array<>();
            tagMap.put(tag, newList);
            addEntities(tag, newList);
        }
        return gameObjects;
    }

    private void addEntities(Tag tag, Array<GameObject> newList) {
        for(GameObject object : newList) {
            if(tag.isMemberOf(object))
                newList.add(object);
        }
    }


}
