package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Main extends ApplicationAdapter {

    @Override
    public void create() {
        CameraManager.getInstance().initSystem();
        RadarManager.getInstance().initSystem();
        GamePieceManager.getInstance().initSystem();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(25 / 255f, 42 / 255f, 20 / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        CameraManager.getInstance().update();
        GamePieceManager.getInstance().update();

        RadarManager.getInstance().render();
        GamePieceManager.getInstance().render();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        RadarManager.getInstance().dispose();
        GamePieceManager.getInstance().dispose();
    }
}
