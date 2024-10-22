package com.example.rockpaperscissors.model;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String playerId;
    private Move move;

    public Player(String playerId) {
        this.playerId = playerId;
    }

    // Getters and Setters

    public String getPlayerId() {
        return playerId;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}