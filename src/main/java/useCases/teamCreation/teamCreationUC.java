package useCases.teamCreation;
import java.util.ArrayList;
import java.util.List;

import entities.*;



public class teamCreationUC implements teamCreationIB {
    final teamCreationOB outputBoundary;

    public teamCreationUC(teamCreationOB outputBoundary){
        this.outputBoundary = outputBoundary;
    }

    public boolean checkPlayer(teamCreationID userInput){
        User creator = userInput.getUser();
        Bracket curBracket = userInput.getBracket();
        return creator.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }

    //used to check if a team with the same teamName exists in the bracket
    public boolean checkTeamNameExists(teamCreationID userInput){
        String teamName = userInput.getTeamName();
        Bracket curBracket = userInput.getBracket();
        List<Team> teams = curBracket.getTeams();
        for(Team team: teams){
            if(team.getTeamName().equals(teamName)){
                return false;
            }
        }
        return true;
    }

    //find a blank team in the bracket, returns null if the bracket is full
    public Team findBlankTeam(teamCreationID userInput){
        Bracket curBracket = userInput.getBracket();
        List<Team> teams = curBracket.getTeams();
        for(Team team: teams){
            if(team.getTeamName().substring(0, 9).equals("BlankTeam")){
                return team;
            }
        }
        return null;
    }

    // creates the team and returns a string that indicates whether the team has been successfully created
    public String createTeam(teamCreationID userInput){
        String teamName = userInput.getTeamName();
        int teamSize = userInput.getTeamSize();
        User creator = userInput.getUser();
        Team newTeam = findBlankTeam(userInput);
        newTeam.setTeamName(teamName);
        newTeam.setTeamSize(teamSize);
        newTeam.addTeamMember(creator);
        return "Your team has been successfully created.";

    }

    @Override
    public teamCreationOD createNewTeam(teamCreationID userInput) {
        if (checkTeamNameExists(userInput)) {
            return outputBoundary.prepareFailView("Team already exists.");
        } else if (findBlankTeam(userInput) == null) {
            return outputBoundary.prepareFailView("The bracket is full, please join an existing team.");
        } else if (!checkPlayer(userInput)){
            return outputBoundary.prepareFailView("Only players can create a new team.");
        }

        String success = createTeam(userInput);

        teamCreationOD outputData = new teamCreationOD(userInput.getBracket(), success);
        return outputBoundary.prepareSuccessView(outputData);
    }
}
