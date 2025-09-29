package com.springbootgamingclubproject.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootgamingclubproject.entity.DailyCollection;

public interface DailyCollectionRepository extends JpaRepository<DailyCollection, Long> {
    Optional<DailyCollection> findByCollectionDate(LocalDate collectionDate);
}