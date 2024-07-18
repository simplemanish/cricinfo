# cricinfo

Overview
TBD

The application is divided into multiple microservices, each responsible for a specific functionality:

Player Service: Manages player information.
Team Service: Manages team details.
Match Service: Manages match schedules and scores.
Commentary Service: Provides real-time score updates.
Search Service: Search information for player, match and team based on given criteria.
Below are the details for each microservice with possible endpoints:

Player Service
Responsibility:

It is designed to manage and provide information about players - maintains player statistics.
Scope:

Player information including name, dob, debut etc.
Search players.
Api for third party integration (dream 11 etc)
Implementing data validation and error handling mechanisms.
Scalability and performance, ensuring the service can handle expected traffic during peak hours.
Ensuring data privacy and security for users accessing player’s data.
Endpoints:

    POST /player: Add a player for a given input, name, dob, nationality, role etc.

    GET /player/{id}: Get player details for a given id.

    PUT /player/{id}: Update player information 

    DELETE /player/{id}: Delete player by id.

    GET /player/search?<query string>: Search players by given search string.           // Query String: name, dob, nationality, role
Team Service
Responsibility:

It is designed to manage and provide info about teams - maintains team statistics.
Manages association b/w team and palyers.
Scope:

Team management (create, update, delete, teamInfo)
Player management in team (add, remove)
Team statistics eg wins, losses, draws.
Api for third party integration (dream 11 etc)
Implementing data validation and error handling mechanisms.
Scalability and performance, ensuring the service can handle expected traffic during peak hours.
Ensuring data privacy and security for users accessing player’s data.
Endpoints:

    POST /team: Create team for  given inputs: name, country, captain etc.

    GET /team/{id}: Get team details for a given id.

    PUT /team/{id}: Update team information . 

    DELETE /team/{id}: Delete team by id.

    POST /team/{teamId}/players: Add players to the team.
    
    DELETE /team/{teamId}/players/{playerId}: Remove player from team.
    
    GET /teams/search?<query string>: Search team by given search string.               // Query String: name, dob, nationality, role
Match Service
Responsibility:

It is designed to handle all aspects of match-related data.
Provide match schedules (team, venue, date Time)
Provide real-time update (scores, wickets, overs)
Provide historical match data, results.
Players and teams’ performance (statistics).
Scope:

Live match update
Match details (team, venue, match status)
Search matches for upcoming or past matches.
Real time scoreboard update and display.
Past scoreboard.
Api for third party integration (dream 11 etc)
Implementing data validation and error handling mechanisms.
Scalability and performance, ensuring the service can handle high traffic during peak hour.
Ensuring data privacy and security for users accessing matches.
Endpoints:

    POST /match: Create a match for a given inputs. schedules, teams playing, venue, format, status.

    GET /match/{id}: Get the match details of a given match id.

    PUT /match /{id}: Update match details by given inputs.

    DELETE /match/{id}: Delete match by id.

    GET /match/search?<query string>: Search matches by given search string.           // Query String: match_id, team_name, status, venue, datetime_range, format
    
    POST /scoreboards: Create a scoreboard for a given match id.

    PUT /scoreboards/{scoreboard_id}: Create a scoreboard for a given match id.
    
    GET /scoreboard/{scoreboard_id}: Get scoreboard by id.
    
    GET /scoreboard/{match_id}/player/{player_id}?type=?: Get Player performance in a match         //type = batting, bowling
    
    GET /scoreboard/{match_id}/team/{team_id}: Get Team  performance in a match
        
    POST /scoreboards/inning: Create a scoreboard for a given match id.
    
    PUT /scoreboard/inning/{inning_id}: Update inning with the latest information.  
Commentary Service
Responsibility:

It is designed to manage and provide info about live match progress.
Provide ball by ball commentary.
Provide past matches commentary.
Provide milestones, specific event related commentary eg wicket, sixes, fours etc.
Scope:

Provide ball by ball detailed description.
Provide past matches commentary.
Provide commentary related to specific event eg wicket, sixes, fours
Api for third party integration (dream 11 etc)
Implementing data validation and error handling mechanisms, to ensure data integrity and availability.
Scalability and performance, ensuring the service can handle expected traffic during peak hours.
Endpoints:

    POST /commentary: Create commentary for a given match and ball .
    
    GET /commentary/{match_id}: Get commentary of a  match.

    PUT /commentary: Update commentary for a given match and ball.

    GET /commentary/event/{event}: Get commentary of a  match 

    GET /commentary/{match_id}/{over}: Get commentary of an over in a match.
Search Service
Responsibility:

It is designed to handle indexing and query data efficiently.
Indexing content (player, teams, matches)
Handling search queries
Centralized search needs, scale independently, searching different data source, easier maintenance.
Scope:

Find relevant content of players, teams, and matches.
Implementing data validation and error handling mechanisms.
Scalability and performance, ensuring the service can handle high traffic during peak hour.
Ensuring data privacy and security for users accessing matches.
Endpoints:

    GET  /search/matches?q=<query-string>: Search matches for given query string.

    GET  /search/teams?q=<query-string>: Search teams for given query string.
    
    GET  /search/players?q=<query-string>: Search players for given query string.
