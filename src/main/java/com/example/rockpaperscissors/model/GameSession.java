package com.example.rockpaperscissors.model;

import java.io.Serializable;

public class GameSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sessionId;
    private Player playerOne;
    private Player playerTwo;
    private String result;
    private boolean isComplete;

    public GameSession(String sessionId, String playerOneId) {
        this.sessionId = sessionId;
        this.playerOne = new Player(playerOneId);
        this.isComplete = false;
    }

    // Getters and Setters

    public String getSessionId() {
        return sessionId;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwoId) {
        this.playerTwo = new Player(playerTwoId);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}