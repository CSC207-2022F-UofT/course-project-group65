package useCases.teamCreation;
import entities .*;

import java.util.ArrayList;

public class teamCreationOD {
    private String success;
    private String username;
    private ArrayList<String> teams;
    private Bracket updatedBracket;

    public teamCreationOD(String username, Bracket updatedBracket, ArrayList<String> teams, String success){
        this.success = success;
        this.updatedBracket = updatedBracket;
        this.username = username;
        this.teams = teams;
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

    public Bracket getUpdatedBracket(){
        return this.updatedBracket;
    }

    public void setUpdatedBracket(Bracket updatedBracket){
        this.updatedBracket = updatedBracket;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams){
        this.teams = teams;
    }
}
