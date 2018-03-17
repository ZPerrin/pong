package com.zperrin.pong.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;
import com.zperrin.pong.core.state.impl.PlayState;

import java.util.Stack;

/**
 * Only one game state should be active, but we can keep multiple states on the stack.
 */
public class GameManager {


    private ShapeRenderer renderer;
    private Paddle[] paddles = {new Paddle(1), new Paddle(2)};
    private Ball ball = new Ball(paddles);

    private Stack<State> states = new Stack<>();

    public GameManager() {

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        renderer.setColor(1, 1, 1, 1);

        states.push(new PlayState(paddles, ball));
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
        states.peek().render(renderer);
        renderer.end();
    }

    public void dispose() {
        while (!states.empty()) {
            states.pop().dispose();
        }
    }
}
