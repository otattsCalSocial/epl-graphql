package com.otattss.epl_graphql.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SeasonProperties {
    @Value("${epl.currentSeason:2025-26}")
    private String currentSeason;

    public String current() {
        return currentSeason;
    }
}
