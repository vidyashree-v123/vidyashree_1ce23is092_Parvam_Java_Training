package com.springbootgamingclubproject.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Column(nullable = false)
    private LocalDate joinDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    public enum Status {
        ACTIVE, INACTIVE
    }
    // Relationships
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recharge> recharges;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public Member() {
    }

    public Member(String name, String email, String phone, LocalDate joinDate,
                  BigDecimal balance, Status status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.joinDate = joinDate;
        this.balance = balance;
        this. status = status;
    }

    // Getters and Setters
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Recharge> getRecharges() {
        return recharges;
    }

    public void setRecharges(List<Recharge> recharges) {
        this.recharges = recharges;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
