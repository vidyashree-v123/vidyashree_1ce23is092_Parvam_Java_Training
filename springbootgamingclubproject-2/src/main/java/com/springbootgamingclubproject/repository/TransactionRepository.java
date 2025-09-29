package com.springbootgamingclubproject.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootgamingclubproject.entity.Game;
import com.springbootgamingclubproject.entity.Member;
import com.springbootgamingclubproject.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(SUM(t.cost), 0) FROM Transaction t WHERE t.transactionDate >= :start AND t.transactionDate < :end")
    BigDecimal sumCostBetween(LocalDateTime start, LocalDateTime end);
    List<Transaction> findByMember(Member member);
    List<Transaction> findByGame(Game game);
}
