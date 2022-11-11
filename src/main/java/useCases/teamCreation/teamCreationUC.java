package useCases.teamCreation;
import java.util.ArrayList;
import java.util.List;

import entities.*;



public class teamCreationUC {


    public boolean checkPlayer(DefaultUser creator, DefaultBracket curBracket){
        return creator.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }

    //used to check if a team with the same teamName exists in the bracket
    public boolean checkTeamNameExists(String teamName, DefaultBracket curBracket){
        ArrayList<Team> teams = curBracket.getTeams();
        for(Team team: teams){
            if(team.getTeamName().equals(teamName)){
                return false;
            }
        }
        return true;
    }

    //find a blank team in the bracket, returns null if the bracket is full
    public Team findBlankTeam(DefaultBracket curBracket){
        ArrayList<Team> teams = curBracket.getTeams();
        for(Team team: teams){
            if(team.getTeamName().substring(0, 9).equals("BlankTeam")){
                return team;
            }
        }
        return null;
    }

    // creates the team and returns a string that indicates whether the team has been successfully created
    public String createTeam(String teamName, int teamSize, DefaultUser creator, DefaultBracket curBracket){
        boolean userIsPlayer = checkPlayer(creator, curBracket);
        boolean validTeamName = !checkTeamNameExists(teamName, curBracket);
        boolean spaceAvailable = findBlankTeam(curBracket) != null;
        if(userIsPlayer && validTeamName && spaceAvailable){
            Team newTeam = findBlankTeam(curBracket);
            newTeam.setTeamName(teamName);
            newTeam.setTeamSize(teamSize);
            newTeam.addTeamMember(creator);
            return "Your team has been successfully created.";
        } else if(!spaceAvailable){
            return "This bracket is full and you can no longer create a new team.";
        }else if (!validTeamName){
            return "This team name has been used by another team, please enter a new team name.";
        } else {
            return "Only players can create a team.";
        }
    }

}
