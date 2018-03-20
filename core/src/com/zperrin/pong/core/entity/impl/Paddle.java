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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.zperrin.pong.Pong;
import com.zperrin.pong.core.entity.IEntity;

/**
 * Created by Zebulon on 3/8/2018.
 */
public class Paddle extends Polygon implements IEntity {

    private static final float SCALE = 400f;
    private static final float WIDTH = 8f;
    private static final float HEIGHT = 48f;
    private static final float DEPTH = 16f;

    private int player;
    private ShapeRenderer renderer;

    private static final Vector3 ZERO_VECTOR = new Vector3(0f, 0f, 0f);
    private ModelBatch modelBatch;
    private ModelBuilder modelBuilder = new ModelBuilder(); // todo: refactor?
    private Model model = modelBuilder.createBox(WIDTH, HEIGHT, DEPTH,
            new Material(ColorAttribute.createDiffuse(Color.WHITE)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    // actual model
    private ModelInstance paddle;
    private Vector3 position3D;
    private BoundingBox boundingBox;
    //private Vector3 velocity = new Vector3(0f, 0f, 0f);

    public Paddle(int player, ShapeRenderer renderer, ModelBatch modelBatch) {

        super(new float[]{0f, 0f, 10f, 0f, 10f, 50f, 0f, 50f});
        float halfViewPortHeight = (Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight()) / 2;
        //this.setOrigin(0, 0);
        //this.setPosition(0, halfViewPortHeight);
        this.player = player;
        //this.renderer = renderer;
        this.modelBatch = modelBatch;
        this.boundingBox = model.calculateBoundingBox(new BoundingBox());


        if(player == 1) {
            paddle = new ModelInstance(model, -Pong.HEIGHT/2 + 4, 0f, IEntity.Z_OFFSET_2D);
        } else {
            //this.setPosition(Gdx.graphics.getWidth() - this.getBoundingRectangle().getWidth(), halfViewPortHeight);
            paddle = new ModelInstance(model, Pong.WIDTH/2 - 4, 0f, IEntity.Z_OFFSET_2D);
        }
    }

    public boolean intersects(BoundingBox box) {
        boolean intersects = boundingBox.clr().mul(paddle.transform).intersects(box);
        return intersects;
    }

    // todo: refactor controls
    @Override
    public void update(float deltaTime) {

        if (this.player == 1) {

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                this.translate(0, SCALE * Gdx.graphics.getDeltaTime());
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                this.translate(0, -SCALE * Gdx.graphics.getDeltaTime());
            }

            if (this.getY() > Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight()) {
                this.setPosition(getX(), Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight());
            }

            if (this.getY() < 0) {
                this.setPosition(getX(), 0);
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
                this.setPosition(getX(), Gdx.graphics.getHeight() - this.getBoundingRectangle().getHeight());
            }

            if (this.getY() < 0) {
                this.setPosition(getX(), 0);
            }
        }
    }

    @Override
    public void render() {
        //Rectangle bounds = this.getBoundingRectangle();
       // renderer.rect(this.getX(), this.getY(), bounds.width, bounds.height);
        modelBatch.render(paddle);
    }

    @Override
    public void render3D() {

    }

    public void dispose() {
        //this.dispose();
    }
}
