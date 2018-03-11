package com.zperrin.pong.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by zebulonperrin on 3/10/18.
 */
public class Ball extends Circle {

    private ShapeRenderer renderer;
    private Vector2 position = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    private Vector2 velocity = new Vector2(2f, 0f);

    public Ball(ShapeRenderer renderer) {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 8);
        this.renderer = renderer;
    }

    public void render(List<Paddle> paddleList) {
        renderer.setColor(1, 1, 1, 1);


        // todo: angles

        // collision logic
        if (Intersector.overlaps(this, paddleList.get(0).getBoundingRectangle()) || Intersector.overlaps(this, paddleList.get(1).getBoundingRectangle())) {
            position.add(velocity.scl(-1));
        } else {
            position.add(velocity);
        }

        // update circle position for renderer
        this.x = position.x;
        this.y = position.y;


        renderer.circle(position.x, position.y, radius);
    }

    public void dispose() {
        this.dispose();
    }
}
