package com.bullethell.game.time;

import com.badlogic.gdx.Gdx;

public class Time {
    public static float deltaTime()
    {
        return Gdx.graphics.getDeltaTime();
    }
}
