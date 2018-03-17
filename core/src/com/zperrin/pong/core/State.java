package com.zperrin.pong.core;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Encapsulates a game state
 */
public abstract class State {

    protected Paddle[] paddles = new Paddle[2];
    protected Ball ball;

    public State(Paddle[] paddles, Ball ball) {
        this.paddles = paddles;
        this.ball = ball;
    }

    public abstract void update(float deltaTime);

    public abstract void render(ShapeRenderer shapeRenderer);

    public abstract void dispose();
}
