package useCases.teamCreation;
import entities .*;

import java.util.ArrayList;

public class teamCreationOD {
    private String success;
    private ArrayList<ArrayList<String>> teamMembers;
    private ArrayList<String> teams;

    public teamCreationOD(ArrayList<ArrayList<String>> teamMembers, ArrayList<String> teams, String success){
        this.success = success;
        this.teamMembers = teamMembers;
        this.teams = teams;
    }

    public String getSuccess(){
        return this.success;
    }

    public void setSuccess(String success){
        this.success = success;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams){
        this.teams = teams;
    }

    public ArrayList<ArrayList<String>> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(ArrayList<ArrayList<String>> teamMembers){
        this.teamMembers = teamMembers;
    }
}
