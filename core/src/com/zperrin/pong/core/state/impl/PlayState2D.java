package com.zperrin.pong.core.state.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.zperrin.pong.Pong;
import com.zperrin.pong.core.State;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public class PlayState2D extends State {

    private OrthographicCamera camera;

    public PlayState2D(Paddle[] paddles, Ball ball, ModelBatch modelBatch) {
        super(paddles, ball, modelBatch);
        camera = new OrthographicCamera(Pong.WIDTH, Pong.HEIGHT);
        //camera.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT); // if i wanted to set the camera coordinate origin to be at the bottom left of the viewport
        camera.near = 40;
    }

    @Override
    public void update(float deltaTime) {
        paddles[0].update(deltaTime);
        paddles[1].update(deltaTime);
        ball.update(deltaTime);
    }

    @Override
    public void render() {
        modelBatch.begin(camera);

        paddles[0].render();
        paddles[1].render();
        //ball.render();
        ball.render3D();

        modelBatch.end();
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
