package com.springbootgamingclubproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootgamingclubproject.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
