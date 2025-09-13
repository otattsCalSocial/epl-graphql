package com.otattss.epl_graphql.repo;

import com.otattss.epl_graphql.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
}
