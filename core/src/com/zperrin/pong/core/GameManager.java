package com.zperrin.pong.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Stack;

/**
 * Only one game state should be active, but we can keep multiple states on the stack.
 */
public class GameManager {

    private Stack<State> states = new Stack<>();

    public GameManager() {
    }

    public void push(State state) {
        states.push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(ShapeRenderer shapeRenderer) {

        // clear frame
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render state
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        states.peek().render(shapeRenderer);
        shapeRenderer.end();
    }

    public void dispose() {
        while(!states.empty()) {
            states.pop().dispose();
        }
    }
}
