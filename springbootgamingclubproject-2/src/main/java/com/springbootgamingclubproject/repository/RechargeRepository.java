package com.springbootgamingclubproject.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootgamingclubproject.entity.Member;
import com.springbootgamingclubproject.entity.Recharge;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {
    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM Recharge r WHERE r.rechargeDate >= :start AND r.rechargeDate < :end")
    BigDecimal sumAmountBetween(LocalDateTime start, LocalDateTime end);

    // Custom finder: get all recharges for a member
    List<Recharge> findByMember(Member member);
}
