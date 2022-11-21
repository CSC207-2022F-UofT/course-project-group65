package useCases.teamCreation;
import java.util.ArrayList;
import java.util.List;

import entities.*;



public class teamCreationUC implements teamCreationIB {
    private final teamCreationOB outputBoundary;
    private final String creatorName;

    private final int bracketID;
    private final AccountRepo accounts;
    private final BracketRepo brackets;

    public teamCreationUC(teamCreationOB outputBoundary, String creatorName, int bracketID, AccountRepo accounts,
                          BracketRepo brackets){
        this.outputBoundary = outputBoundary;
        this.creatorName = creatorName;
        this.bracketID = bracketID;
        this.accounts = accounts;
        this.brackets = brackets;
    }

    public boolean checkPlayer(){
        Bracket curBracket = brackets.getBracket(bracketID);
        User creator = accounts.getUser(creatorName);
        return creator.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }

    //used to check if a team with the same teamName exists in the bracket
    public boolean checkTeamNameExists(teamCreationID userInput){
        String teamName = userInput.getTeamName();
        Bracket curBracket = brackets.getBracket(bracketID);
        List<Team> teams = curBracket.getTeams();
        for(Team team: teams){
            if(team.getTeamName().equals(teamName)){
                return false;
            }
        }
        return true;
    }

    //find a blank team in the bracket, returns null if the bracket is full
    public Team findBlankTeam(){
        Bracket curBracket = brackets.getBracket(bracketID);
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
        User creator = accounts.getUser(creatorName);
        Team newTeam = findBlankTeam();
        newTeam.setTeamName(teamName);
        newTeam.addTeamMember(creator);
        return "Your team has been successfully created.";

    }

    @Override
    public teamCreationOD createNewTeam(teamCreationID userInput) {
        if (checkTeamNameExists(userInput)) {
            return outputBoundary.prepareFailView("Team already exists.");
        } else if (findBlankTeam() == null) {
            return outputBoundary.prepareFailView("The bracket is full, please join an existing team.");
        } else if (!checkPlayer()){
            return outputBoundary.prepareFailView("Only players can create a new team.");
        }

        String success = createTeam(userInput);
        Bracket curBracket = brackets.getBracket(bracketID);
        ArrayList<String> teams = new ArrayList<>();
        ArrayList<ArrayList<String>> teamMembers = new ArrayList<>();
        for (Team team : curBracket.getTeams()) {
            teams.add(team.getTeamName());
            ArrayList<String> members = new ArrayList<>();
            for(User member : team.getTeamMembers()){
                members.add(member.getUsername());
            }
            teamMembers.add(members);
        }


        teamCreationOD outputData = new teamCreationOD(teamMembers, teams, success, creatorName,
                bracketID, accounts, brackets);
        return outputBoundary.prepareSuccessView(outputData);
    }
}
