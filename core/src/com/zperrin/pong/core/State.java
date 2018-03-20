package com.zperrin.pong.core;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Encapsulates a game state
 */
public abstract class State {

    protected Paddle[] paddles;
    protected Ball ball;
    protected ModelBatch modelBatch;

    public State(Paddle[] paddles, Ball ball, ModelBatch modelBatch) {
        this.paddles = paddles;
        this.ball = ball;
        this.modelBatch = modelBatch;
    }

    public abstract void update(float deltaTime);

    public abstract void render();

    public abstract void dispose();
}
