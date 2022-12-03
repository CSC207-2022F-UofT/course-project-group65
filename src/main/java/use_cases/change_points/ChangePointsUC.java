package use_cases.change_points;

import entities.*;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;
import use_cases.general_classes.perm_restriction_strategies.PermissionChecker;
//import useCases.generalClasses.traversalStrategies.BracketMethods;
//import useCases.generalClasses.traversalStrategies.DefaultBracketMethods;

import java.util.ArrayList;
import java.util.Arrays;


public class ChangePointsUC implements ChangePointsIB{
    public Bracket bracket;
    public int newPoints;
    public Team team;
    public User user;
    public Game game;
//    public BracketMethods treeMethodAccess;
//    public BracketMethods bracketMethods;
    public ChangePointsOB outputBoundary;
    private BracketRepo bracketRepo;
    private AccountRepo accountRepo;
    public ChangePointsGateway gateway;

    /**
     * Construct a ChangePointsUC interactor instance with the given BracketRepo and AccountRepo.
     *
//     * @param bracketRepo    The BracketRepo to use
//     * @param accountRepo    The AccountRepo to use
     * @param gateway        The gateway to use
     * @param outputBoundary The output boundary to use
     * @param username         The ID of the user who is advancing the team
     * @param bracketID      The ID of the bracket the user is advancing the team in
     */

//    public ChangePointsUC(ChangePointsOB outputBoundary, ChangePointsGateway gateway,
//                          AccountRepo accountRepo, BracketRepo bracketRepo, int bracketID, String username) {
//        this.outputBoundary = outputBoundary;
//        this.gateway = gateway;
//        this.bracketRepo = bracketRepo;
//        this.bracket = this.bracketRepo.getBracket(bracketID);
//        this.user = accountRepo.getUser(username);
////        String bracketType = "Default"; // This can be changed later to accommodate different types of brackets
////        this.treeMethodAccess = new BracketMethods(bracketType);
//        this.bracketMethods = new DefaultBracketMethods((DefaultBracket) bracket); //possibly changing
//    }

    public ChangePointsUC(ChangePointsOB outputBoundary, ChangePointsGateway gateway,
                          InformationRecord informationRecord, int bracketID, String username) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        this.accountRepo = informationRecord.getAccountData();
        this.bracketRepo = informationRecord.getBracketData();
        this.bracket = this.bracketRepo.getBracket(bracketID);
        this.user = this.accountRepo.getUser(username);
//        this.bracketMethods = new DefaultBracketMethods((DefaultBracket) bracket); //possibly changing
//        try{
//            this.bracketRepo = (BracketRepo) bracketRepo;
//            this.bracket = this.bracketRepo.getBracket(bracketID);
//            this.accountRepo = (AccountRepo) accountRepo;
//            this.user = this.accountRepo.getUser(username);
//            this.bracketMethods = new DefaultBracketMethods((DefaultBracket) bracket); //possibly changing
//        } catch (Exception e){
//            System.out.println("Error in ChangePointsUC constructor");
//        }
//        this.bracketRepo = (BracketRepo) bracketRepo;
//        this.bracket = this.bracketRepo.getBracket(bracketID);
//        this.user = (User) accountRepo;
    }

//    private void findGame(int gameID, Game head) {
//        this.game = this.treeMethodAccess.findGame(gameID, head);
//    }

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
        if (assignedObserver == null) {
            return false;
        }
        return user.getUsername().equals(assignedObserver.getUsername());
    }

    private boolean checkTeam(Team team) {
        return this.game.getTeams().contains(team);
    }

    private boolean checkGame(Game game) {
        return game != null;
    }

//    public ArrayList<Game> returnLevelGames(Game head, int roundNum){
//        return this.treeMethodAccess.levelNodes(head, roundNum);
//    }


    // This method is used to check if the game is full and if the game is full, it will check if all the games
    // in the round are full
    private boolean checkAllGamesFull(Game game){
        int teamRound = game.getGameRound();
//        ArrayList<Game> games = returnLevelGames(this.bracket.getFinalGame(), teamRound);
        ArrayList<Game> games = bracket.getGamesInRound(teamRound);
//        ArrayList<Game> games;
//        if (bracket instanceof DefaultBracket){
//            RoundGames<DefaultBracket> roundGames = new TreeRoundGames<>();
//            games = roundGames.getGamesInRound(bracket.getFinalGame(), teamRound);
//        } else {
//            RoundGames<Bracket> roundGames = new GeneralisedRoundGames<>();
//            games = roundGames.getGamesInRound(bracket.getFinalGame(), teamRound);
//        }
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
        return changedPoints <= this.bracket.getWinCondition() && changedPoints >= 0;
    }


    /**
     * Change the points of a team in a game, provided that all the checks pass.
     *
     * @param inputData The input data to use
     */

    public ChangePointsOD changePoints(ChangePointsID inputData) {
//        findGame(inputData.getGameIDCP(), this.bracket.getFinalGame());
        this.game = bracket.getGame(inputData.getGameIDCP());

//        if (bracket instanceof DefaultBracket){
//            GameFinder<DefaultBracket> gameFinder = new TreeGameFinder<>();
//            this.game = gameFinder.getGame(inputData.getGameIDCP(), this.bracket.getFinalGame());
//        } else {
//            GameFinder<Bracket> gameFinder = new GeneralisedGameFinder<>();
//            this.game = gameFinder.getGame(inputData.getGameIDCP(), this.bracket.getFinalGame());
//        }

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

        if (game.getGameStatus()){
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

        this.game.setTeam(this.team, changedPoints);

        ChangePointsDSID dsInputData = new ChangePointsDSID(this.bracketRepo);

        try {
            this.gateway.save(dsInputData);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the bracket.");
        }

        BundleBracketData bundleBracketData = new BundleBracketData();
        bundleBracketData.bundleBracket(this.bracket);
        ArrayList<String> teams = bundleBracketData.getGameToTeams().get(this.game.getGameID());
        ArrayList<Integer> points = bundleBracketData.getGameToScores().get(this.game.getGameID());

        ChangePointsOD outputData = new ChangePointsOD(teams, points);

//        ChangePointsOD outputData = new ChangePointsOD(this.game, this.team, this.game.getTeamPoints(this.team),
//                this.bracket);
        return this.outputBoundary.presentSuccess(outputData);
    }
}