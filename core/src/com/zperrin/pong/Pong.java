package com.zperrin.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.zperrin.pong.core.GameManager;

public class Pong extends ApplicationAdapter {


    private GameManager manager;

    @Override
    public void create() {

        manager = new GameManager();

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render() {

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
