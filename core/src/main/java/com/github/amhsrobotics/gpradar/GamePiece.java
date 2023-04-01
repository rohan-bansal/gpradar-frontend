package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GamePiece {

    enum Type { CONE, CUBE }

    private static int ID_INCRIM = 1;

    private Sprite image;
    private Vector2 position;
    private int id = 0;
    private Type type;

    public GamePiece(Type type) {
        this.id = ID_INCRIM;
        ID_INCRIM++;

        this.type = type;

        if(this.type == Type.CONE) {
            image = new Sprite(new Texture(Gdx.files.internal("cone.png")));
        } else if(this.type == Type.CUBE) {
            image = new Sprite(new Texture(Gdx.files.internal("cube.png")));
        }
        assert image != null;

        position = new Vector2(0, 0);
        image.setOriginCenter();
        image.setScale(0.75f);
        image.setOriginBasedPosition(position.x, position.y);
    }

    public void setPosition(float angleX, float distance) {

        if(angleX > 0) {
            angleX = 90 - angleX;
        } else if(angleX < 0) {
            angleX = 90 + Math.abs(angleX);
        } else {
            angleX = 90;
        }

        // pixels
        float virtMin = (float) Gdx.graphics.getHeight() / 2;
        float virtMax = (float) Gdx.graphics.getHeight();

        // centimeters
        float rawMin = 0;
        float rawMax = 400;

//        float distNormalized = (virtMax - virtMin) * ((distance - rawMin)/(rawMax - rawMin)) + virtMin;

        double x = distance * Math.cos(Math.toRadians(angleX));
        double y = distance * Math.sin(Math.toRadians(angleX));

        position.set((float) Gdx.graphics.getWidth() / 2 + (float) x, (float) Gdx.graphics.getHeight() / 2 + (float) y);
        image.setOriginBasedPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch) {
        image.draw(batch);
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }
}
