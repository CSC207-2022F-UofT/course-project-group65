package entities;

import java.io.Serializable;
import java.util.ArrayList;

/** This class represents the default team*/
public class DefaultTeam implements Team, Serializable {
    /** A string for team name */
    private String teamName;
    /** An int for team size */
    private int teamSize;
    /** An arraylist for team's members */
    private final ArrayList<User> teamMembers;
    /** The constructor for default team */
    public DefaultTeam(){
        teamName = "";
        teamSize = 0;
        teamMembers = new ArrayList<>();
    }
    /** The getter and setter methods for all the variables */
    public String getTeamName() {return teamName;}

    public void setTeamName(String teamName) {this.teamName = teamName;}

    public int getTeamSize() {return teamSize;}

    public void setTeamSize(int teamSize) {this.teamSize = teamSize;}
    public ArrayList<User> getTeamMembers() {return teamMembers;}

    public void addTeamMember(User teamMember) {teamMembers.add(teamMember);}
}
