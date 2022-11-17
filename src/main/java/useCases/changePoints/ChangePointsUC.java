package useCases.changePoints;

import entities.*;
import useCases.generalClasses.permRestrictionStrategies.PermissionChecker;
import useCases.generalClasses.traversalStrategies.TreeMethods;
import java.util.ArrayList;
import java.util.Arrays;


public class ChangePointsUC implements ChangePointsIB{
    public Bracket bracket;
    public int newPoints;
    public Team team;
    public User user;
    public Game game;
    public TreeMethods treeMethodAccess;
    public ChangePointsOB outputBoundary;
    private final AccountRepo accountRepo;
    private final BracketRepo bracketRepo;
    public ChangePointsGateway gateway;

    /**
     * Construct a ChangePointsUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param bracketRepo    The BracketRepo to use
     * @param accountRepo    The AccountRepo to use
     * @param gateway        The gateway to use
     * @param outputBoundary The output boundary to use
     */

    public ChangePointsUC(ChangePointsOB outputBoundary, ChangePointsGateway gateway,
                          AccountRepo accountRepo, BracketRepo bracketRepo) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        this.accountRepo = accountRepo;
        this.bracketRepo = bracketRepo;
    }

    private void findUser(ChangePointsID inputData){
        this.user = this.accountRepo.getUser(inputData.getUsernameCP());
    }

    private void findBracket(ChangePointsID inputData) {
        this.bracket = bracketRepo.getBracket(inputData.getBracketIDCP());
        String bracketType = "Default"; // This can be changed later to accommodate different types of brackets
        this.treeMethodAccess = new TreeMethods(bracketType);
    }

    private void findGame(int gameID, Game head) {
        this.game = this.treeMethodAccess.findGame(gameID, head);
    }

    private void findTeam(ChangePointsID inputData) {
        ArrayList<Team> teams = this.game.getTeams();
        for (Team team : teams) {
            if (team.getTeamName().equals(inputData.getTeamNameCPID())) {
                this.team = team;
            }
        }
    }

    // Check whether the user has permission to change the points of a team in a game
    private boolean checkUserPermission(User user) {
        PermissionChecker permissionChecker = new PermissionChecker();
        ArrayList<String> permittedUsers = new ArrayList<>(Arrays.asList("Observer", "Overseer"));
        return permissionChecker.checkUserPermission(permittedUsers, user, this.bracket.getTournamentID());
    }

    private boolean checkObserverAssigned(User user) {
        User assignedObserver = this.game.getObserver();
        if (user.getBracketRole(this.bracket.getTournamentID()).equals("Observer")) {
            return assignedObserver.getUsername().equals(user.getUsername());
        }
        return true;
    }

    private boolean checkTeam(Team team) {
        ArrayList<Team> teams = (ArrayList<Team>) this.bracket.getTeams();
        return teams.contains(team);
    }

    private boolean checkGame(Game game) {
        return game != null;
    }

    public ArrayList<Game> returnLevelGames(Game head, int roundNum){
        return this.treeMethodAccess.levelNodes(head, roundNum);
    }


    // This method is used to check if the game is full and if the game is full, it will check if all the games
    // in the round are full
    private boolean checkAllGamesFull(Game game){
        int teamRound = game.getGameRound();
        ArrayList<Game> games = returnLevelGames(this.bracket.getFinalGame(), teamRound);
        for (Game g: games){
            if (g.getNumTeams() < 2){
                return false;
            }
        }
        return true;
    }

    // This method checks if the points are valid.
    private boolean validPoints(Game game, int points){
        int changedPoints = points + this.game.getTeamPoints(this.team);
        if (!game.getGameStatus()) {
            return changedPoints <= this.bracket.getWinCondition() && changedPoints >= 0;
        } else {
            return false;
        }
    }

    /**
     * Change the points of a team in a game, provided that all the checks pass.
     *
     * @param inputData The input data to use
     */

    public ChangePointsOD changePoints(ChangePointsID inputData) {
        findUser(inputData);
        findBracket(inputData);
        findGame(inputData.getGameIDCP(), this.bracket.getFinalGame());
        findTeam(inputData);
        this.newPoints = inputData.getNewPointsCP();

        if (!checkUserPermission(this.user)) {
            return this.outputBoundary.presentError("You do not have permission to change points.");
        }

        if (!checkObserverAssigned(this.user)) {
            return this.outputBoundary.presentError("You are not assigned to this game.");
        }

        if (!checkTeam(this.team)) {
            return this.outputBoundary.presentError("The team you are trying to change points for is not " +
                    "in the bracket.");
        }
        if (!checkGame(this.game)) {
            return this.outputBoundary.presentError("The game you are trying to change points in is not " +
                    "in the bracket.");
        }

        if (!validPoints(this.game, this.newPoints)) {
            return this.outputBoundary.presentError("The points you are trying to change to are not valid.");
        }

        if (!checkAllGamesFull(this.game)) {
            return this.outputBoundary.presentError("Not all games in the round are full. You cannot " +
                    "add points yet");
        }

        this.game.setTeam(this.team, this.newPoints);

        ChangePointsDSID dsInputData = new ChangePointsDSID(this.bracketRepo);

        try {
            this.gateway.save(dsInputData);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the bracket.");
        }

        ChangePointsOD outputData = new ChangePointsOD(this.game, this.team, this.game.getTeamPoints(this.team),
                this.bracket);
        return this.outputBoundary.presentSuccess(outputData);
    }
}
