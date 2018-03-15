package com.zperrin.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.GameManager;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;
import com.zperrin.pong.core.state.PlayState;

import java.util.ArrayList;
import java.util.List;

public class Pong extends ApplicationAdapter {

    private ShapeRenderer renderer = new ShapeRenderer();
    private GameManager manager;
    private List<Paddle> players = new ArrayList();
    private Ball ball;


    @Override
    public void create() {

        renderer.setAutoShapeType(true);
        renderer.setColor(1,1,1,1);

        PlayState playState = new PlayState();

        // entities
        players.add(new Paddle(renderer, 1));
        players.add(new Paddle(renderer, 2));
        ball = new Ball(renderer);

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render() {

        // clear frame
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // todo: simulation

        // todo: render graphics

        // update graphics
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Paddle player : players) {
            player.render();
        }
        ball.render(players);

        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        for (Paddle player : players) {
            player.dispose();
        }
        ball.dispose();
    }
}
