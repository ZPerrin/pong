package com.zperrin.pong.core.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.zperrin.pong.core.entity.IEntity;


import java.util.List;
import java.util.Random;

/**
 * Created by zebulonperrin on 3/10/18.
 */
public class Ball extends Circle implements IEntity {

    private ShapeRenderer renderer;
    private Vector2 position = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    private Vector2 velocity = new Vector2();
    private Sound blip;

    public Ball() {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 8);
    }

    public Ball(ShapeRenderer renderer) {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 8);
        this.renderer = renderer;
        this.blip = Gdx.audio.newSound(Gdx.files.internal("pong-blip.wav"));
        setRandomVelocity();
    }

    public void render(List<Paddle> paddleList) {
        renderer.setColor(1, 1, 1, 1);

        // collision logic for paddles
        if (Intersector.overlaps(this, paddleList.get(0).getBoundingRectangle()) || Intersector.overlaps(this, paddleList.get(1).getBoundingRectangle())) {
            setPosition(velocity.scl(-1, 1), true);
        } else if (position.y + this.radius >= Gdx.graphics.getHeight()) {
            setPosition(velocity.scl(1, -1), true);
        } else if (position.y - this.radius <= 0) {
            setPosition(velocity.scl(1, -1), true);
        } else if (position.x + this.radius <= 0 || position.x - this.radius >= Gdx.graphics.getWidth()) {
            setRandomVelocity();
            position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        } else {
            setPosition(velocity, false);
        }

        // update circle position for renderer
        this.x = position.x;
        this.y = position.y;


        renderer.circle(position.x, position.y, radius);
    }

    private void setPosition(Vector2 velocity, boolean collision) {
        if (collision) {
            blip.play();
        }
        position.add(velocity);
    }

    private void setRandomVelocity() {
        Random r = new Random();
        double x = 1 + r.nextDouble() * (2 - 1);
        double y = 0 + r.nextDouble() * (1 - 0);
        double d = 0 + r.nextDouble() * (1 - 0);
        this.velocity.set((float) x, (float) y);
        this.velocity.nor();

        if (d <= .25) {
            this.velocity = velocity.scl(1, 1);
        } else if (d <= .45) {
            this.velocity = velocity.scl(-1, 1);
        } else if (d <= .75) {
            this.velocity = velocity.scl(1, -1);
        } else {
            this.velocity = velocity.scl(-1, -1);
        }
        this.velocity.scl(4);
    }

    public void dispose() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }
}
