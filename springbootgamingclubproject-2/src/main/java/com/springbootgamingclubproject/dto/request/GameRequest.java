package com.springbootgamingclubproject.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GameRequest {
	@NotBlank(message = "Game name is required")
	@Size(max = 100, message = "Game name must be ≤100 chars")
	private String gameName;

	@NotNull(message = "Cost per hour is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Cost must be >0")
	private BigDecimal costPerHour;

	@NotBlank(message = "Status is required")
	private String status;

	public GameRequest() {
	}

	// Getters and setters
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
