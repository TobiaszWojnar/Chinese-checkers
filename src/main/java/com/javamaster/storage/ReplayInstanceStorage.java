package com.javamaster.storage;

import com.javamaster.model.Game;
import com.javamaster.model.chineseCheckers.ReplayInstance;

import java.util.HashMap;
import java.util.Map;

public class ReplayInstanceStorage {

    private static Map<String, ReplayInstance> replays;
    private static ReplayInstanceStorage instance;

    private ReplayInstanceStorage() {
        replays = new HashMap<>();
    }

    public static synchronized ReplayInstanceStorage getInstance() {
        if (instance == null) {
            instance = new ReplayInstanceStorage();
        }
        return instance;
    }

    public Map<String, ReplayInstance> getReplays() {
        return replays;
    }

    public void setReplay(ReplayInstance replay) {
        replays.put(replay.getGameId(), replay);
    }
}
