package useCases.advanceTeam;
import entities.*;

public class AdvanceTeamOD {

    private Bracket bracket;
    private Game advancedGame;
    private Team winningTeam;

    public AdvanceTeamOD(Bracket bracket, Game advancedGame, Team winningTeam){
        this.bracket = bracket;
        this.advancedGame = advancedGame;
        this.winningTeam = winningTeam;
    }

    public Bracket getUpdatedBracket(){
        return this.bracket;
    }

    public Game getAdvancedGame(){
        return this.advancedGame;
    }

    public Team getWinningTeam(){
        return this.winningTeam;
    }

    public void setUpdatedBracket(Bracket bracket){
        this.bracket = bracket;
    }

    public void setAdvancedGame(Game advancedGame){
        this.advancedGame = advancedGame;
    }

    public void setWinningTeam(Team winningTeam){
        this.winningTeam = winningTeam;
    }

}
