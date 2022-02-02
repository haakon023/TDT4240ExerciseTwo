package com.bullethell.game.state;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateHandler {
    
    private final Stack<State> states = new Stack<>();
    
    public void setState(State state)
    {
        pop();
        add(state);
    }
    
    public void pop()
    {
        State state = states.pop();
        state.dispose();
    }
    
    public void add(State state)
    {
        states.add(state);
        state.onCreate();
    }
    
    public void update()
    {
        states.peek().update();
    }
}