package com.zperrin.pong.core.state;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.State;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public class PlayState extends State {

    private Paddle[] paddles = new Paddle[2];
    private Ball ball;

    public PlayState() {
        paddles[0] = new Paddle(1);
        paddles[1] = new Paddle(2);
        ball = new Ball(this);
    }

    @Override
    public void update(float deltaTime) {
        paddles[0].update(deltaTime);
        paddles[1].update(deltaTime);
        ball.update(deltaTime);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        paddles[0].render(shapeRenderer);
        paddles[1].render(shapeRenderer);
        ball.render(shapeRenderer);
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
