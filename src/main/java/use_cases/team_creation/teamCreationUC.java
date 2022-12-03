package use_cases.team_creation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import entities.*;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

/**
 * This is the interactor class for teamCreation.
 * This class is responsible for creating a new team in the bracketbased on the user input and
 * saving the updated bracket back to the bracket repository.
 */
public class teamCreationUC implements teamCreationIB {
    /** The response model for teamCreationUC **/
    private final teamCreationOB outputBoundary;
    /** The gateway for teamCreationUC to access database **/
    private final teamCreationGateway gateway;
    /** The username of the creator that creates the team **/
    private final String creatorName;
    /** The ID of the bracket that the team should be created in **/
    private final int bracketID;
    /** The account repository */
    private final AccountRepo accounts;
    /** The bracket repository */
    private final BracketRepo brackets;
    private String oldTeam;

    /**
     * Creates a new teamCreationUC object.
     * @param outputBoundary The output boundary used for updating the view
     * @param gateway The gateway to access the database to store info
     * @param creatorName The username of the user that is creating the team
     * @param informationRecord The information record containing the account and bracket repositories
     */
    public teamCreationUC(teamCreationOB outputBoundary, teamCreationGateway gateway,
                          String creatorName, int bracketID, InformationRecord informationRecord) {
        this.outputBoundary = outputBoundary;
        this.creatorName = creatorName;
        this.bracketID = bracketID;
        this.brackets = informationRecord.getBracketData();
        this.accounts = informationRecord.getAccountData();
        this.gateway = gateway;
    }
    /**
     * Checks if the creator is a player
     * @return a boolean that indicates whether the current user is a player
     */
    public boolean checkPlayer(){
        Bracket curBracket = brackets.getBracket(bracketID);
        User creator = accounts.getUser(creatorName);
        return creator.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }

    /**
     * Checks if the team name already exists
     * @param userInput input data
     * @return a boolean that indicates whether the teamName already exists
     */
    public boolean checkTeamNameExists(teamCreationID userInput){
        String teamName = userInput.getTeamName();
        Bracket curBracket = brackets.getBracket(bracketID);
        List<Team> teams = curBracket.getTeams();
        for(Team team: teams){
            if(team.getTeamName().equals(teamName)){
                return true;
            }
        }
        return false;
    }

    /**
     * find a blank team in the bracket
     * @return a blank team in the bracket, returns null if the bracket is full
     */
    public Team findBlankTeam(){
        Bracket curBracket = brackets.getBracket(bracketID);
        List<Team> teams = curBracket.getTeams();
        for(Team team: teams){
            if(team.getTeamName().contains("BlankTeam")){
                return team;
            }
        }
        return null;
    }

    /**
     * creates the team based on user input by updating the currrent bracket
     * @param userInput input data from user
     */
    public void createTeam(teamCreationID userInput){
        String teamName = userInput.getTeamName();
        User creator = accounts.getUser(creatorName);
        Team newTeam = findBlankTeam();
        oldTeam = newTeam.getTeamName();
        newTeam.setTeamName(teamName);
        newTeam.addTeamMember(creator);

    }
    /**
     * Checks if the creator is in a team
     * @return a boolean that indicates whether the current user is in a team
     */
    public boolean inATeam(Bracket bracket){
        User creator = accounts.getUser(creatorName);
        ArrayList<Team> teams = bracket.getTeams();
        for(Team team: teams){
            if(team.getTeamMembers().contains(creator)){
                return true;
            }
        }
        return false;
    }


    /**
     * Creates the new team based on user input, outputs an error message if the team
     * cannot be created.
     * @param userInput the input data
     * @return the output data
     */
    @Override
    public teamCreationOD createNewTeam(teamCreationID userInput) {
        if (checkTeamNameExists(userInput)) {
            return outputBoundary.prepareFailView("Team already exists.");
        } else if (findBlankTeam() == null) {
            return outputBoundary.prepareFailView("The bracket is full, please join an existing team.");
        } else if (!checkPlayer()){
            return outputBoundary.prepareFailView("Only players can create a new team.");
        } else if (inATeam(brackets.getBracket(bracketID))){
            return outputBoundary.prepareFailView("You are already in a team.");
        }

        createTeam(userInput);
        Bracket curBracket = brackets.getBracket(bracketID);

        BundleBracketData data = new BundleBracketData();
        data.bundleBracket(curBracket);
        LinkedHashMap<Integer, ArrayList<String>> gameToTeams = data.getGameToTeams();
        LinkedHashMap<String, ArrayList<String>> teamToPlayers = data.getTeamToPlayers();

        teamCreationDSID teamCreationDSID = new teamCreationDSID(this.brackets);
        try {
            this.gateway.save(teamCreationDSID);
        } catch (Exception e) {
            return this.outputBoundary.prepareFailView("There was an error saving the bracket.");
        }

        teamCreationOD outputData = new teamCreationOD(creatorName, userInput.getTeamName(), oldTeam,
                gameToTeams, teamToPlayers);
        return outputBoundary.prepareSuccessView(outputData);
    }

}
