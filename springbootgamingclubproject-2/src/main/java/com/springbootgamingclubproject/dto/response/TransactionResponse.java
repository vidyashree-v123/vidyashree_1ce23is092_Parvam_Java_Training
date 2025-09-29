package com.springbootgamingclubproject.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
	private Long transactionId;
	private Long memberId;
	private Long gameId;
	private BigDecimal playTimeHrs;
	private BigDecimal cost;
	private LocalDateTime transactionDate;

	// Getters & Setters
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
}
