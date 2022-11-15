package useCases.joinTeam;

import entities.User;

public class joinTeamID {
    private String teamName;
    private User user;

    public joinTeamID(User user, String teamName){
        this.user = user;
        this.teamName = teamName;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public String getTeamName(){
        return this.teamName;
    }
}
