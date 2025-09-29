package com.springbootgamingclubproject.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.springbootgamingclubproject.entity.Game;

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
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	@Column(nullable = false, precision = 5, scale = 2)
	private BigDecimal playTimeHrs;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal cost;

	@Column(nullable = false)
	private LocalDateTime transactionDate;

	public Transaction() {
	}

	public Transaction(Member member, Game game, BigDecimal playTimeHrs, BigDecimal cost,
			LocalDateTime transactionDate) {
		this.member = member;
		this.game = game;
		this.playTimeHrs = playTimeHrs;
		this.cost = cost;
		this.transactionDate = transactionDate;
	}

	// Getters and Setters
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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
