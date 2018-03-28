package com.zperrin.pong.core.state.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.zperrin.pong.Pong;
import com.zperrin.pong.core.State;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Created by zebulonperrin on 3/17/18.
 */
// todo: pong 3d version
public class PlayState3D extends State {

    PerspectiveCamera camera;

    private Environment environment;

    public PlayState3D(Paddle[] paddles, Ball ball, ModelBatch modelBatch) {

        super(paddles, ball, modelBatch);

        // todo: tweak these
        camera = new PerspectiveCamera(67f, Pong.WIDTH, Pong.HEIGHT);
        camera.position.set(-500f, 0f, -16f);
        camera.lookAt(0f, 0f, 0f);
        camera.near = 0.1f;
        camera.far = 1000f;
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));

    }

    @Override
    public void update(float deltaTime) {


        // todo: static variables for re-used vectors
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), -1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            camera.position.add(0f, 0f, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            camera.position.add(0f, 0f, -1f);
        }

        paddles[0].update(deltaTime);
        paddles[1].update(deltaTime);
        ball.update(deltaTime);
        camera.update();

    }

    @Override
    public void render() {

        modelBatch.begin(camera);

        paddles[0].render(environment);
        paddles[1].render(environment);
        ball.render(environment);

        modelBatch.end();
    }

    @Override
    public void dispose() {

    }
}
