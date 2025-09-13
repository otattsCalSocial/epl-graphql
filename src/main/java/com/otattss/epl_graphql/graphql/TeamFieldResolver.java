package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.domain.Player;
import com.otattss.epl_graphql.domain.Team;
import com.otattss.epl_graphql.repo.PlayerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;

@Controller
public class TeamFieldResolver {

    private final PlayerRepository playerRepository;

    public TeamFieldResolver(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @SchemaMapping(typeName = "Team", field = "players")
    public List<Player> players(Team team,
                                @Argument Integer limit,
                                @Argument String sortBy) {
        var list = playerRepository.findByTeamId(team.getId());
        if (sortBy != null && sortBy.equalsIgnoreCase("goals")) {
            list.sort(Comparator.comparingInt(Player::getGoals).reversed());
        }
        if (limit != null && limit > 0 && limit < list.size()) {
            return list.subList(0, limit);
        }
        return list;
    }
}
