package com.zperrin.pong.core.state;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zperrin.pong.core.State;
import com.zperrin.pong.core.entity.impl.Ball;
import com.zperrin.pong.core.entity.impl.Paddle;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public class PlayState extends State {

    private Paddle[] paddles = new Paddle[2];
    private Ball ball = new Ball();

    public PlayState() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {

    }
}
