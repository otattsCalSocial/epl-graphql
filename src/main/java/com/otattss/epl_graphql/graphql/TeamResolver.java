package com.otattss.epl_graphql.graphql;

import com.otattss.epl_graphql.domain.Team;
import com.otattss.epl_graphql.repo.TeamRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TeamResolver {

    private final TeamRepository teamRepository;

    public TeamResolver(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @QueryMapping
    public List<Team> teams() {
        return teamRepository.findAll();
    }

    @QueryMapping
    public Team team(@Argument Long id){
        return teamRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Team not found: " + id));
    }
}
