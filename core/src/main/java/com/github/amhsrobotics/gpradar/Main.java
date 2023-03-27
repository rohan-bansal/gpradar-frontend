package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer renderer;

    private float angle = -1f;

    float centerX;
    float centerY;

    Vector2 center;
    Vector2 sweepLine1;
    Vector2 sweepLine2;

    Texture cone, cube;


    @Override
    public void create() {
        centerX = (float) Gdx.graphics.getWidth() / 2;
        centerY = (float) Gdx.graphics.getHeight() / 2;
        center = new Vector2(centerX, centerY);
        sweepLine1 = new Vector2(centerX, centerY + centerY - 25);
        sweepLine2 = sweepLine1.cpy().rotateAroundDeg(center, 2);
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        cone = new Texture(Gdx.files.internal("cone.png"));
        cube = new Texture(Gdx.files.internal("cube.png"));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(25 / 255f, 42 / 255f, 20 / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sweepLine1.rotateAroundDeg(center, angle);
        sweepLine2.rotateAroundDeg(center, angle);

        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.setColor(66 / 255f, 92 / 255f, 46 / 255f, 1f);
        Gdx.gl.glLineWidth(2);
        for(int i = 90; i < Gdx.graphics.getWidth(); i += 90) {
            for(int j = 90; j < Gdx.graphics.getHeight(); j += 90) {
                renderer.line(new Vector2(i, 0), new Vector2(i, (float) Gdx.graphics.getHeight())); // z
                renderer.line(new Vector2(0, j), new Vector2((float) Gdx.graphics.getWidth(), j)); // x
            }
        }

        renderer.setColor(151 / 255f, 222 / 255f, 123 / 255f, 1f);
        renderer.line(0, (float) Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight() / 2);
        renderer.line((float) Gdx.graphics.getWidth() / 2, 0, (float) Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());

        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glLineWidth(2);
        renderer.triangle(centerX, centerY, sweepLine1.x, sweepLine1.y, sweepLine2.x, sweepLine2.y);
        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);

        Gdx.gl.glLineWidth(4);
        renderer.circle(centerX, centerY, centerY - 25, 100);
        renderer.circle(centerX, centerY, centerY - 110, 100);
        renderer.circle(centerX, centerY, centerY - 195, 100);
        renderer.circle(centerX, centerY, centerY - 285, 100);
        renderer.circle(centerX, centerY, centerY - 375, 100);
        renderer.end();

        batch.begin();
//        batch.draw(cone, 150, 150);
//        batch.draw(cube, 550, 600);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
