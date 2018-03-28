package com.zperrin.pong.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;
import com.zperrin.pong.core.state.impl.PlayState2D;
import com.zperrin.pong.core.state.impl.PlayState3D;

import java.util.Stack;

/**
 * Manage the game states (e.g. 2d vs 3d).  Only one should be rendered at any given time.
 */
public class GameManager {

    private ShapeRenderer renderer;
    private ModelBatch modelBatch;
    private Paddle[] paddles = new Paddle[2];
    private Ball ball;

    private Stack<State> states = new Stack<>();

    public GameManager() {

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        renderer.setColor(1, 1, 1, 1);

        modelBatch = new ModelBatch();

        paddles[0] = new Paddle(1, modelBatch);
        paddles[1] = new Paddle(2, modelBatch);
        ball = new Ball(paddles, modelBatch);

        states.push(new PlayState2D(paddles, ball, modelBatch));
        states.push(new PlayState3D(paddles, ball, modelBatch));
    }

    public void push(State state) {
        states.push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render() {

        // clear frame
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render state
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        states.peek().render();
        renderer.end();
    }

    public void dispose() {
        while (!states.empty()) {
            states.pop().dispose();
        }
    }
}
