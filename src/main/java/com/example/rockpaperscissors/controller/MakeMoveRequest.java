package com.example.rockpaperscissors.controller;

import com.example.rockpaperscissors.model.Move;

public class MakeMoveRequest {
    private String playerId;
    private Move move;

    // Getters and Setters

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}