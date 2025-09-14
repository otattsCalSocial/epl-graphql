package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.config.SeasonProperties;
import com.otattss.epl_graphql.domain.Fixture;
import com.otattss.epl_graphql.domain.Team;
import com.otattss.epl_graphql.repo.FixtureRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TeamMatchesResolver {

    private final FixtureRepository fixtureRepository;
    private final SeasonProperties seasonProperties;

    public TeamMatchesResolver(FixtureRepository fixtureRepository,
                               SeasonProperties seasonProperties) {
        this.fixtureRepository = fixtureRepository;
        this.seasonProperties = seasonProperties;
    }

    @SchemaMapping(typeName = "Team", field = "matches")
    public List<Fixture> matches(Team team, @Argument String season) {
        String s = (season == null || season.isBlank()) ? seasonProperties.current() : season;
        return fixtureRepository.findByTeamAndSeason(team.getId(), s);
    }
}
