package useCases.declareWinner;

import entities.*;
import useCases.generalClasses.TreeMethods;
import useCases.generalInterfaces.CheckUserPermissionIF;

import java.util.ArrayList;

public class DeclareWinnerUC implements CheckUserPermissionIF, DeclareWinnerIB {

    public Bracket bracket;
    public User user;
    public Game game;
    public DeclareWinnerOB outputBoundary;
    public TreeMethods treeMethodAccess;

    public void DeclareWinner(DeclareWinnerOB outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void findUser(DeclareWinnerID inputData){
        this.user = inputData.getUserDW();
    }

    public void findBracket(DeclareWinnerID inputData) {
        this.bracket = inputData.getBracketDW();
        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
        this.treeMethodAccess = new TreeMethods(bracketType);
        findGame(inputData.getGameIDDW(), this.bracket.getFinalGame());
    }

    public void findGame(int gameID, Game head) {
        this.game = this.treeMethodAccess.findGame(gameID, head);
    }

    public boolean checkUserPermission(User user) {
        String userRole = user.getBracketRole(this.bracket.getTournamentID());
        User assignedObserver = this.game.getObserver();
        String username = user.getUsername();
        String assignedObserverUsername = assignedObserver.getUsername();

        if (userRole.equals("Overseer")) {
            return true;
        } else if (userRole.equals("Observer")) {
            return username.equals(assignedObserverUsername);
        } else {
            return false;
        }
    }

    // Make helper functions private.

    public boolean checkGame(Game game) {
        return this.game != null;
    }

    public DeclareWinnerOD setWinner(DeclareWinnerID inputData) {
        findUser(inputData);
        findBracket(inputData);

        if (!checkUserPermission(user)) {
            return this.outputBoundary.presentError("You do not have permission to declare a winner for " +
                    "this game.");
        }

        if (!checkGame(game)) {
            return this.outputBoundary.presentError("This game does not exist.");
        }

        ArrayList<Team> teams = this.game.getTeams();
        for (Team team : teams) {
            if (this.game.getTeamPoints(team) == this.bracket.getWinCondition()) {
                this.game.setWinner(team);
                this.game.setGameStatus(true);
                DeclareWinnerOD outputData = new DeclareWinnerOD(this.game, team, this.bracket);
                return this.outputBoundary.presentSuccess(outputData);
            }
        }
        return this.outputBoundary.presentError("The game has not been completed yet.");
    }
}