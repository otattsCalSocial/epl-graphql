package com.otattss.epl_graphql.repo;

import com.otattss.epl_graphql.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> { }
