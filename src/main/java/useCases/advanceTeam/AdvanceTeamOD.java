package useCases.advanceTeam;

import entities.Bracket;
import entities.Game;
import entities.Team;

public class AdvanceTeamOD {

    private int advancedGame;
    private String winningTeam;

    public AdvanceTeamOD(int advancedGame, String winningTeam){
        this.advancedGame = advancedGame;
        this.winningTeam = winningTeam;
    }


    public int getAdvancedGame(){
        return this.advancedGame;
    }

    public String getWinningTeam(){
        return this.winningTeam;
    }

    public void setAdvancedGame(int advancedGame){
        this.advancedGame = advancedGame;
    }

    public void setWinningTeam(String winningTeam){
        this.winningTeam = winningTeam;
    }

}
