package com.zperrin.pong.core.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
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

/**
 * Created by Zebulon on 3/8/2018.
 */
public class Paddle implements IEntity {

    private static final float SCALE = 400f;
    private static final float WIDTH = 8f;
    private static final float HEIGHT = 48f;
    private static final float DEPTH = 16f;
    private static final Vector3 P1_INITIAL_POSITION = new Vector3(-Pong.HEIGHT / 2 + 4, 0f, IEntity.Z_OFFSET_2D);
    private static final Vector3 P2_INITIAL_POSITION = new Vector3(Pong.WIDTH / 2 - 4, 0f, IEntity.Z_OFFSET_2D);

    private int player;
    private ModelBatch modelBatch;
    private ModelBuilder modelBuilder = new ModelBuilder();
    private Model model = modelBuilder.createBox(WIDTH, HEIGHT, DEPTH,
            new Material(ColorAttribute.createDiffuse(Color.WHITE)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    private ModelInstance paddle;
    private Vector3 position = new Vector3();
    private Vector3 velocity = new Vector3();
    private Vector3 nextTranslation = new Vector3();
    private BoundingBox boundingBox;

    public Paddle(int player, ModelBatch modelBatch) {

        this.player = player;
        this.modelBatch = modelBatch;

        // model instantiation
        if (player == 1) {
            paddle = new ModelInstance(model, P1_INITIAL_POSITION);
        } else {
            paddle = new ModelInstance(model, P2_INITIAL_POSITION);
        }
        boundingBox = model.calculateBoundingBox(new BoundingBox()).mul(paddle.transform);
        paddle.transform.getTranslation(position);

    }

    @Override
    public void update(float deltaTime) {
        // todo: make player2 AI

        // current position
        paddle.transform.getTranslation(position);

        // player 1 controls
        if (this.player == 1) {

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                moveUp();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                moveDown();
            }
        }

        // player 2 controls
        if (this.player == 2) {

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                moveUp();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                moveDown();
            }
        }
    }

    /**
     * Move the paddle up in a 2d space
     */
    private void moveUp() {

        velocity = new Vector3(0, SCALE * Gdx.graphics.getDeltaTime(), 0);
        nextTranslation = position.add(velocity);

        if (nextTranslation.y > Pong.HEIGHT / 2 - HEIGHT / 2) {
            // top bound
            paddle.transform.setToTranslation(position.x, Pong.HEIGHT / 2 - HEIGHT / 2, IEntity.Z_OFFSET_2D);
            // todo: update bounding box
        } else {
            paddle.transform.translate(velocity);
            boundingBox.set(boundingBox.min.add(velocity), boundingBox.max.add(velocity));
        }
    }

    /**
     * Move the paddle down in a 2d space
     */
    private void moveDown() {
        velocity = new Vector3(0, -SCALE * Gdx.graphics.getDeltaTime(), 0);
        nextTranslation = position.add(velocity);

        if (nextTranslation.y < -Pong.HEIGHT / 2 + HEIGHT / 2) {
            // bottom bound
            paddle.transform.setToTranslation(position.x, -Pong.HEIGHT / 2 + HEIGHT / 2, IEntity.Z_OFFSET_2D);
            // todo: update bounding box
        } else {
            paddle.transform.translate(velocity);
            boundingBox.set(boundingBox.min.add(velocity), boundingBox.max.add(velocity));
        }
    }

    /**
     * Collision detection
     *
     * @param box - {@link BoundingBox} instance to compare against
     * @return boolean
     */
    public boolean intersects(BoundingBox box) {
        return boundingBox.intersects(box);
    }

    @Override
    public void render() {
        modelBatch.render(paddle);
    }

    @Override
    public void render3D() {

    }

    public void dispose() {
        //this.dispose();
    }
}
