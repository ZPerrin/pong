package com.zperrin.pong.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Zebulon on 3/8/2018.
 */
public class Paddle extends Rectangle {

    private static final float SCALE = 400f;
    private int player;
    private ShapeRenderer renderer;


    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void render() {

        // bound & scale movement by dt
        if (this.player == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                this.y += SCALE * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                this.y -= SCALE * Gdx.graphics.getDeltaTime();
            }

            if (this.getY() > 500 - 50) {
                this.y = 500 - 50;
            }

            if (this.getY() < 0) {
                this.y = 0;
            }
            renderer.setColor(1, 1, 1, 1);
        }

        if (this.player == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                this.y += SCALE * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                this.y -= SCALE * Gdx.graphics.getDeltaTime();
            }

            if (this.getY() > 500 - 50) {
                this.y = 500 - 50;
            }

            if (this.getY() < 0) {
                this.y = 0;
            }
            renderer.setColor(1, 0, 1, 1);
        }
        renderer.rect(x, y, width, height);
    }

    public Paddle setPlayer(int player) {
        this.player = player;
        return this;
    }

    public Paddle setRenderer(ShapeRenderer renderer) {
        this.renderer = renderer;
        return this;
    }

    public void dispose() {
        this.dispose();
    }
}
