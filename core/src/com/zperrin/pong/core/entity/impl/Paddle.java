package com.zperrin.pong.core.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.zperrin.pong.core.entity.IEntity;

/**
 * Created by Zebulon on 3/8/2018.
 */
public class Paddle extends Polygon implements IEntity {

    private static final float SCALE = 400f;
    private int player;

    public Paddle(int player) {
        super(new float[]{0f, 0f, 10f, 0f, 10f, 50f, 0f, 50f});
        float yPos = (Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight()) / 2;
        this.setOrigin(0, 0);
        this.setPosition(0, yPos);
        this.player = player;

        if (player == 1) {
            this.setPosition(Gdx.graphics.getWidth() - this.getBoundingRectangle().getWidth(), yPos);
        }
    }

    // todo: refactor controls
    @Override
    public void update(float deltaTime) {
        if (this.player == 1) {
            float xPos = Gdx.graphics.getWidth() - this.getBoundingRectangle().getWidth();
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                this.translate(0, SCALE * Gdx.graphics.getDeltaTime());
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                this.translate(0, -SCALE * Gdx.graphics.getDeltaTime());
            }

            if (this.getY() > Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight()) {
                this.setPosition(xPos, Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight());
            }

            if (this.getY() < 0) {
                this.setPosition(xPos, 0);
            }
        }

        if (this.player == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                this.translate(0, SCALE * Gdx.graphics.getDeltaTime());
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                this.translate(0, -SCALE * Gdx.graphics.getDeltaTime());
            }

            if (this.getY() > Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight()) {
                this.setPosition(0, Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight());
            }

            if (this.getY() < 0) {
                this.setPosition(0, 0);
            }
        }
    }

    @Override
    public void render(ShapeRenderer renderer) {
        Rectangle bounds = this.getBoundingRectangle();
        renderer.rect(this.getX(), this.getY(), bounds.width, bounds.height);
    }

    public void dispose() {
        //this.dispose();
    }
}