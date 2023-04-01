package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;

public class CameraManager {

    private static CameraManager cameraManager;

    private OrthographicCamera camera;

    float timeToCameraZoomTarget, cameraZoomTarget, cameraZoomOrigin, cameraZoomDuration;


    public static CameraManager getInstance() {
        if(cameraManager == null) {
            cameraManager = new CameraManager();
        }
        return cameraManager;
    }

    public void initSystem() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.zoom = 0.1f;
        zoomTo(1f, 2);

    }

    private void zoomTo(float newZoom, float duration) {
        cameraZoomOrigin = camera.zoom;
        cameraZoomTarget = newZoom;
        timeToCameraZoomTarget = cameraZoomDuration = duration;
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        camera.update();

        if (timeToCameraZoomTarget >= 0) {
            timeToCameraZoomTarget -= deltaTime;
            float progress = timeToCameraZoomTarget < 0 ? 1 : 1f - timeToCameraZoomTarget / cameraZoomDuration;
            camera.zoom = Interpolation.pow3Out.apply(cameraZoomOrigin, cameraZoomTarget, progress);
        }

    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}
