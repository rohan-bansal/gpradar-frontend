package com.github.amhsrobotics.gpradar;

import com.badlogic.gdx.Gdx;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetworkTablesClient {

    private static NetworkTableInstance inst = NetworkTableInstance.getDefault();

    public static void run() {
        inst.startClientTeam(1351);
        inst.startDSClient();
        Gdx.app.log("Network Tables", "Started Server");

    }

    public static String getGamePieces() {
        return inst.getTable("dashboard").getEntry("gamepieces").getString("none");
    }

}
