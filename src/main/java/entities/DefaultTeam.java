package entities;

import java.util.List;
public class DefaultTeam implements Team{
    private String teamName;
    private int teamSize;
    private List<User> teamMembers;

    public DefaultTeam(){
        teamName = "";
        teamSize = 0;
        teamMembers = null;
    }
    public String getTeamName() {return teamName;};

    public void setTeamName(String teamName) {this.teamName = teamName;};

    public int getTeamSize() {return teamSize;};

    public void setTeamSize(int teamSize) {this.teamSize = teamSize;};
    public List<User> getTeamMembers() {return teamMembers;};

    public void addTeamMember(User teamMember) {teamMembers.add(teamMember);};
}
