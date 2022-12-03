package use_cases.join_team;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 The output data class for the join team use case.
 */

public class JoinTeamOD {
    private String success;

    private ArrayList<String> membersNames;
    // Used to update the corresponding team members list in the bracketView screen
    private LinkedHashMap<String, ArrayList<String>> teamToPlayers;

    public JoinTeamOD(String success, ArrayList<String> membersNames, LinkedHashMap<String, ArrayList<String>> teamToPlayers){
        this.success = success;
        this.membersNames = membersNames;
        this.teamToPlayers = teamToPlayers;
    }

    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers() {
        return teamToPlayers;
    }

    public String getSuccess(){
        return this.success;
    }

    public void setSuccess(String success){
        this.success = success;
    }

    public ArrayList<String> getMembersNames() {return this.membersNames;}

    public void setMembersNames(ArrayList<String> membersNames) {this.membersNames = membersNames;}

}
