package com.zperrin.pong.core;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Stack;

/**
 * Only one game state should be active, but we can keep multiple states on the stack.
 */
public class GameManager {

    private Stack<State> states = new Stack<State>();

    public GameManager() {
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(ShapeRenderer shapeRenderer) {
        states.peek().render(shapeRenderer);
    }
}
