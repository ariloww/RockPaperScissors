package com.example.rockpaperscissors.controller;

import com.example.rockpaperscissors.exception.GameException;
import com.example.rockpaperscissors.model.GameSession;
import com.example.rockpaperscissors.service.GameService;
import com.example.rockpaperscissors.model.Move;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new game session", description = "Creates a new game session and returns the session ID.")
    @ApiResponse(responseCode = "200", description = "Game session created successfully")
    public ResponseEntity<GameSession> createGameSession(@RequestBody CreateGameRequest request) {
        GameSession session = gameService.createGame(request.getPlayerId());
        return ResponseEntity.ok(session);
    }

    @PostMapping("/join")
    @Operation(summary = "Join an existing game session", description = "Allows a player to join an existing game session.")
    @ApiResponse(responseCode = "200", description = "Joined game session successfully")
    public ResponseEntity<?> joinGameSession(@RequestBody JoinGameRequest request) {
        try {
            GameSession session = gameService.joinGame(request.getSessionId(), request.getPlayerId());
            return ResponseEntity.ok(session);
        } catch (GameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{sessionId}/move")
    @Operation(summary = "Make a move", description = "Allows a player to make a move in a game session.")
    @ApiResponse(responseCode = "200", description = "Move made successfully")
    public ResponseEntity<?> makeMove(@PathVariable String sessionId, @RequestBody MakeMoveRequest request) {
        try {
            GameSession session = gameService.makeMove(sessionId, request.getPlayerId(), request.getMove());
            return ResponseEntity.ok(session);
        } catch (GameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{sessionId}/state")
    @Operation(summary = "Get game state", description = "Retrieves the current state of a game session.")
    @ApiResponse(responseCode = "200", description = "Game state retrieved successfully")
    public ResponseEntity<?> getGameState(@PathVariable String sessionId) {
        try {
            GameSession session = gameService.getGameState(sessionId);
            return ResponseEntity.ok(session);
        } catch (GameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}