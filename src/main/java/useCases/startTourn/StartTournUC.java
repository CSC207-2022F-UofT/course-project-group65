package useCases.startTourn;

import entities.*;
import useCases.generalClasses.InformationRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the Use Case (interactor) class for the StartTourn use case. This class is responsible
 * for starting a new tournament and updating the bracket repository.
 * It connects and uses many of the classes used in this package.
 * Implements the StartTournIB to allow the controller to call the startTourn method.
 */
public class StartTournUC implements StartTournIB{
    /** The output boundary */
    private final StartTournOB outputBoundary;
    /** The bracket repository to update the current bracket */
    private final BracketRepo brackets;
    /** The bracket id to access the current bracket (tournament)*/
    private final int bracketId;
    /** The current bracket */
    private final Bracket bracket;
    /** The User who wants to start a tournament */
    private final User user;
    /** The gateway to access the database to store info */
    private final StartTournGateway gateway;

    /**
     * Creates a new StartTournUC object.
     * @param outputBoundary The output boundary
     * @param currentUser The username of the current user starting a new tournament
     * @param informationRecord The information record containing the account and bracket repositories
     * @param bracketId The bracket id
     * @param gateway The gateway to access the database to store the info
     */
    public StartTournUC(StartTournOB outputBoundary, String currentUser, InformationRecord informationRecord,
                      int bracketId, StartTournGateway gateway) {
        this.outputBoundary = outputBoundary;
        AccountRepo accounts = informationRecord.getAccountData();
        this.brackets = informationRecord.getBracketData();
        this.bracketId = bracketId;
        this.bracket = brackets.getBracket(bracketId);
        this.user = accounts.getUser(currentUser);
        this.gateway = gateway;

    }



    /**
     * Checks if the user is an Overseer.
     * @return true if and only if the user is an Overseer.
     */
    public boolean checkUserRole() {
        return (Objects.equals(this.user.getBracketRole(bracketId), "Overseer"));
    }

    /**
     * Checks if the number of teams matches the maximum number of teams in the bracket.
     * @return true if and only if the number of teams matches the maximum number of teams in the bracket.
     */
    public boolean checkNumTeams() {
        List<Team> teams = this.bracket.getTeams();
        for (Team team : teams) {
            if (Objects.equals(team.getTeamName(), "BlankTeam")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all the teams are full.
     * @return true if and only if all the teams are full.
     */
    public boolean checkTeamFull() {
        int maxTeamSize = this.bracket.getTeamSize();
        List<Team> teams = this.bracket.getTeams();
        for (Team team : teams) {
            int thisTeamSize = team.getTeamSize();
            if (thisTeamSize < maxTeamSize) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if every game in the bracket has an observer assigned.
     * @return true if and only if every game in the bracket has an observer assigned.
     */
    public  boolean checkGameObserver() {
        return helperCheckGameObserver(this.bracket.getFinalGame());
    }

    // Recursive helper method for checkGameObserver.
    public boolean helperCheckGameObserver(Game game) {
        if (game.getObserver() == null) {
            return false;
        } else if (game.getPrevGame1() == null) {
            return true;
        } else {
            return (helperCheckGameObserver(game.getPrevGame1()) &&
                    helperCheckGameObserver(game.getPrevGame2()));
        }
    }

    /**
     * The start method of the StartTournUC class.
     * It sets the tournament condition to true, which means that the current tournament has started.
     */
    public void start() {
//        System.out.println(bracket.getTournamentCondition());
        this.bracket.setTournamentCondition(true);
//        System.out.println(bracket.getTournamentCondition());
    }

    /**
     * The main startTourn method.
     * @return the output data that contains potential error types.
     */
    @Override
    public StartTournOD startTourn() {
        ArrayList<String> errors = new ArrayList<>();
        String errorType1 = "USERROLE";
        String errorType2 = "NUMTEAMS";
        String errorType3 = "NOOBSERVER";
        String errorType4 = "TEAMNOTFULL";

        if (!checkUserRole()) {
            errors.add(errorType1);
//            return this.outputBoundary.presentError("You do not have permission to start the tournament.");
        }

        if (checkNumTeams()) {
            errors.add(errorType2);
//            return this.outputBoundary.presentError("The number of teams is invalid.");
        }

        if (!checkGameObserver()) {
            errors.add(errorType3);
//              return this.outputBoundary.presentError("There is at least one game that does not have an observer assigned.");
        }

        if (checkTeamFull()) {
            errors.add(errorType4);
//              return this.outputBoundary.presentError("The teams are not full.");
        }

//        inputData.getBracket().setTournamentCondition(true);
        StartTournDSID dataStoreID = new StartTournDSID(this.brackets);

        try {
            this.gateway.save(dataStoreID);
        } catch (Exception e){
            return this.outputBoundary.presentError("Error saving to database.");
        }
        StartTournOD outputData = new StartTournOD(errors);
        return this.outputBoundary.presentSuccess(outputData);
    }
}
