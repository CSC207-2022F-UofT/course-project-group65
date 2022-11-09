package entities;

import java.util.ArrayList;
import java.util.List;
public class DefaultTeam implements Team{
    private String teamName;
    private int teamSize;
    private ArrayList<User> teamMembers;

    public DefaultTeam(){
        teamName = "";
        teamSize = 0;
        teamMembers = new ArrayList<User>();
    }
    public String getTeamName() {return teamName;}

    public void setTeamName(String teamName) {this.teamName = teamName;}

    public int getTeamSize() {return teamSize;}

    public void setTeamSize(int teamSize) {this.teamSize = teamSize;}
    public ArrayList<User> getTeamMembers() {return teamMembers;}

    public void addTeamMember(User teamMember) {teamMembers.add(teamMember);}
}
