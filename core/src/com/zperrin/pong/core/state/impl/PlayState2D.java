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
// todo: is this state even needed? 3D + perspective should be able to fake a 2d representation
public class PlayState2D extends State {

    private OrthographicCamera camera;

    public PlayState2D(Paddle[] paddles, Ball ball, ModelBatch modelBatch) {
        super(paddles, ball, modelBatch);
        camera = new OrthographicCamera(Pong.WIDTH, Pong.HEIGHT);
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

        paddles[0].render(null);
        paddles[1].render(null);
        ball.render(null);

        modelBatch.end();
    }

    public void dispose() {
        paddles[0].dispose();
        paddles[1].dispose();
        ball.dispose();
    }
}
