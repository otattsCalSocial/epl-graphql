package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.config.SeasonProperties;
import com.otattss.epl_graphql.domain.Player;
import com.otattss.epl_graphql.graphql.dto.PlayerSeasonStatsDTO;
import com.otattss.epl_graphql.repo.AppearanceRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerStatsResolver {

    private final AppearanceRepository appearanceRepository;
    private final SeasonProperties seasonProperties;

    public PlayerStatsResolver(AppearanceRepository appearanceRepository,
                               SeasonProperties seasonProperties) {
        this.appearanceRepository = appearanceRepository;
        this.seasonProperties = seasonProperties;
    }

    @SchemaMapping(typeName = "Player", field = "seasonStats")
    public PlayerSeasonStatsDTO seasonStats(Player player, @Argument String season) {
        String s = (season == null || season.isBlank()) ? seasonProperties.current() : season;
        var agg = appearanceRepository.aggregateForPlayerAndSeason(player.getId(), s);
        int games = agg == null ? 0 : Math.toIntExact(agg.getGamesPlayed());
        int mins  = agg == null ? 0 : Math.toIntExact(agg.getMinutesPlayed());
        int goals = agg == null ? 0 : Math.toIntExact(agg.getGoals());
        return new PlayerSeasonStatsDTO(games, mins, goals);
    }
}
