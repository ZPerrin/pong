package com.zperrin.pong.core.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public interface IEntity {

    public static final float Z_OFFSET_2D = -16f;

    public void update(float deltaTime);

    public void render();

    public void render3D();

}
