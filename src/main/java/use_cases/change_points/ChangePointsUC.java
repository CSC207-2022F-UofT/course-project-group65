package use_cases.change_points;

import entities.*;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;
import use_cases.general_classes.perm_restriction_strategies.PermissionChecker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A use case class that handles the changing of points for a user. It will change the points of the team and
 * save and update the bracket repository afterwards.
 */
public class ChangePointsUC implements ChangePointsIB {
    private final BracketRepo bracketRepo;
    public Bracket bracket;
    public int newPoints;
    public Team team;
    public User user;
    public Game game;
    public ChangePointsOB outputBoundary;
    public ChangePointsGateway gateway;

    /**
     * Creates a new ChangePointsUC object.
     *
     * @param outputBoundary    The output boundary used for updating the view
     * @param gateway           The gateway to access the database to store info
     * @param informationRecord The information record containing the bracket repository
     * @param bracketID         The ID of the bracket to change points in
     * @param username          The username of the user changing points
     */
    public ChangePointsUC(ChangePointsOB outputBoundary, ChangePointsGateway gateway,
                          InformationRecord informationRecord, int bracketID, String username) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        AccountRepo accountRepo = informationRecord.getAccountData();
        this.bracketRepo = informationRecord.getBracketData();
        this.bracket = this.bracketRepo.getBracket(bracketID);
        this.user = accountRepo.getUser(username);
    }

    /**
     * Finds the team in the bracket that should have its points changed.
     *
     * @param inputData The input data containing the team name and the new points
     */
    private void findTeam(ChangePointsID inputData) {
        ArrayList<Team> teams = this.game.getTeams();
        for (Team team : teams) {
            if (team.getTeamName().equals(inputData.getTeamNameCPID())) {
                this.team = team;
            }
        }
    }

    /**
     * Checks whether the user has permission to change points in the bracket. By using the permission checker class.
     * Essentially a helper method for the changePoints method.
     *
     * @param user The user changing points
     * @return True if the user has permission to change points in the bracket, false otherwise
     */
    private boolean checkUserPermission(User user) {
        PermissionChecker permissionChecker = new PermissionChecker();
        ArrayList<String> permittedUsers = new ArrayList<>(Arrays.asList("Observer", "Overseer"));
        return permissionChecker.checkUserPermission(permittedUsers, user, this.bracket.getTournamentID());
    }

    /**
     * Checks whether the user is the correct observer assigned to this game.
     *
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
     * Checks whether the team exists in the game.
     *
     * @param team The team to check
     * @return True if the team exists in the game, false otherwise
     */
    private boolean checkTeam(Team team) {
        return this.game.getTeams().contains(team);
    }

    /**
     * Check whether the game exists in the bracket.
     *
     * @param game The game to check
     * @return True if the game exists in the bracket, false otherwise
     */
    private boolean checkGame(Game game) {
        return game != null;
    }

    /**
     * Checks if all the games in the round are full. We only allow the points to be changed if all the games in the
     * round are full.
     *
     * @param game The game whose round is to be checked
     * @return True if all the games in the round are full, false otherwise
     */
    private boolean checkAllGamesFull(Game game) {
        int teamRound = game.getGameRound();
        ArrayList<Game> games = bracket.getGamesInRound(teamRound);
        for (Game g : games) {
            if (g.getNumTeams() < 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the points are valid (i.e. not negative and less than or equal to win condition).
     *
     * @param points The points to check
     * @return True if the points are valid, false otherwise
     */
    private boolean validPoints(int points) {
        int changedPoints = points + this.game.getTeamPoints(this.team);
        return changedPoints <= this.bracket.getWinCondition() && changedPoints >= 0;
    }


    /**
     * Change the points of a team in a game, provided that all the checks pass.
     *
     * @param inputData The input data to use
     */
    public ChangePointsOD changePoints(ChangePointsID inputData) {
        this.game = bracket.getGame(inputData.getGameIDCP());

        // Checks to see if the request is valid.
        if (!checkGame(this.game)) {
            return this.outputBoundary.presentError("The game you are trying to change points in is not " +
                    "in the bracket.");
        }
        findTeam(inputData);
        if (!this.bracket.getTournamentCondition()) {
            return this.outputBoundary.presentError("The tournament is not in progress. " +
                    "Tournament had ended or has not yet started.");
        }
        if (!checkTeam(this.team)) {
            return this.outputBoundary.presentError("The team you are trying to change points for is not " +
                    "in the game.");
        }

        this.newPoints = inputData.getNewPointsCP();
        int changedPoints = this.newPoints + this.game.getTeamPoints(this.team);

        if (game.getGameStatus()) {
            return this.outputBoundary.presentError("This game has already been won.");
        }

        if (!checkUserPermission(this.user)) {
            return this.outputBoundary.presentError("You do not have permission to change points.");
        }

        if (this.user.getBracketRole(this.bracket.getTournamentID()).equals("Observer")) {
            if (!checkObserverAssigned(this.user)) {
                return this.outputBoundary.presentError("You are not assigned to this game.");
            }
        }

        if (!validPoints(this.newPoints)) {
            return this.outputBoundary.presentError("The points you are trying to change to are not valid.");
        }

        if (!checkAllGamesFull(this.game)) {
            return this.outputBoundary.presentError("Not all games in the round are full. You cannot " +
                    "add points yet");
        }

        this.game.setTeam(this.team, changedPoints);

        // Saving to file.
        ChangePointsDSID dsInputData = new ChangePointsDSID(this.bracketRepo);
        try {
            this.gateway.save(dsInputData);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the bracket.");
        }

        // Bundling data for output data.
        BundleBracketData bundleBracketData = new BundleBracketData();
        bundleBracketData.bundleBracket(this.bracket);
        ArrayList<String> teams = bundleBracketData.getGameToTeams().get(this.game.getGameID());
        ArrayList<Integer> points = bundleBracketData.getGameToScores().get(this.game.getGameID());

        ChangePointsOD outputData = new ChangePointsOD(this.game.getGameID(), teams, points);

        return this.outputBoundary.presentSuccess(outputData);
    }
}
