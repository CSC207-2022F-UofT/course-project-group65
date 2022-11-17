package useCases.startTourn;

import entities.*;
import useCases.endTourn.EndTournOB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StartTournUC implements StartTournIB{
    private StartTournOB outputBoundary;
    private String currentUser;
    private AccountRepo accounts;
    private BracketRepo brackets;
    private int bracketId;
    private Bracket bracket;
    private User user;

    public StartTournUC(StartTournOB outputBoundary, String currentUser, AccountRepo accounts, BracketRepo brackets,
                      int bracketId) {
        this.outputBoundary = outputBoundary;
        this.currentUser = currentUser;
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

    // TODO: implement the check part after finishing front end.
    /// TODO: after returning the error message to the user, we want to know whether the user still wants to
    /// start the tournament anyway. but for now I don't know how to implement that so I'm just leaving it.
    @Override
    public StartTournOD startTourn(StartTournID inputData) {

//        if (!checkUserRole(inputData)) {
//            return this.outputBoundary.presentError("You do not have permission to start the tournament.");
//        }
//
//        if (!checkNumTeams(inputData)) {
//            return this.outputBoundary.presentError("The number of teams is invalid.");
//        }
//
//        if (!checkGameObserver(inputData)) {
//              return this.outputBoundary.presentError("There is at least one game that does not have an observer assigned.");
//        }
//
//        if (!checkTeamFull(inputData)) {
//              return this.outputBoundary.presentError("The teams are not full.");
//        }

        inputData.getBracket().setTournamentCondition(true);

        StartTournOD outputData = new StartTournOD(currentUser, accounts, brackets, bracketId);
        return this.outputBoundary.presentSuccess(outputData);
    }
}
