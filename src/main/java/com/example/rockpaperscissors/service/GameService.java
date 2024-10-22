package com.example.rockpaperscissors.service;

import com.example.rockpaperscissors.exception.GameException;
import com.example.rockpaperscissors.model.GameSession;
import com.example.rockpaperscissors.model.Move;
import com.example.rockpaperscissors.storage.FileStorage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    private final FileStorage fileStorage = FileStorage.getInstance();

    public GameSession createGame(String playerOneId) {
        String sessionId = UUID.randomUUID().toString();
        GameSession session = new GameSession(sessionId, playerOneId);
        fileStorage.saveGameSession(session);
        return session;
    }

    public GameSession joinGame(String sessionId, String playerTwoId) throws GameException {
        GameSession session = fileStorage.loadGameSession(sessionId);
        if (session == null) {
            throw new GameException("Game session not found.");
        }
        if (session.getPlayerTwo() != null) {
            throw new GameException("Game session already has two players.");
        }
        session.setPlayerTwo(playerTwoId);
        fileStorage.saveGameSession(session);
        return session;
    }

    public GameSession makeMove(String sessionId, String playerId, Move move) throws GameException {
        GameSession session = fileStorage.loadGameSession(sessionId);
        if (session == null) {
            throw new GameException("Game session not found.");
        }

        if (session.isComplete()) {
            throw new GameException("Game session is already complete.");
        }

        if (session.getPlayerOne().getPlayerId().equals(playerId)) {
            if (session.getPlayerOne().getMove() != null) {
                throw new GameException("Player has already made a move.");
            }
            session.getPlayerOne().setMove(move);
        } else if (session.getPlayerTwo() != null && session.getPlayerTwo().getPlayerId().equals(playerId)) {
            if (session.getPlayerTwo().getMove() != null) {
                throw new GameException("Player has already made a move.");
            }
            session.getPlayerTwo().setMove(move);
        } else {
            throw new GameException("Player not part of this game session.");
        }

        // Check if both players have made a move
        if (session.getPlayerOne().getMove() != null && session.getPlayerTwo() != null && session.getPlayerTwo().getMove() != null) {
            determineResult(session);
            session.setComplete(true);
        }

        fileStorage.saveGameSession(session);
        return session;
    }

    public GameSession getGameState(String sessionId) throws GameException {
        GameSession session = fileStorage.loadGameSession(sessionId);
        if (session == null) {
            throw new GameException("Game session not found.");
        }
        return session;
    }

    private void determineResult(GameSession session) {
        Move moveOne = session.getPlayerOne().getMove();
        Move moveTwo = session.getPlayerTwo().getMove();

        if (moveOne == moveTwo) {
            session.setResult("It's a tie!");
        } else if (moveOne.beats(moveTwo)) {
            session.setResult("Player " + session.getPlayerOne().getPlayerId() + " wins!");
        } else {
            session.setResult("Player " + session.getPlayerTwo().getPlayerId() + " wins!");
        }
    }
}