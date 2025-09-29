package com.springbootgamingclubproject.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyCollectionResponse {
	private Long collectionId;
	private LocalDate collectionDate;
	private BigDecimal totalRecharges;
	private BigDecimal totalSpent;
	private BigDecimal netCollection;

	// Getters & Setters
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
