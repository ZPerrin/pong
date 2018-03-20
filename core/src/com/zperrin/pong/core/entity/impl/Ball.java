package com.zperrin.pong.core.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.zperrin.pong.Pong;
import com.zperrin.pong.core.entity.IEntity;

import java.util.Random;

/**
 * Created by zebulonperrin on 3/10/18.
 */
public class Ball extends Circle implements IEntity {


    private static final Vector3 ZERO_VECTOR = new Vector3(0f, 0f, 0f);
    private ModelBatch modelBatch;
    private ModelBuilder modelBuilder = new ModelBuilder(); // todo: refactor?
    private Model model = modelBuilder.createSphere(16f, 16f, 16f, 10, 10,
            new Material(ColorAttribute.createDiffuse(Color.WHITE)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    // actual model
    private ModelInstance ball;
    private Vector3 position3D;
    private BoundingBox boundingBox;
    private Vector3 velocity = new Vector3(0f, 0f, 0f);

    private Vector3 position = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    private Sound blip = Gdx.audio.newSound(Gdx.files.internal("pong-blip.wav"));
    private Paddle[] paddles;
    private ShapeRenderer renderer;

    public Ball(Paddle[] paddles, ModelBatch modelBatch) {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 8); // todo static?
        this.paddles = paddles;
        //this.renderer = renderer;
        this.modelBatch = modelBatch;

        // model instantiation
        ball = new ModelInstance(model, 0f, 0f, IEntity.Z_OFFSET_2D);
        position3D = ball.transform.getTranslation(ZERO_VECTOR);
        boundingBox = model.calculateBoundingBox(new BoundingBox()).ext(ZERO_VECTOR, 32f);
        //boundingBox.

        setRandomVelocity();
    }


    @Override
    public void update(float deltaTime) {


        // collision logic for paddles
/*        if (Intersector.overlaps(this, paddles[0].getBoundingRectangle()) || Intersector.overlaps(this, paddles[1].getBoundingRectangle())) {
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
        }*/

//        if (Intersector.overlaps(this, paddles[0].getBoundingRectangle()) || Intersector.overlaps(this, paddles[1].getBoundingRectangle())) {
//            //setPosition(velocity.scl(-1, 1, 0), true);
//        } else if (position.y + this.radius >= Gdx.graphics.getHeight()) {
//            //setPosition(velocity.scl(1, -1, 0), true);
//        } else if (position.y - this.radius <= 0) {
//            //setPosition(velocity.scl(1, -1, 0), true);
//        } else if (position.x + this.radius <= 0 || position.x - this.radius >= Gdx.graphics.getWidth()) {
//            setRandomVelocity();
//            //position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
//        } else {
//            //setPosition(velocity, false);
//        }

        //position3D = ;
        //System.out.println(position3D.toString());
        System.out.println("ball bb before mul: " + boundingBox.getCenter(new Vector3()));
        boundingBox = new BoundingBox().clr().mul(ball.transform);
        System.out.println("ball bb after mul: " +boundingBox.getCenter(new Vector3()));
        if (paddles[0].intersects(boundingBox) || paddles[1].intersects(boundingBox)) {
            velocity.scl(-1, 1, 0);
            ball.transform.translate(velocity);
        }

/*        if (Intersector.overlaps(this, paddles[0].getBoundingRectangle()) || Intersector.overlaps(this, paddles[1].getBoundingRectangle())) {
            setPosition(velocity.scl(-1, 1, 0), true);
            //boundingBox.contains(paddles[0].getB)
        }*/

        if (position3D.x + boundingBox.getWidth() >= Pong.WIDTH / 2 || position3D.x - boundingBox.getWidth() <= -1 * (Pong.WIDTH / 2)) {
            ball.transform.setToTranslation(0f, 0f, -16f);
            setRandomVelocity();
        } else {
            //System.out.println(velocity.toString());
            ball.transform.translate(velocity);
        }

        System.out.println("ball position: " + position3D);
/*        System.out.println(velocity.toString());
        ball.transform.translate(velocity);*/


        //this.x = position.x;
        //this.y = position.y;

    }

    // TODO: do we need the 3d method?
    @Override
    public void render() {
        modelBatch.render(ball);
    }

    @Override
    public void render3D() {
        render();
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
        this.velocity = new Vector3(1, 0, 0);
    }

    public void dispose() {
        //this.dispose();
    }
}
