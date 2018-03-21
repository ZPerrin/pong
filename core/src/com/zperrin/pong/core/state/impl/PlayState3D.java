package com.zperrin.pong.core.state.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
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
    private ModelBatch batch;
    private ModelBuilder builder;
    private Model ball2;
    private ModelInstance modelInstance;
    //private Environment environment;

    public PlayState3D(Paddle[] paddles, Ball ball, ModelBatch modelBatch) {

        super(paddles, ball, modelBatch);

        // todo: tweak these
        camera = new PerspectiveCamera(67f, Pong.WIDTH, Pong.HEIGHT);
        camera.position.set(0f, 0f, -10f);
        camera.lookAt(0f, 0f, 1f);
        camera.near = 0.1f;
        camera.far = 300f;

        batch = modelBatch;
        builder = new ModelBuilder();
        ball2 = builder.createBox(2f, 2f, 2f,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        modelInstance = new ModelInstance(ball2, 0f, 0f, 0f);
        //environment = new Environment();
        //environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));

    }

    @Override
    public void update(float deltaTime) {
        // todo: static variables for re-used vectors
        camera.update();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), -1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.rotateAround(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f), 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            modelInstance.transform.translate(0f, 0f, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            modelInstance.transform.translate(0f, 0f, -1f);
        }

    }

    @Override
    public void render() {
        batch.begin(camera);
        batch.render(modelInstance);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
