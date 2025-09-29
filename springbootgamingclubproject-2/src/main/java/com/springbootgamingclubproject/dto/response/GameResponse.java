package com.springbootgamingclubproject.dto.response;

import java.math.BigDecimal;

public class GameResponse {
	private Long gameId;
	private String gameName;
	private BigDecimal costPerHour;
	private String status;

	public GameResponse() {
	}

	public GameResponse(Long gameId, String gameName, BigDecimal costPerHour, String status) {
		this.gameId = gameId;
		this.gameName = gameName;
		this.costPerHour = costPerHour;
		this.status = status;
	}

	// Getters and setters
	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public BigDecimal getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(BigDecimal costPerHour) {
		this.costPerHour = costPerHour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
