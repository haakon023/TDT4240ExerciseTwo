package com.bullethell.game.ecs;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    
    private final List<EntitySystem> entitySystems = new ArrayList<>();
    private final List<IEntityListener> entityListeners = new ArrayList<>();
    private final EntityManager entityManager = new EntityManager(new EngineEntityListener());
    public boolean hasStarted;
    
    private static Engine Instance;
    
    public static final Engine getInstance()
    {
        if(Instance == null)
            Instance = new Engine();
        return Instance;
    }
    
    public Engine()
    {
        Instance = this;
    }
    
    public void start()
    {
        hasStarted = true;
        for (EntitySystem system: entitySystems) {
            system.start();
        }
    }
    
    public void update()
    {
        for(EntitySystem system : entitySystems) {
            system.update();
        }
    }
    
    public void addListener(IEntityListener listener)
    {
        entityListeners.add(listener);
    }
    
    public void removeListener(IEntityListener listener)
    {
        entityListeners.remove(listener);
    }
    
    public void addSystemToEngine(EntitySystem system)
    {
        entitySystems.add(system);
        system.addedToEngine(this);
    }
    
    public void removedFromSystem(EntitySystem system)
    {
        entitySystems.remove(system);
        system.removedFromEngine(this);
    }
    
    public void addGameObject(GameObject gameObject)
    {
        entityManager.addGameObject(gameObject);
    }
    
    public void removeGameObject(GameObject gameObject)
    {
        entityManager.removeGameObject(gameObject);
    }

    public List<GameObject> getGameObjects() {
        return entityManager.getGameObjects();
    }
    
    public Array<GameObject> getGameObjectsOfType(Tag tag)
    {
       return entityManager.getComponentsFromTag(tag);
    }

    public void dispose() {
        for(EntitySystem system : entitySystems)
            system.dispose();
    }

    private class EngineEntityListener implements IEntityListener {
        @Override
        public void gameObjectAdded(GameObject gameObject) {
            for(IEntityListener listener : entityListeners) {
                listener.gameObjectAdded(gameObject);
            }
        }

        @Override
        public void gameObjectRemoved(GameObject gameObject) {
            for(IEntityListener listener : entityListeners) {
                listener.gameObjectRemoved(gameObject);
            }
        }
    }
}
