package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class GameConfig {

    private static Lwjgl3ApplicationConfiguration config = null;

    GameConfig(){
        config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(120);
        config.setIdleFPS(120);
        config.setTitle("Grupp28_GDX");
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
    }

    public static Lwjgl3ApplicationConfiguration getInstance(){

        if(config == null){
            config = new Lwjgl3ApplicationConfiguration();
        }
        return config;
    }

}
