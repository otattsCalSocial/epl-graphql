# EPL GraphQL API

This project is a Java Spring Boot application that provides a GraphQL API for accessing English Premier League (EPL) data, including teams, players, fixtures, and statistics.

## Features
- GraphQL API endpoints for EPL data
- Query teams, players, fixtures, and player statistics
- Custom resolvers for advanced queries
- Data loaded from SQL scripts

## Project Structure
- `src/main/java/com/otattss/epl_graphql/` - Main Java source code
  - `domain/` - Entity classes (Player, Team, Fixture, etc.)
  - `graphql/` - GraphQL resolvers
  - `repo/` - Spring Data repositories
  - `config/` - Configuration classes
- `src/main/resources/`
  - `graphql/` - GraphQL schema files
  - `application.properties` - Application configuration
  - `data.sql` - Initial data
- `src/test/java/` - Unit and integration tests

## Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.6+

### Build and Run

1. **Clone the repository:**
   ```sh
git clone https://github.com/otattsCalSocial/epl-graphql.git
cd epl-graphql
```
2. **Build the project:**
   ```sh
./mvnw clean install
```
3. **Run the application:**
   ```sh
./mvnw spring-boot:run
```
   The server will start on `http://localhost:8080`.

### Accessing GraphQL Playground
Visit `http://localhost:8080/graphiql` or `http://localhost:8080/graphql` to interact with the API.

## Example GraphQL Queries

### 1. Get All Teams with Players
```graphql
query {
  teams {
    id
    name
    players {
      id
      name
    }
  }
}
```
**Explanation:**
Fetches all teams and, for each team, lists its players with their IDs and names.

### 2. Get Player by ID
```graphql
query {
  player(id: 1) {
    id
    name
    position
    team {
      name
    }
  }
}
```
**Explanation:**
Fetches a single player by their ID, including their position and the name of their team.

### 3. Search Players by Name
```graphql
query {
  searchPlayers(name: "Harry") {
    id
    name
    team {
      name
    }
  }
}
```
**Explanation:**
Searches for players whose names contain "Harry" and returns their IDs, names, and team names.

### 4. Get Fixtures for a Team
```graphql
query {
  team(id: 1) {
    name
    fixtures {
      id
      date
      opponent {
        name
      }
      result
    }
  }
}
```
**Explanation:**
Fetches a team by ID and lists all its fixtures, including the date, opponent name, and result.

### 5. Get Player Statistics
```graphql
query {
  player(id: 1) {
    name
    appearances {
      season
      goals
      assists
      minutesPlayed
    }
  }
}
```
**Explanation:**
Fetches a player by ID and lists their appearance statistics for each season, including goals, assists, and minutes played.

## Configuration
- Edit `src/main/resources/application.properties` to change database or server settings.

## Database
- Uses H2 in-memory database by default
- Schema and data are loaded from `data.sql` and migration scripts

## Testing
Run tests with:
```sh
./mvnw test
```

## License
MIT License

## Author
[otattsCalSocial](https://github.com/otattsCalSocial)
