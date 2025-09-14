package com.otattss.epl_graphql.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerSeasonStatsDTO {
    private int gamesPlayed;
    private int minutesPlayed;
    private int goals;
}
