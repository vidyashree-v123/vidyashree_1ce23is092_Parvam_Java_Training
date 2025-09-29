package com.springbootgamingclubproject.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class TransactionRequestDTO {
	@NotNull
	private Long memberId;
	@NotNull
	private Long gameId;
	@NotNull
	@DecimalMin("0.01")
	private BigDecimal playTimeHrs;
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public BigDecimal getPlayTimeHrs() {
		return playTimeHrs;
	}
	public void setPlayTimeHrs(BigDecimal playTimeHrs) {
		this.playTimeHrs = playTimeHrs;
	}
}
