insert into team (id, name, short_name) values
 (1, 'Arsenal', 'ARS'),
 (2, 'Manchester City', 'MCI'),
 (3, 'Liverpool', 'LIV');

insert into player (id, name, position, goals, team_id) values
 (1, 'Bukayo Saka', 'RW', 4, 1),
 (2, 'Martin Ødegaard', 'AM', 3, 1),
 (3, 'Erling Haaland', 'ST', 6, 2),
 (4, 'Phil Foden', 'AM', 3, 2),
 (5, 'Mohamed Salah', 'RW', 5, 3);

-- Fixtures (note: date uses yyyy-MM-dd only)
-- 1) Arsenal vs Man City ends 2-2 (matches the "draw" comment)
-- 2) Arsenal vs Liverpool ends 2-0 (matches the appearances below)
-- 3) Liverpool vs Arsenal scheduled (no appearances yet)
insert into fixture (id, date, season, status, home_id, away_id, home_goals, away_goals) values
 (1, '2025-08-01', '2025-26', 'FINISHED', 1, 2, 2, 2),
 (2, '2025-08-08', '2025-26', 'FINISHED', 1, 3, 2, 0),
 (3, '2025-08-15', '2025-26', 'SCHEDULED', 3, 1, 0, 0);

-- Appearances (players who actually played those fixtures)

-- Fixture 1: Arsenal vs Man City (2-2 draw)
insert into appearance (id, player_id, fixture_id, minutes, goals) values
  (1001, 1, 1, 90, 1),   -- Saka scored
  (1002, 2, 1, 85, 0);   -- Ødegaard played

-- Fixture 2: Arsenal vs Liverpool (2-0)
insert into appearance (id, player_id, fixture_id, minutes, goals) values
  (1003, 1, 2, 90, 1),   -- Saka
  (1004, 2, 2, 80, 1),   -- Ødegaard
  (1005, 5, 2, 90, 0);   -- Salah (played, no goals)
