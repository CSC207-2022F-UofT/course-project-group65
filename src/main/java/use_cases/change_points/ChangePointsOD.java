package use_cases.change_points;

import java.util.ArrayList;

public class ChangePointsOD {

//    private int newPoints;
//    private int gameID;
//    private String changedTeamName;
//    private String otherTeamName;
//    private int otherTeamPoints;
    private ArrayList<String> teams;
    private ArrayList<Integer> points;

    public ChangePointsOD(ArrayList<String> teams, ArrayList<Integer> points) {
        this.points = points;
        this.teams = teams;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public void setPoints(ArrayList<Integer> points) {
        this.points = points;
    }

//    public int getGameID() {
//        return this.gameID;
//    }
//
//    public String getChangedTeamName() {
//        return this.changedTeamName;
//    }
//
//    public String getOtherTeamName() {
//        return this.otherTeamName;
//    }
//
//    public int getNewPoints() {
//        return this.newPoints;
//    }
//
//    public int getOtherTeamPoints() {
//        return this.otherTeamPoints;
//    }



//    public ChangePointsOD(Game game, Team team, int newPoints, Bracket bracket){
//        this.game = game;
//        this.team = team;
//        this.newPoints = newPoints;
//        this.bracket = bracket;
//    }

//    public Game getUpdatedGameCP(){
//        return this.game;
//    }
//
//    public Team getUpdatedTeamCP(){
//        return this.team;
//    }
//
//    public int getUpdatedPointsCP(){
//        return this.newPoints;
//    }
//
//    public Bracket getUpdatedBracketCP(){
//        return this.bracket;
//    }
//
//    public void setUpdatedGameCP(Game game){
//        this.game = game;
//    }
//
//    public void setUpdatedTeamCP(Team team){
//        this.team = team;
//    }
//
//    public void setUpdatedPointsCP(int newPoints){
//        this.newPoints = newPoints;
//    }
//
//    public void setUpdatedBracketCP(Bracket bracket){
//        this.bracket = bracket;
//    }

}
