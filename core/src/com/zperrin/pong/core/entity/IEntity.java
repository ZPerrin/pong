package com.zperrin.pong.core.entity;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public interface IEntity {

    public static final float Z_OFFSET_2D = -16f; // todo: is this needed if I just used perspective cam + top down view?

    public void update(float deltaTime);

    public void render();

    public void render3D();

}
