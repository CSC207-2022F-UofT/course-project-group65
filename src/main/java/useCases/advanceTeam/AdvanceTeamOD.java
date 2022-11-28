package useCases.advanceTeam;

import entities.Bracket;
import entities.Game;
import entities.Team;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AdvanceTeamOD {

    private int advancedGameID;
//    private ArrayList<String> teams;
    private LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    private LinkedHashMap<Integer, ArrayList<Integer>> gameToScores;

    public AdvanceTeamOD(int advancedGame, LinkedHashMap<Integer, ArrayList<String>> gameToTeams,
                         LinkedHashMap<Integer, ArrayList<Integer>> gameToScores) {
        this.advancedGameID = advancedGame;
        this.gameToTeams = gameToTeams;
        this.gameToScores = gameToScores;
    }


    public int getAdvancedGame(){
        return this.advancedGameID;
    }

    public void setAdvancedGame(int advancedGame){
        this.advancedGameID = advancedGame;
    }

    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams(){
        return this.gameToTeams;
    }

    public void setGameToTeams(LinkedHashMap<Integer, ArrayList<String>> gameToTeams){
        this.gameToTeams = gameToTeams;
    }

    public LinkedHashMap<Integer, ArrayList<Integer>> getGameToScores(){
        return this.gameToScores;
    }

    public void setGameToScores(LinkedHashMap<Integer, ArrayList<Integer>> gameToScores){
        this.gameToScores = gameToScores;
    }

//    public ArrayList<String> getTeams(){
//        return this.teams;
//    }
//
//    public void setTeams(ArrayList<String> teams){
//        this.teams = teams;
//    }

}
