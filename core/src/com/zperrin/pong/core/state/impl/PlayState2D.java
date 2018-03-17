package com.zperrin.pong.core.state.impl;

import com.zperrin.pong.core.State;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public class PlayState2D extends State {

    public PlayState2D(Paddle[] paddles, Ball ball) {
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
        paddles[0].render();
        paddles[1].render();
        ball.render();
    }

    public void dispose() {
        paddles[0].dispose();
        paddles[1].dispose();
        ball.dispose();
    }

    public Paddle[] getPaddles() {
        return paddles;
    }
}
