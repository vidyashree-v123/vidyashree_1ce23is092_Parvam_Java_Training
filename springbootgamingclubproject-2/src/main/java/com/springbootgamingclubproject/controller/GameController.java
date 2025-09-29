package com.springbootgamingclubproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootgamingclubproject.api.ApiResponse;
import com.springbootgamingclubproject.dto.request.GameRequest;
import com.springbootgamingclubproject.dto.response.GameResponse;
import com.springbootgamingclubproject.service.GameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/games")
public class GameController {
	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<GameResponse>> create(@Valid @RequestBody GameRequest req) {
		return ResponseEntity.ok(ApiResponse.success("Game created", gameService.createGame(req)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<GameResponse>> getById(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.success("Game retrieved", gameService.getGameById(id)));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<GameResponse>>> getAll() {
		return ResponseEntity.ok(ApiResponse.success("Games list retrieved", gameService.getAllGames()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<GameResponse>> update(@PathVariable Long id,
			@Valid @RequestBody GameRequest req) {
		return ResponseEntity.ok(ApiResponse.success("Game updated", gameService.updateGame(id, req)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
		gameService.deleteGame(id);
		return ResponseEntity.ok(ApiResponse.success("Game deleted", null));
	}
}
