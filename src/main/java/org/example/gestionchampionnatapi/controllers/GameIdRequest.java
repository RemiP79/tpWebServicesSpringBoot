package org.example.gestionchampionnatapi.controllers;

import java.util.List;

public class GameIdRequest {
    private List<Long> gameIds;

    public List<Long> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<Long> gameIds) {
        this.gameIds = gameIds;
    }
}
