package com.zperrin.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.Entity.Paddle;

import java.util.ArrayList;
import java.util.List;

public class Pong extends ApplicationAdapter {

    private ShapeRenderer renderer;
    private List<Paddle> players = new ArrayList();


    @Override
    public void create() {

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        // entities
        players.add(new Paddle(0, 500 / 2, 10, 50)
                .setPlayer(1)
                .setRenderer(renderer));
        players.add(new Paddle(500 - 10, 500 / 2, 10, 50)
                .setPlayer(2)
                .setRenderer(renderer));

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render() {

        // clear frame
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update graphics
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Paddle player : players) {
            player.render();
        }
        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        for (Paddle player : players) {
            player.dispose();
        }
    }
}
