package com.mygdx.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class RenderUtil {
    public static void clearScreen(float r, float g, float b, float a)
    {
        Gdx.gl.glClearColor(r, g, b, a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void clearScreen(Color color)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void updateCamera()
    {
        MazeGame.camera.update(true);
        MazeGame.viewport.apply();
        MazeGame.batch.setProjectionMatrix(MazeGame.camera.combined);
        MazeGame.shape.setProjectionMatrix(MazeGame.camera.combined);
    }
}
