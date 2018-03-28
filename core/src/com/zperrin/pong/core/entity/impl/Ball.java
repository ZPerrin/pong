package com.zperrin.pong.core.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.zperrin.pong.Pong;
import com.zperrin.pong.core.entity.IEntity;

import java.util.Random;

/**
 * Created by zebulonperrin on 3/10/18.
 */
public class Ball implements IEntity {


    private static final float DIAMETER = 16f;

    private ModelBatch modelBatch;
    private ModelBuilder modelBuilder = new ModelBuilder(); // todo: refactor?
    private Model model = modelBuilder.createSphere(DIAMETER, DIAMETER, DIAMETER, 10, 10,
            new Material(ColorAttribute.createDiffuse(Color.BLUE)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    private ModelInstance ball;
    private Vector3 position = new Vector3();
    private BoundingBox boundingBox;
    private Vector3 velocity = new Vector3();
    private Sound blip = Gdx.audio.newSound(Gdx.files.internal("pong-blip.wav"));
    private Paddle[] paddles;

    public Ball(Paddle[] paddles, ModelBatch modelBatch) {

        this.paddles = paddles;
        this.modelBatch = modelBatch;

        // model instantiation
        ball = new ModelInstance(model, 0f, 0f, IEntity.Z_OFFSET_2D);
        ball.transform.getTranslation(position);
        boundingBox = model.calculateBoundingBox(new BoundingBox()).mul(ball.transform);

        setRandomVelocity();
    }


    @Override
    public void update(float deltaTime) {
        // todo: prediction / correction physics

        // current position
        ball.transform.getTranslation(position);
        boundingBox.set(boundingBox.min.add(velocity), boundingBox.max.add(velocity));

        // collision logic for paddles
        if (paddles[0].intersects(boundingBox) || paddles[1].intersects(boundingBox)) {
            velocity.scl(-1, 1, 0);
            ball.transform.translate(velocity);
            blip.play();
        }
        // y axis bounds
        else if (position.y - boundingBox.getHeight() <= -Pong.HEIGHT / 2 || position.y + boundingBox.getHeight() >= Pong.HEIGHT / 2) {
            velocity.scl(1, -1, 0);
            ball.transform.translate(velocity);
            blip.play();
        }
        // x axis bounds
        else if (position.x - boundingBox.getWidth() >= Pong.WIDTH / 2 || position.x + boundingBox.getWidth() <= -Pong.WIDTH / 2) {
            ball.transform.setToTranslation(0f, 0f, IEntity.Z_OFFSET_2D);
            boundingBox.clr().mul(ball.transform);
            setRandomVelocity();
        }
        // regular movement
        else {
            ball.transform.translate(velocity);
        }
    }

    @Override
    public void render(Environment environment) {
        modelBatch.render(ball, environment);
    }

    @Override
    public void render3D(Environment environment) {
        // todo: do i need the 3d method?
        render(environment);
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
