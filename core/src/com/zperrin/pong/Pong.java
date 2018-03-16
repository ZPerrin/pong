package com.zperrin.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.GameManager;
import com.zperrin.pong.core.state.PlayState;

public class Pong extends ApplicationAdapter {

    private ShapeRenderer renderer;
    private GameManager manager;

    @Override
    public void create() {

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        renderer.setColor(1, 1, 1, 1);

        manager = new GameManager();
        manager.push(new PlayState());

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render() {

        // physics
        manager.update(Gdx.graphics.getDeltaTime());

        // graphics
        manager.render(renderer);

    }

    @Override
    public void dispose() {
        renderer.dispose();
        manager.dispose();
    }
}
