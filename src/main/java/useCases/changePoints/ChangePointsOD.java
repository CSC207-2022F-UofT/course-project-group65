package useCases.changePoints;
import entities.*;

public class ChangePointsOD {

    private Game game;
    private Team team;
    private int newPoints;
    private Bracket bracket;

    public ChangePointsOD(Game game, Team team, int newPoints, Bracket bracket){
        this.game = game;
        this.team = team;
        this.newPoints = newPoints;
        this.bracket = bracket;
    }

    public Game getUpdatedGameCP(){
        return this.game;
    }

    public Team getUpdatedTeamCP(){
        return this.team;
    }

    public int getUpdatedPointsCP(){
        return this.newPoints;
    }

    public Bracket getUpdatedBracketCP(){
        return this.bracket;
    }

    public void setUpdatedGameCP(Game game){
        this.game = game;
    }

    public void setUpdatedTeamCP(Team team){
        this.team = team;
    }

    public void setUpdatedPointsCP(int newPoints){
        this.newPoints = newPoints;
    }

    public void setUpdatedBracketCP(Bracket bracket){
        this.bracket = bracket;
    }

}
