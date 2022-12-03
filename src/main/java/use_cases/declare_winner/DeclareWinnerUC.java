package use_cases.declare_winner;

import entities.*;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.perm_restriction_strategies.PermissionChecker;

import java.util.ArrayList;
import java.util.Arrays;

/** This class is responsible for declaring the winner of a game once the game has a suitable winner available. It then
 * updates the information in the bracket and saves it.
 */
public class DeclareWinnerUC implements DeclareWinnerIB {

    public Bracket bracket;
    public User user;
    public Game game;
    public DeclareWinnerOB outputBoundary;
    public DeclareWinnerGateway gateway;
    private final BracketRepo bracketRepo;

    /** Constructor for DeclareWinnerUC
     *
     * @param outputBoundary The output boundary for this use case
     * @param gateway The gateway for this use case
     * @param informationRecord The information record for this use case
     * @param bracketID The ID of the bracket that the game is in
     * @param username The username of the user that is declaring the winner
     */
    public DeclareWinnerUC(DeclareWinnerOB outputBoundary, DeclareWinnerGateway gateway,
                           InformationRecord informationRecord, int bracketID, String username) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        this.bracketRepo = informationRecord.getBracketData();
        AccountRepo accountRepo = informationRecord.getAccountData();
        this.bracket = this.bracketRepo.getBracket(bracketID);
        this.user = accountRepo.getUser(username);
        this.gateway = gateway;
    }

    /**
     * Checks whether the user has permission to advance a team in the bracket. By using the permission checker class.
     * Essentially a helper method for the advanceWinner method.
     * @param user The user advancing a team
     * @return True if the user has permission to advance a team in the bracket, false otherwise
     */
    private boolean checkUserPermission(User user) {
        PermissionChecker permissionChecker = new PermissionChecker();
        ArrayList<String> permittedUsers = new ArrayList<>(Arrays.asList("Observer", "Overseer"));
        return permissionChecker.checkUserPermission(permittedUsers, user, this.bracket.getTournamentID());
    }

    /**
     * Checks whether the user is the correct observer assigned to this game.
     * @param user The user advancing a team
     * @return True if the user is the correct observer assigned to this game, false otherwise
     */
    private boolean checkObserverAssigned(User user) {
        User assignedObserver = this.game.getObserver();
        if (assignedObserver == null) {
            return false;
        }
        return user.getUsername().equals(assignedObserver.getUsername());
    }

    /**
     * Checks whether the game is non-empty. Essentially a helper method for the advanceWinner method.
     * @param game The game to check.
     * @return True if the game is non-empty, false otherwise
     */
    private boolean checkGame(Game game) {
        return game != null;
    }

    /** This method is responsible for declaring the winner of a game once the game has a suitable winner available. It then
     * updates the information in the bracket and saves it.
     *
     * @param game The game that the winner is being declared for
     * @return True if the winner was declared successfully, false otherwise
     */
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
        this.game = bracket.getGame(inputData.getGameIDDW());


        // Multiple checks to see if the request is valid.
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

        if (this.game.getGameStatus()){
            return this.outputBoundary.presentError("This game has already been won.");
        }

        boolean winnerDeclared = determineWinner(this.game);

        if (!winnerDeclared) {
            return this.outputBoundary.presentError("No team has won this game yet.");
        }

        // Saves the information to the gateway.
        DeclareWinnerDSID declareWinnerDSID = new DeclareWinnerDSID(this.bracketRepo);
        try {
            this.gateway.save(declareWinnerDSID);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the bracket.");
        }

        Team winner = this.game.getWinner();
        DeclareWinnerOD outputData = new DeclareWinnerOD(winner.getTeamName());
        return this.outputBoundary.presentSuccess(outputData);
    }
}