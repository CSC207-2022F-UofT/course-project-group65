package useCases.joinTeam;

import java.util.ArrayList;

public class JoinTeamOD {
    /*
    The output data class for the join team use case.
     */
    private String success;

    private ArrayList<String> membersNames;
    // Used to update the corresponding team members list in the bracketView screen

    public JoinTeamOD(String success, ArrayList<String> membersNames){
        this.success = success;
        this.membersNames = membersNames;
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
