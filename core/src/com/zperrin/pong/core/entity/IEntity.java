package com.zperrin.pong.core.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by zebulonperrin on 3/14/18.
 */
public interface IEntity {

    public void update(float deltaTime);

    public void render(ShapeRenderer renderer);

}
