package com.zperrin.pong.core.state.impl;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.State;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public class PauseState extends State {

    public PauseState(Paddle[] paddles, Ball ball, ModelBatch modelBatch) {
        super(paddles, ball, modelBatch);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}
