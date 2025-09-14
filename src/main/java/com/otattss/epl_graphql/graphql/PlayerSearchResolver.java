package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.config.SeasonProperties;
import com.otattss.epl_graphql.domain.Appearance;
import com.otattss.epl_graphql.domain.Player;
import com.otattss.epl_graphql.repo.AppearanceRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerSearchResolver {

    private final AppearanceRepository appearanceRepository;
    private final SeasonProperties seasonProperties;

    public PlayerSearchResolver(AppearanceRepository appearanceRepository,
                                SeasonProperties seasonProperties) {
        this.appearanceRepository = appearanceRepository;
        this.seasonProperties = seasonProperties;
    }

    @QueryMapping("playersWhoPlayed")
    public List<Player> playersWhoPlayed(@Argument String season, @Argument String q) {
        String s = (season == null || season.isBlank()) ? seasonProperties.current() : season;
        if (q == null || q.isBlank()) {
            return appearanceRepository.playersWhoPlayedInSeason(s);
        }
        // We queried Appearance rows for the season & search string, then map to distinct players.
        return appearanceRepository.searchAppearancesBySeasonAndPlayerName(s, q)
                .stream()
                .map(Appearance::getPlayer)
                .distinct()
                .toList();
    }
}
