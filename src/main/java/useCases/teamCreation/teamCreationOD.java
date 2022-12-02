package useCases.teamCreation;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class represents the response model for the CreateBracket use case.
 * This data is used in updating/creating the view after the bracket has been
 * successfully created.
 */
public class teamCreationOD {
    /** The name of the user created the new team */
    private final String username;
    /** The name of the new team */
    private final String newTeam;
    /** The name of the old team */
    private final String oldTeam;
    /** The hasmap that maps game to teams*/
    private final LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    /** The hasmap that maps team to players*/
    private final LinkedHashMap<String, ArrayList<String>> teamToPlayers;
    /**
     * Creates a new teamCreationOD object.
     * @param username The name of the user who created the team
     * @param newTeam The updated team name
     * @param oldTeam The old team name
     * @param gameToTeams The hashmap that contains all the games mapped to teams
     * @param teamToPlayers The hashmap that contains all the teams mapped to players
     */
    public teamCreationOD(String username, String newTeam, String oldTeam,
                          LinkedHashMap<Integer, ArrayList<String>> gameToTeams,
                          LinkedHashMap<String, ArrayList<String>> teamToPlayers){
        this.username = username;
        this.newTeam = newTeam;
        this.oldTeam = oldTeam;
        this.gameToTeams =  gameToTeams;
        this.teamToPlayers = teamToPlayers;
    }
    /** getter methods **/
    public String getUsername(){
        return username;
    }

    public String getNewTeam() {
        return newTeam;
    }

    public String getOldTeam() {
        return oldTeam;
    }

    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams(){
        return gameToTeams;
    }

    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers(){
        return teamToPlayers;
    }
}
