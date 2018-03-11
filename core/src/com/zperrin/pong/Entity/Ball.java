package com.zperrin.pong.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

/**
 * Created by zebulonperrin on 3/10/18.
 */
public class Ball extends Circle {

    private ShapeRenderer renderer;
    private Vector2 position = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    private Vector2 velocity = new Vector2();

    public Ball(ShapeRenderer renderer) {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 8);
        this.renderer = renderer;
        setRandomVelocity();
    }

    public void render(List<Paddle> paddleList) {
        renderer.setColor(1, 1, 1, 1);

        // collision logic for paddles
        if (Intersector.overlaps(this, paddleList.get(0).getBoundingRectangle()) || Intersector.overlaps(this, paddleList.get(1).getBoundingRectangle())) {
            position.add(velocity.scl(-1, 1));
        } else {
            position.add(velocity);
        }

        // collision logic for top/bottom
        if (position.y + this.radius >= Gdx.graphics.getHeight()) {
            position.add(velocity.scl(1, -1));
        }
        if (position.y - this.radius <= 0) {
            position.add(velocity.scl(1, -1));
        }

        // reset if completely off screen;
        if (position.x + this.radius <= 0 || position.x - this.radius >= Gdx.graphics.getWidth()) {
            setRandomVelocity();
            position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        }

        // update circle position for renderer
        this.x = position.x;
        this.y = position.y;


        renderer.circle(position.x, position.y, radius);
    }

    private void setRandomVelocity() {
        Random r = new Random();
        double x = 1 + r.nextDouble() * (2 - 1);
        double y = 0 + r.nextDouble() * (1 - 0);
        double d = 0 + r.nextDouble() * (1 - 0);
        this.velocity.set((float) x, (float) y);
        if(d <= .25) {
            this.velocity = velocity.nor().scl(4, 4);
        } else if (d <= .40){
            this.velocity = velocity.nor().scl(-4, 4);
        } else if (d <= .75) {
            this.velocity = velocity.nor().scl(4, -4);
        } else {
            this.velocity = velocity.nor().scl(-4, -4);
        }
    }

    public void dispose() {
        this.dispose();
    }
}
