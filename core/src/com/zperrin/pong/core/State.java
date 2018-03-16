package com.zperrin.pong.core;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Encapsulates a game state
 */
public abstract class State {

    public abstract void update(float deltaTime);

    public abstract void render(ShapeRenderer shapeRenderer);

    public abstract void dispose();
}
