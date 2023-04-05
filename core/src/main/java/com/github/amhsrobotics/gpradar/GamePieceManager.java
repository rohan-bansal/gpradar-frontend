package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class GamePieceManager {

    private static GamePieceManager gamePieceManager;

    private SpriteBatch batch;

    private ArrayList<GamePiece> gamePieces;
    private ArrayList<GamePiece> gamePieceQueue;

    public static GamePieceManager getInstance() {
        if(gamePieceManager == null) {
            gamePieceManager = new GamePieceManager();
        }
        return gamePieceManager;
    }

    public void initSystem() {
        batch = new SpriteBatch();
        gamePieces = new ArrayList<>();
        gamePieceQueue = new ArrayList<>();
    }

    public void addGamePiece(GamePiece.Type type, float angleX, float distance) {
        GamePiece gp = new GamePiece(type);
        gp.setPosition(angleX, distance);

        gamePieceQueue.add(gp);
    }

    public void update() {
        gamePieces.clear();

        gamePieces.addAll(gamePieceQueue);

        gamePieceQueue.clear();
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
