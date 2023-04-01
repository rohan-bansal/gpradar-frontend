package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class GamePieceManager {

    private static GamePieceManager gamePieceManager;

    private SpriteBatch batch;

    private ArrayList<GamePiece> gamePieces;

    public static GamePieceManager getInstance() {
        if(gamePieceManager == null) {
            gamePieceManager = new GamePieceManager();
        }
        return gamePieceManager;
    }

    public void initSystem() {
        batch = new SpriteBatch();
        gamePieces = new ArrayList<>();
    }

    public void update() {
        gamePieces.clear();

        GamePiece gp = new GamePiece(GamePiece.Type.CUBE);
        gp.setPosition(-40, 300);

        gamePieces.add(gp);
    }

    public void render() {

        batch.setProjectionMatrix(CameraManager.getInstance().getCamera().combined);
        batch.begin();

        for(GamePiece gp : gamePieces) {
            gp.render(batch);
        }

        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }

}
