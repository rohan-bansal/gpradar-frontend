package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class RadarManager {

    private static RadarManager radarManager;

    private ShapeRenderer shapeRenderer;

    private Vector2 windowCenter;
    private Vector2 sweepLineComponent1, sweepLineComponent2;

    private static float DELTA_ANGLE = -1f;
    private static int SWEEP_LINE_THICKNESS = 2;

    public static RadarManager getInstance() {
        if(radarManager == null) {
            radarManager = new RadarManager();
        }
        return radarManager;
    }

    public void initSystem() {
        shapeRenderer = new ShapeRenderer();

        float centerX = (float) Gdx.graphics.getWidth() / 2;
        float centerY = (float) Gdx.graphics.getHeight() / 2;

        windowCenter = new Vector2(centerX, centerY);

        sweepLineComponent1 = new Vector2(centerX, centerY + centerY - 25);
        sweepLineComponent2 = sweepLineComponent1.cpy().rotateAroundDeg(windowCenter, SWEEP_LINE_THICKNESS);
    }

    private void drawGrid() {
        shapeRenderer.setColor(66 / 255f, 92 / 255f, 46 / 255f, 1f);
        Gdx.gl.glLineWidth(2);
        for(int i = 90; i < Gdx.graphics.getWidth(); i += 90) {
            for(int j = 90; j < Gdx.graphics.getHeight(); j += 90) {
                shapeRenderer.line(new Vector2(i, 0), new Vector2(i, (float) Gdx.graphics.getHeight()));
                shapeRenderer.line(new Vector2(0, j), new Vector2((float) Gdx.graphics.getWidth(), j));
            }
        }

        shapeRenderer.setColor(151 / 255f, 222 / 255f, 123 / 255f, 1f);
        shapeRenderer.line(0, (float) Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight() / 2);
        shapeRenderer.line((float) Gdx.graphics.getWidth() / 2, 0, (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
    }

    private void drawSweepLine() {
        Gdx.gl.glLineWidth(2);
        shapeRenderer.triangle(windowCenter.x, windowCenter.y, sweepLineComponent1.x, sweepLineComponent1.y, sweepLineComponent2.x, sweepLineComponent2.y);
    }

    private void drawConcentricCircles() {
        Gdx.gl.glLineWidth(4);
        shapeRenderer.circle(windowCenter.x, windowCenter.y, windowCenter.y - 25, 100);
        shapeRenderer.circle(windowCenter.x, windowCenter.y, windowCenter.y - 110, 100);
        shapeRenderer.circle(windowCenter.x, windowCenter.y, windowCenter.y - 195, 100);
        shapeRenderer.circle(windowCenter.x, windowCenter.y, windowCenter.y - 285, 100);
        shapeRenderer.circle(windowCenter.x, windowCenter.y, windowCenter.y - 375, 100);
    }

    public void render() {
        sweepLineComponent1.rotateAroundDeg(windowCenter, DELTA_ANGLE);
        sweepLineComponent2.rotateAroundDeg(windowCenter, DELTA_ANGLE);

        shapeRenderer.setProjectionMatrix(CameraManager.getInstance().getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawGrid();
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawSweepLine();
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawConcentricCircles();
        shapeRenderer.end();

    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
