package com.example.rockpaperscissors.model;

public enum Move {
    ROCK, PAPER, SCISSORS;

    /**
     * Determines if this move beats the opponent's move.
     * @param opponentMove The opponent's move.
     * @return True if this move beats the opponent's move, false otherwise.
     */
    public boolean beats(Move opponentMove) {
        switch (this) {
            case ROCK:
                return opponentMove == SCISSORS;
            case PAPER:
                return opponentMove == ROCK;
            case SCISSORS:
                return opponentMove == PAPER;
            default:
                return false;
        }
    }
}