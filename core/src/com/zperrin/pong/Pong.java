package com.zperrin.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.zperrin.pong.core.GameManager;

public class Pong extends ApplicationAdapter {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private GameManager manager;

    @Override
    public void create() {

        manager = new GameManager();

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // physics
        manager.update(Gdx.graphics.getDeltaTime());

        // graphics
        manager.render();

    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
