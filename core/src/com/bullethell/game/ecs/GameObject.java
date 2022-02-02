package com.bullethell.game.ecs;


import com.bullethell.game.ecs.components.Component;
import com.bullethell.game.ecs.components.EntityObject;
import com.bullethell.game.ecs.components.Transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GameObject extends EntityObject{
    
    public boolean isEnabled;
    private final List<Component> componentList;
    private final HashMap<Class<? extends Component>, Component> cachedComponents;
  
    private final UUID gameObjectId;
    
    private String name;
    private Transform transform;

    public static GameObject find(String name) {
        List<GameObject> objs = Engine.getInstance().getGameObjects();
        for(int i = 0; i < objs.size(); i++) {
            if(objs.get(i).getName().equals(name))
                return objs.get(i);
        }
        return null;
    }

    public Transform getTransform()
    {
        return transform;
    }
    
    public List<Component> getComponentList()
    {
        return componentList;
    }
    
    //create an empty Gameobject
    public GameObject()
    {
        componentList = new ArrayList<>();
        cachedComponents = new HashMap<>();
        transform = (Transform) addComponent(Transform.class);
        gameObjectId = UUID.randomUUID();
    }

    //This method should take in parameter to create a object with components added
    public static GameObject instantiate(GameObject object)
    {
        Engine.getInstance().addGameObject(object);
        return object;
    }
    
    public <T extends Component> T getComponent(Class<T> component)
    {
        Component cachedComponent = cachedComponents.get(component);
        if(cachedComponent != null)
            return component.cast(cachedComponent);  
        
        for (Component c : componentList) {
            if (component.isAssignableFrom(c.getClass())) {
                cachedComponents.put(component, c);
                return (component.cast(c));
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent (Class<T> component)
    {
        for (int i = 0; i < componentList.size(); i++) {
            Component c = componentList.get(i);
            if (component.isAssignableFrom(c.getClass())) {
                componentList.remove(i);
                return;
            }
        }
    }

    public Component addComponent(Class<? extends Component> componentType)
    {
        try {
            Component component = componentType.newInstance();
            component.setGameObject(this);
            componentList.add(component);
            return component;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public <T extends Component> boolean hasComponent(Class<T> component)
    {
        if(cachedComponents.containsKey(component))
            return true;
        
       for(Component c : componentList) {
           if(component.isAssignableFrom(c.getClass()))
               return true;
       }
       return false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass().isAssignableFrom(this.getClass()))
            return gameObjectId == ((GameObject)o).gameObjectId;
        return false;
    }
}
