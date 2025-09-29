package com.springbootgamingclubproject.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class RechargeRequestDTO {
    @NotNull
    private Long memberId;

    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;

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
}
