package com.zperrin.pong.core.state.impl;

import com.zperrin.pong.core.State;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Created by zebulonperrin on 3/17/18.
 */
public class PlayState3D extends State {


    public PlayState3D(Paddle[] paddles, Ball ball) {
        super(paddles, ball);

    }

    @Override
    public void update(float deltaTime) {
        paddles[0].update(deltaTime);
        paddles[1].update(deltaTime);
        ball.update(deltaTime);
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}
