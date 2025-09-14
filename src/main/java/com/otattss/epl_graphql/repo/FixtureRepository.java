package com.otattss.epl_graphql.repo;

import com.otattss.epl_graphql.domain.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {

    List<Fixture> findBySeasonOrderByDateAsc(String season);

    @Query("""
           select f from Fixture f
           where f.season = :season and (f.home.id = :teamId or f.away.id = :teamId)
           order by f.date asc
           """)
    List<Fixture> findByTeamAndSeason(Long teamId, String season);
}
