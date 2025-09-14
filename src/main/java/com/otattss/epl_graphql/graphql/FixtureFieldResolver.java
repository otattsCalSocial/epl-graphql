package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.domain.Fixture;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FixtureFieldResolver {

    @SchemaMapping(typeName = "Fixture", field = "resultFor")
    public String resultFor(Fixture f, @Argument Long teamId) {
        boolean isHome = f.getHome().getId().equals(teamId);
        int gf = isHome ? f.getHomeGoals() : f.getAwayGoals();
        int ga = isHome ? f.getAwayGoals() : f.getHomeGoals();

        if (!"FINISHED".equalsIgnoreCase(f.getStatus())) return "N/A";
        if (gf > ga) return "W";
        if (gf == ga) return "D";
        return "L";
    }
}
