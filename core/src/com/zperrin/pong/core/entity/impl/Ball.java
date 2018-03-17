package com.zperrin.pong.core.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.zperrin.pong.core.entity.IEntity;

import java.util.Random;

/**
 * Created by zebulonperrin on 3/10/18.
 */
public class Ball extends Circle implements IEntity {

    private Vector3 position = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    private Vector3 velocity = new Vector3();
    private Sound blip = Gdx.audio.newSound(Gdx.files.internal("pong-blip.wav"));
    private Paddle[] paddles;
    private ShapeRenderer renderer;

    public Ball(Paddle[] paddles, ShapeRenderer renderer) {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 8); // todo static?
        this.paddles = paddles;
        this.renderer = renderer;
        setRandomVelocity();
    }


    @Override
    public void update(float deltaTime) {

        // collision logic for paddles
        if (Intersector.overlaps(this, paddles[0].getBoundingRectangle()) || Intersector.overlaps(this, paddles[1].getBoundingRectangle())) {
            setPosition(velocity.scl(-1, 1, 0), true);
        } else if (position.y + this.radius >= Gdx.graphics.getHeight()) {
            setPosition(velocity.scl(1, -1, 0), true);
        } else if (position.y - this.radius <= 0) {
            setPosition(velocity.scl(1, -1, 0), true);
        } else if (position.x + this.radius <= 0 || position.x - this.radius >= Gdx.graphics.getWidth()) {
            setRandomVelocity();
            position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        } else {
            setPosition(velocity, false);
        }
        this.x = position.x;
        this.y = position.y;
    }

    @Override
    public void render() {
        renderer.circle(position.x, position.y, radius);
    }

    private void setPosition(Vector3 velocity, boolean collision) {
        if (collision) {
            blip.play();
        }
        position.add(velocity);
    }

    // todo: update this
    private void setRandomVelocity() {
        Random r = new Random();
        double x = 1 + r.nextDouble() * (2 - 1);
        double y = 0 + r.nextDouble() * (1 - 0);
        double d = 0 + r.nextDouble() * (1 - 0);
        this.velocity.set((float) x, (float) y, 0f);
        this.velocity.nor();

        if (d <= .25) {
            this.velocity = velocity.scl(1, 1, 0);
        } else if (d <= .45) {
            this.velocity = velocity.scl(-1, 1, 0);
        } else if (d <= .75) {
            this.velocity = velocity.scl(1, -1, 0);
        } else {
            this.velocity = velocity.scl(-1, -1, 0);
        }
        this.velocity.scl(4);
    }

    public void dispose() {
        //this.dispose();
    }
}
