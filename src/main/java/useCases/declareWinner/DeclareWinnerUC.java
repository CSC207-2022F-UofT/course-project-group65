package useCases.declareWinner;

import entities.*;
import useCases.generalClasses.permRestrictionStrategies.PermissionChecker;
import useCases.generalClasses.traversalStrategies.TreeMethods;
import java.util.ArrayList;
import java.util.Arrays;

public class DeclareWinnerUC implements DeclareWinnerIB {

    public Bracket bracket;
    public User user;
    public Game game;
    public DeclareWinnerOB outputBoundary;
    public TreeMethods treeMethodAccess;
    public DeclareWinnerGateway gateway;
    private BracketRepo bracketRepo;

    /**
     * Construct a DeclareWinnerUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param bracketRepo    The BracketRepo to use
     * @param gateway        The gateway to use
     * @param outputBoundary The output boundary to use
     * @param username         The ID of the user who is advancing the team
     * @param bracketID      The ID of the bracket the user is advancing the team in
     */

    public void DeclareWinner(DeclareWinnerOB outputBoundary, DeclareWinnerGateway gateway,
                              BracketRepo bracketRepo, AccountRepo accountRepo, int bracketID, String username) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        this.bracketRepo = bracketRepo;
        this.bracket = bracketRepo.getBracket(bracketID);
        this.user = accountRepo.getUser(username);
        String bracketType = "Default"; // This can be changed later to accommodate different types of brackets
        this.treeMethodAccess = new TreeMethods(bracketType);
    }

    private void findGame(int gameID, Game head) {
        this.game = this.treeMethodAccess.findGame(gameID, head);
    }

    private boolean checkUserPermission(User user) {
        PermissionChecker permissionChecker = new PermissionChecker();
        ArrayList<String> permittedUsers = new ArrayList<>(Arrays.asList("Observer", "Overseer"));
        return permissionChecker.checkUserPermission(permittedUsers, user, this.bracket.getTournamentID());
    }

    private boolean checkObserverAssigned(User user) {
        User assignedObserver = this.game.getObserver();
        return user.getUsername().equals(assignedObserver.getUsername());
    }

    private boolean checkGame(Game game) {
        return game != null;
    }

    // Note: If there are multiple games that match the win condition, we choose to return the first team in the list.
    private boolean determineWinner(Game game) {
        ArrayList<Team> teams = game.getTeams();
        for (Team team : teams) {
            if (game.getTeamPoints(team) == this.bracket.getWinCondition()) {
                game.setWinner(team);
                game.setGameStatus(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Declares the winner of a game, given all the checks pass.
     *
     * @param inputData The input data to use
     */

    public DeclareWinnerOD setWinner(DeclareWinnerID inputData) {
        findGame(inputData.getGameIDDW(), this.bracket.getFinalGame());

        if (!checkUserPermission(this.user)) {
            return this.outputBoundary.presentError("You do not have permission to declare a winner for " +
                    "this game.");
        }

        if (this.user.getBracketRole(this.bracket.getTournamentID()).equals("Observer")) {
            if (!checkObserverAssigned(this.user)) {
                return this.outputBoundary.presentError("You are not assigned to this game.");
            }
        }

        if (!checkGame(this.game)) {
            return this.outputBoundary.presentError("This game does not exist.");
        }

        boolean winnerDeclared = determineWinner(this.game);

        if (!winnerDeclared) {
            return this.outputBoundary.presentError("No team has won this game yet.");
        }

        DeclareWinnerDSID declareWinnerDSID = new DeclareWinnerDSID(this.bracketRepo);
        try {
            this.gateway.save(declareWinnerDSID);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the bracket.");
        }

        Team winner = this.game.getWinner();
        DeclareWinnerOD outputData = new DeclareWinnerOD(this.game, winner, this.bracket);
        return this.outputBoundary.presentSuccess(outputData);
    }
}