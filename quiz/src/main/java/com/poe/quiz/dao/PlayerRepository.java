package com.poe.quiz.dao;

import com.poe.quiz.business.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    public List<Player> findAllByOrderByScoreDesc();
}
