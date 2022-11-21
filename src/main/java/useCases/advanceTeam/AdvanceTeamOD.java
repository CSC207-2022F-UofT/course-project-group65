package useCases.advanceTeam;

import entities.Bracket;
import entities.Game;
import entities.Team;

import java.util.ArrayList;

public class AdvanceTeamOD {

    private int advancedGame;
    private ArrayList<String> teams;

    public AdvanceTeamOD(int advancedGame, ArrayList<String> teams) {
        this.advancedGame = advancedGame;
        this.teams = teams;
    }


    public int getAdvancedGame(){
        return this.advancedGame;
    }

    public void setAdvancedGame(int advancedGame){
        this.advancedGame = advancedGame;
    }
    public ArrayList<String> getTeams(){
        return this.teams;
    }

    public void setTeams(ArrayList<String> teams){
        this.teams = teams;
    }

}
