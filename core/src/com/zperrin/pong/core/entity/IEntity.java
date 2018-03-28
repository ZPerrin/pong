package com.zperrin.pong.core.entity;

import com.badlogic.gdx.graphics.g3d.Environment;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public interface IEntity {

    public static final float Z_OFFSET_2D = -16f; // todo: is this needed if I just used perspective cam + top down view?

    public void update(float deltaTime);

    public void render(Environment environment);

    public void render3D(Environment environment);

}
