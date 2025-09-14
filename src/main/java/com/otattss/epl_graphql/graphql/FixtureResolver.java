package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.config.SeasonProperties;
import com.otattss.epl_graphql.domain.Fixture;
import com.otattss.epl_graphql.repo.FixtureRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FixtureResolver {

    private final FixtureRepository fixtureRepository;
    private final SeasonProperties seasonProperties;

    public FixtureResolver(FixtureRepository fixtureRepository,
                           SeasonProperties seasonProperties) {
        this.fixtureRepository = fixtureRepository;
        this.seasonProperties = seasonProperties;
    }

    @QueryMapping("matches")
    public List<Fixture> matches(@Argument String season) {
        String s = (season == null || season.isBlank()) ? seasonProperties.current() : season;
        return fixtureRepository.findBySeasonOrderByDateAsc(s);
    }

    @QueryMapping("teamMatches")
    public List<Fixture> teamMatches(@Argument Long teamId, @Argument String season) {
        String s = (season == null || season.isBlank()) ? seasonProperties.current() : season;
        return fixtureRepository.findByTeamAndSeason(teamId, s);
    }
}
