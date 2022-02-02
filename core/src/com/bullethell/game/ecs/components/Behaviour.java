package com.bullethell.game.ecs.components;

public abstract class Behaviour extends Component {

    public abstract void onStart();
    public abstract void onUpdate();
    public abstract void dispose();
}
