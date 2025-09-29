package com.springbootgamingclubproject.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "daily_collections")
public class DailyCollection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long collectionId;

	@Column(nullable = false)
	private LocalDate collectionDate;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal totalRecharges;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal totalSpent;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal netCollection;

	public DailyCollection() {
	}

	public DailyCollection(LocalDate collectionDate, BigDecimal totalRecharges, BigDecimal totalSpent,
			BigDecimal netCollection) {
		this.collectionDate = collectionDate;
		this.totalRecharges = totalRecharges;
		this.totalSpent = totalSpent;
		this.netCollection = netCollection;
	}

	// Getters and Setters
	public Long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	public LocalDate getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(LocalDate collectionDate) {
		this.collectionDate = collectionDate;
	}

	public BigDecimal getTotalRecharges() {
		return totalRecharges;
	}

	public void setTotalRecharges(BigDecimal totalRecharges) {
		this.totalRecharges = totalRecharges;
	}

	public BigDecimal getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(BigDecimal totalSpent) {
		this.totalSpent = totalSpent;
	}

	public BigDecimal getNetCollection() {
		return netCollection;
	}

	public void setNetCollection(BigDecimal netCollection) {
		this.netCollection = netCollection;
	}
}
