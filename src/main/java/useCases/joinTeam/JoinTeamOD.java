package useCases.joinTeam;

import java.util.ArrayList;

public class JoinTeamOD {
    private String success;
    private String username;

    private ArrayList<String> membersNames;

    public JoinTeamOD(String success, String username, ArrayList<String> membersNames){
        this.success = success;
        this.username = username;
        this.membersNames = membersNames;
    }

    public String getSuccess(){
        return this.success;
    }

    public void setSuccess(String success){
        this.success = success;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public ArrayList<String> getMembersNames() {return this.membersNames;}

    public void setMembersNames(ArrayList<String> membersNames) {this.membersNames = membersNames;}

}
