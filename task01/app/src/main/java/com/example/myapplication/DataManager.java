package com.example.myapplication;
//ЛОГИН!!!!!

public class DataManager {
    private static DataManager instance;
    private String playerName;

    private DataManager() {
    }
    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
