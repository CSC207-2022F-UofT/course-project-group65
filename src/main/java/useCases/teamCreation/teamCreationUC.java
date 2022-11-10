package useCases.teamCreation;
import java.util.ArrayList;
import java.util.List;
//import entities.DefaultUser;
//import entities.DefaultTeam;
//import entities.Team;
//import entities.DefaultBracket;
import entities.*;



public class teamCreationUC {


    public boolean checkPlayer(DefaultUser creator, DefaultBracket curBracket){
        return creator.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }


    //used to check if team with the same teamName exists in the first round
    public boolean checkTeamNameExists(String teamName, DefaultBracket curBracket){
        ArrayList<Team> teams = getAllTeams(curBracket);
        for(Team team: teams){
            if(team.getTeamName().equals(teamName)){
                return false;
            }
        }
        return true;
    }

    // helper method to get all the teams in the game tree
    public ArrayList<Team> getAllTeams(DefaultBracket curBracket){
        ArrayList<Game> games = getAllLeaves(curBracket.getFinalGame());
        ArrayList<Team> teams = new ArrayList<Team>();
        for(Game game: games){
            teams.addAll(game.getTeams());
        }
        return teams;
    }
    // helper method to get all the leaves of the game tree
    public ArrayList<Game> getAllLeaves(Game curGame){
        ArrayList<Game> games =  new ArrayList<Game>();
        if(curGame.getPrevGame1() == null && curGame.getPrevGame2() == null){
            games.add(curGame);
        } else {
            games.addAll(getAllLeaves(curGame.getPrevGame1()));
            games.addAll(getAllLeaves(curGame.getPrevGame2()));
        }
        return games;
    }

    //find a blank team in the game tree, returns null if the bracket is full
    public Team findBlankTeam(DefaultBracket curBracket){
        ArrayList<Team> teams = getAllTeams(curBracket);
        for(Team team: teams){
            if(team.getTeamName().substring(0, 9).equals("BlankTeam")){
                return team;
            }
        }
        return null;
    }

    // creates a new team and add the new team to the bracket based on user input
    public Team createTeam(String teamName, int teamSize, DefaultUser creator, DefaultBracket curBracket){
        boolean userIsPlayer = checkPlayer(creator, curBracket);
        boolean validTeamName = !checkTeamNameExists(teamName, curBracket);
        boolean spaceAvailable = findBlankTeam(curBracket) != null;
        if(userIsPlayer && validTeamName && spaceAvailable){
            Team newTeam = findBlankTeam(curBracket);
            newTeam.setTeamName(teamName);
            newTeam.setTeamSize(teamSize);
            newTeam.addTeamMember(creator);
            curBracket.addTeam(newTeam);
            return newTeam;
        }
        return null;
    }

}
