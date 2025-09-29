


































































                                                                                                                                                                                                                                                                                                                                                                                        package com.springbootgamingclubproject.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recharges")
public class Recharge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rechargeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false)
	private LocalDateTime rechargeDate;

	public Recharge() {
	}

	public Recharge(Member member, BigDecimal amount, LocalDateTime rechargeDate) {
		this.member = member;
		this.amount = amount;
		this.rechargeDate = rechargeDate;
	}

	// Getters and Setters
	public Long getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(Long rechargeId) {
		this.rechargeId = rechargeId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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
