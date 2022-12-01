package useCases.startTourn;

import entities.*;

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

    public StartTournUC(StartTournOB outputBoundary, String currentUser, AccountRepo accounts, BracketRepo brackets,
                      int bracketId) {
        this.outputBoundary = outputBoundary;
        this.accounts = accounts;
        this.brackets = brackets;
        this.bracketId = bracketId;
        this.bracket = brackets.getBracket(bracketId);
        this.user = accounts.getUser(currentUser);

    }




    public boolean checkUserRole() {
        return (Objects.equals(this.user.getBracketRole(bracketId), "Overseer"));
    }

    public boolean checkNumTeams() {
        List<Team> teams = this.bracket.getTeams();
        for (Team team : teams) {
            if (Objects.equals(team.getTeamName(), "BlankTeam")) {
                return false;
            }
        }
        return true;
    }

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
            return this.outputBoundary.presentError("You do not have permission to start the tournament.");
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
