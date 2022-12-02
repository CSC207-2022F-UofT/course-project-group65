package useCases.startTourn;

import entities.*;
import useCases.generalClasses.InformationRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A use case for starting the tournament.
 */
public class StartTournUC implements StartTournIB{
    private final StartTournOB outputBoundary;
    private final AccountRepo accounts;
    private final BracketRepo brackets;
    private final int bracketId;
    private final Bracket bracket;
    private final User user;
    private final StartTournGateway gateway;

    public StartTournUC(StartTournOB outputBoundary, String currentUser, InformationRecord informationRecord,
                      int bracketId, StartTournGateway gateway) {
        this.outputBoundary = outputBoundary;
        this.accounts = informationRecord.getAccountData();
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

    public void start() {
//        System.out.println(bracket.getTournamentCondition());
        this.bracket.setTournamentCondition(true);
//        System.out.println(bracket.getTournamentCondition());
    }

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



    public AccountRepo getAccounts() {
        return accounts;
    }

    public BracketRepo getBrackets() {
        return brackets;
    }
}
