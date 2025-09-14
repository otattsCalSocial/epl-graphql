package com.otattss.epl_graphql.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name = "fixture")
public class Fixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(nullable=false)
    private String season;

    @Column(nullable=false)
    private String status;   // "SCHEDULED", "FINISHED"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", nullable = false)
    private Team home;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_id", nullable = false)
    private Team away;

    private int homeGoals;
    private int awayGoals;
}
