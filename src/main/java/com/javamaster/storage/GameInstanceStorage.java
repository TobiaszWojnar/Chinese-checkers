package com.javamaster.storage;

import com.javamaster.model.chineseCheckers.GameInstance;

import java.util.HashMap;
import java.util.Map;

public class GameInstanceStorage {

    private static Map<String, GameInstance> gameInstances;
    private static GameInstanceStorage instance;

    private GameInstanceStorage() {
        gameInstances = new HashMap<>();
    }

    public static synchronized GameInstanceStorage getInstance() {
        if (instance == null) {
            instance = new GameInstanceStorage();
        }
        return instance;
    }

    public Map<String, GameInstance> getGames() {
        return gameInstances;
    }

    public void setGameInstance(GameInstance gameInstance) {
        gameInstances.put(gameInstance.getGameId(), gameInstance);
    }
}
