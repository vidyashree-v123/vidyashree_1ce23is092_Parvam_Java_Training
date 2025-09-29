package com.springbootgamingclubproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgamingclubproject.dto.request.GameRequest;
import com.springbootgamingclubproject.dto.response.GameResponse;
import com.springbootgamingclubproject.entity.Game;
import com.springbootgamingclubproject.exception.ResourceNotFoundException;
import com.springbootgamingclubproject.repository.GameRepository;
import com.springbootgamingclubproject.util.MapperUtil;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;

	public GameResponse createGame(GameRequest req) {
		Game g = new Game(req.getGameName(), req.getCostPerHour(), Game.Status.valueOf(req.getStatus().toUpperCase()));
		return MapperUtil.gameToResponse(gameRepository.save(g));
	}

	public GameResponse updateGame(Long id, GameRequest req) {
		Game g = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game not found: " + id));
		g.setGameName(req.getGameName());
		g.setCostPerHour(req.getCostPerHour());
		g.setStatus(Game.Status.valueOf(req.getStatus().toUpperCase()));
		return MapperUtil.gameToResponse(gameRepository.save(g));
	}

	public GameResponse getGameById(Long id) {
		Game g = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game not found: " + id));
		return MapperUtil.gameToResponse(g);
	}

	public List<GameResponse> getAllGames() {
		List<GameResponse> res = new ArrayList<>();
		for (Game g : gameRepository.findAll()) {
			res.add(MapperUtil.gameToResponse(g));
		}
		return res;
	}

	public void deleteGame(Long id) {
		Game g = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game not found: " + id));
		gameRepository.delete(g);
	}
}
