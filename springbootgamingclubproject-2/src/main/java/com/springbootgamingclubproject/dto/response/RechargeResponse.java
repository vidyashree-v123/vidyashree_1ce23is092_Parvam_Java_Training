package com.springbootgamingclubproject.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RechargeResponse {
	private Long rechargeId;
	private Long memberId;
	private BigDecimal amount;
	private LocalDateTime rechargeDate;

	// Getters & Setters
	public Long getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(Long rechargeId) {
		this.rechargeId = rechargeId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(LocalDateTime rechargeDate) {
		this.rechargeDate = rechargeDate;
	}
}
