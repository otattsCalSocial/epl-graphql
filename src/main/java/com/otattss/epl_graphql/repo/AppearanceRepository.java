package com.otattss.epl_graphql.repo;

import com.otattss.epl_graphql.domain.Appearance;
import com.otattss.epl_graphql.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppearanceRepository extends JpaRepository<Appearance, Long> {

    @Query("""
           select a from Appearance a
           where a.fixture.season = :season
             and lower(a.player.name) like lower(concat('%', :q, '%'))
           order by a.player.name asc
           """)
    List<Appearance> searchAppearancesBySeasonAndPlayerName(String season, String q);

    @Query("""
           select distinct a.player from Appearance a
           where a.fixture.season = :season
           order by a.player.name asc
           """)
    List<Player> playersWhoPlayedInSeason(String season);

    interface PlayerSeasonAggregate {
        long getGamesPlayed();
        long getMinutesPlayed();
        long getGoals();
    }

    @Query("""
           select count(a) as gamesPlayed,
                  coalesce(sum(a.minutes),0) as minutesPlayed,
                  coalesce(sum(a.goals),0) as goals
           from Appearance a
           where a.player.id = :playerId and a.fixture.season = :season
           """)
    PlayerSeasonAggregate aggregateForPlayerAndSeason(Long playerId, String season);
}
