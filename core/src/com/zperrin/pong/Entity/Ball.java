package com.zperrin.pong.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javafx.scene.shape.Circle;

/**
 * Created by zebulonperrin on 3/10/18.
 */
public class Ball extends Circle {

    private ShapeRenderer renderer;
    private static float SCALE = 150;

    public Ball(ShapeRenderer renderer) {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 8);
        this.renderer = renderer;
    }

    public void render() {
        renderer.setColor(1, 1, 1, 1);
        this.setCenterX(getCenterX() + SCALE * Gdx.graphics.getDeltaTime());
        renderer.circle((float) getCenterX(), (float) getCenterY(), (float) getRadius());
    }

    public void dispose() {
        this.dispose();
    }
}
