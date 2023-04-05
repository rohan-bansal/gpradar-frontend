package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main extends ApplicationAdapter {

    @Override
    public void create() {
        CameraManager.getInstance().initSystem();
        RadarManager.getInstance().initSystem();
        GamePieceManager.getInstance().initSystem();
        NetworkTablesClient.run();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(25 / 255f, 42 / 255f, 20 / 255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        String data = NetworkTablesClient.getGamePieces();
        if(!data.equals("none")) {
            try {
                JSONObject dataParsed = new JSONObject(data);

                JSONArray cones = dataParsed.getJSONArray("cones");
                JSONArray cubes = dataParsed.getJSONArray("cubes");

                for(int i = 0; i < cones.length(); i++) {
                    double dist = cones.getJSONObject(i).getDouble("distance");
                    double angleX = cones.getJSONObject(i).getDouble("anglex");

                    GamePieceManager.getInstance().addGamePiece(GamePiece.Type.CONE, (float) angleX, (float) dist);
                }

                for(int i = 0; i < cubes.length(); i++) {
                    double dist = cubes.getJSONObject(i).getDouble("distance");
                    double angleX = cubes.getJSONObject(i).getDouble("anglex");

                    GamePieceManager.getInstance().addGamePiece(GamePiece.Type.CUBE, (float) angleX, (float) dist);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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
