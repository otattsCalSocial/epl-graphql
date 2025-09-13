package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.domain.Player;
import com.otattss.epl_graphql.repo.PlayerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerResolver {

    private final PlayerRepository playerRepository;

    public PlayerResolver(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @QueryMapping("players")
    public List<Player> players() {
        return playerRepository.findAll();
    }

    @QueryMapping("playersByTeam")
    public List<Player> playersByTeam(@Argument Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }
}
