package useCases.declareWinner;
import entities.*;

public class DeclareWinnerOD {
    private Bracket bracket;
    private Game finishedGame;
    private Team winningTeam;

    public DeclareWinnerOD(Game finishedGame, Team winningTeam, Bracket bracket){
        this.bracket = bracket;
        this.finishedGame = finishedGame;
        this.winningTeam = winningTeam;
    }

    public Bracket getUpdatedBracket(){
        return this.bracket;
    }

    public Game getFinishedGame(){
        return this.finishedGame;
    }

    public Team getWinningTeam(){
        return this.winningTeam;
    }

    public void setUpdatedBracket(Bracket bracket){
        this.bracket = bracket;
    }

    public void setFinishedGame(Game finishedGame){
        this.finishedGame = finishedGame;
    }

    public void setWinningTeam(Team winningTeam){
        this.winningTeam = winningTeam;
    }
}
