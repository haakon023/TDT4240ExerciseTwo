package com.bullethell.game.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bullethell.game.ecs.GameObject;


import java.util.ArrayList;
import java.util.List;

public abstract class State{
    
    protected GameStateHandler gameStateHandler;
    
    public State(GameStateHandler handler)
    {
        gameStateHandler = handler;
    }
    
    public abstract void update();
    public abstract void onCreate();
    public abstract void dispose();

}
