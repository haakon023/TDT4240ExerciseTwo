package com.bullethell.game.ecs.components;

import com.badlogic.gdx.math.Vector3;

public class Transform extends Component{

    private Vector3 position = new Vector3().setZero();
    private Vector3 rotation = new Vector3().setZero();
    private Vector3 scale = new Vector3(1,1,1);

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public void setRotation(Vector3 rotation) {
        this.rotation = rotation;
    }

    public Vector3 getScale() {
        return scale;
    }

    public void setScale(Vector3 scale) {
        this.scale = scale;
    }
}
