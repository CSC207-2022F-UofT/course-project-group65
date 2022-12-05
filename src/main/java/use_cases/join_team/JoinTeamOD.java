package use_cases.join_team;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class represents the output data for the JoinTeam use case.
 * This data is used in updating/creating the view after the bracket has been
 * successfully created.
 */

public class JoinTeamOD {
    /**
     * The success massage for the user
     */
    private final String success;
    /**
     * The list of membersNames in the team
     */
    private final ArrayList<String> membersNames;
    /**
     * Used to update the corresponding team members list in the bracketView screen
     */
    private final LinkedHashMap<String, ArrayList<String>> teamToPlayers;

    /**
     * Creates a new teamCreationOD object.
     *
     * @param success       The success massage for the user
     * @param membersNames  The list of membersNames in the team
     * @param teamToPlayers The hashmap that contains all the teams mapped to players
     */
    public JoinTeamOD(String success, ArrayList<String> membersNames, LinkedHashMap<String, ArrayList<String>> teamToPlayers) {
        this.success = success;
        this.membersNames = membersNames;
        this.teamToPlayers = teamToPlayers;
    }

    /**
     * The getter methods
     */
    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers() {
        return teamToPlayers;
    }

    public String getSuccess() {
        return this.success;
    }

    public ArrayList<String> getMembersNames() {
        return this.membersNames;
    }

}
