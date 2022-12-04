package use_cases.advance_team;

import entities.*;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;
import use_cases.general_classes.perm_restriction_strategies.PermissionChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * This is the Use Case (interactor) class for the AdvanceTeam use case. This class is responsible
 * for advancing a team in a bracket (based on the user input) and updating the bracket repository.
 * Implements the AdvanceTeamIB to allow the controller to call the advance method.
 */
public class AdvanceTeamUC implements AdvanceTeamIB {

    private final BracketRepo bracketRepo;
    public Bracket bracket;
    public User user;
    public Game game;
    public AdvanceTeamOB outputBoundary;
    public AdvanceTeamGateway gateway;

    /**
     * Creates a new AdvanceTeamUC object.
     *
     * @param outputBoundary    The output boundary used for updating the view
     * @param gateway           The gateway to access the database to store info
     * @param informationRecord The information record containing the bracket repository
     * @param bracketID         The ID of the bracket to advance a team in
     * @param username          The username of the user advancing a team
     */

    public AdvanceTeamUC(AdvanceTeamOB outputBoundary, AdvanceTeamGateway gateway,
                         InformationRecord informationRecord, int bracketID, String username) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        AccountRepo accountRepo = informationRecord.getAccountData();
        this.bracketRepo = informationRecord.getBracketData();
        this.user = accountRepo.getUser(username);
        this.bracket = this.bracketRepo.getBracket(bracketID);
        this.user = accountRepo.getUser(username);
    }

    /**
     * Checks whether the user has permission to advance a team in the bracket. By using the permission checker class.
     * Essentially a helper method for the advanceWinner method.
     *
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
     * Checks whether the game is non-empty. Essentially a helper method for the advanceWinner method.
     *
     * @param game The game to check.
     * @return True if the game is non-empty, false otherwise
     */
    private boolean checkGame(Game game) {
        return game != null;
    }

    /**
     * Checks whether the game is complete, which only happens if it has a winner.
     * Essentially a helper method for the advanceWinner method.
     *
     * @param game The game to check.
     * @return True if the game is complete, false otherwise
     */
    private boolean checkGameWinner(Game game) {
        return game.getGameStatus();
    }

    /**
     * This is a helper for insertTeam. It returns all of the games in a level (or height) of a bracket, which can be
     * thought of a tree if bracket is an instance of default bracket, or a graph, else.
     *
     * @param roundNum The round number of the games to return
     * @return An array list of games in the round number
     */
    private ArrayList<Game> returnLevelGames(int roundNum) {
        return bracket.getGamesInRound(roundNum);
    }

    /**
     * This is method that inserts a team into a game in the following round, once the game is complete.
     *
     * @param team The team to insert
     * @param game The game to insert the team into
     * @return The game with the inserted team
     */
    private Game insertTeam(Team team, Game game) {
        // This line returns the games that are in the next round of the bracket, so we know the set of potential games
        // to insert the team into.
        ArrayList<Game> games = returnLevelGames(this.game.getGameRound() + 1);
        for (Game g : games) {
            // Basically, since the game is in the next round, we want to find the game g such that g's parent is the
            // game that the team was originally a part of.
            if (g.getPrevGame1().getGameID() == game.getGameID() || g.getPrevGame2().getGameID() == game.getGameID()) {
                g.setTeam(team, 0);
                g.setNumTeams(g.getNumTeams() + 1); // Increases the number of teams in the game by 1/
                return g;
            }
        }
        return null;
    }

    /**
     * Advance the team of the given game to the next round, and return the output data if checks pass, and save
     * the updated bracket repo to the gateway.
     *
     * @param inputData The input data to use
     */
    public AdvanceTeamOD advanceWinner(AdvanceTeamID inputData) {
        this.game = bracket.getGame(inputData.getGameIDAT());

        if (!checkGame(this.game)) {
            return this.outputBoundary.presentError("This game does not exist.");
        }

        if (this.game.getGameRound() >= this.bracket.getNumRounds()) {
            return this.outputBoundary.presentError("This game is in the final round.");
        }

        if (!checkUserPermission(this.user)) {
            return this.outputBoundary.presentError("You do not have permission to advance this team.");
        }

        if (this.user.getBracketRole(this.bracket.getTournamentID()).equals("Observer")) {
            if (!checkObserverAssigned(this.user)) {
                return this.outputBoundary.presentError("You are not assigned to this game.");
            }
        }

        if (!checkGameWinner(this.game)) {
            return this.outputBoundary.presentError("This game has not been completed.");
        }

        Team winningTeam = this.game.getWinner();
        Game advancedGame = insertTeam(winningTeam, this.game);

        // This block saves the data.
        AdvanceTeamDSID dsInputData = new AdvanceTeamDSID(this.bracketRepo);
        try {
            this.gateway.save(dsInputData);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the information.");
        }

        // The purpose of this class is to bundle data for the view.
        BundleBracketData bundleBracketData = new BundleBracketData();
        bundleBracketData.bundleBracket(this.bracket);
        LinkedHashMap<Integer, ArrayList<String>> gameTeamMap = bundleBracketData.getGameToTeams();
        LinkedHashMap<Integer, ArrayList<Integer>> gameScoresMap = bundleBracketData.getGameToScores();

        assert advancedGame != null; // This assertion is true because we checked that the game is non-empty.
        AdvanceTeamOD outputData = new AdvanceTeamOD(advancedGame.getGameID(), gameTeamMap, gameScoresMap);
        return this.outputBoundary.presentSuccess(outputData);
    }
}
