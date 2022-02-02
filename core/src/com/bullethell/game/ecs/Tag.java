package com.bullethell.game.ecs;

import com.bullethell.game.ecs.components.Component;

import java.util.HashSet;
import java.util.Set;

public class Tag {
    private Set<Class<? extends Component>> types = new HashSet<>();
    
    @SafeVarargs
    public static Tag create(Class<? extends Component>... types)
    {
        Tag tag = new Tag();
        for(Class<? extends Component> type : types) {
            tag.types.add(type);
        }
        return tag;
    }
    
    public boolean isMemberOf(GameObject gameObject)
    {
        for (Class<? extends Component> type : types) {
            if (!gameObject.hasComponent(type)) {
                return false;
            }
        }
        return true;
    }
    
    private Tag()
    {
        //left empty to force the use of Tag.Create()
    }
}
