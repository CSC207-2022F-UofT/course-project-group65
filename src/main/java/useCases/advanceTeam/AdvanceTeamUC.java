package useCases.advanceTeam;
import entities.*;
import entities.game_finder_strategy.GameFinder;
import entities.game_finder_strategy.GeneralisedGameFinder;
import entities.game_finder_strategy.TreeGameFinder;
import entities.round_games_strategy.GeneralisedRoundGames;
import entities.round_games_strategy.RoundGames;
import entities.round_games_strategy.TreeRoundGames;
import useCases.generalClasses.InformationRecord;
import useCases.generalClasses.bundleBracketData.BundleBracketData;
import useCases.generalClasses.permRestrictionStrategies.PermissionChecker;
//import useCases.generalClasses.traversalStrategies.BracketMethods;
//import useCases.generalClasses.traversalStrategies.DefaultBracketMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class AdvanceTeamUC implements AdvanceTeamIB {

    public Bracket bracket;
    public User user;
    public Game game;
//    public BracketMethods treeMethodAccess;
//    public BracketMethods bracketMethods;
    public AdvanceTeamOB outputBoundary;
    public AdvanceTeamGateway gateway;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;

    /**
     * Construct an AdvanceTeamUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * //@param bracketRepo    The BracketRepo to use
     * @param gateway        The gateway to use
     * @param outputBoundary The output boundary to use
     * @param username         The ID of the user who is advancing the team
     * @param bracketID      The ID of the bracket the user is advancing the team in
     */

//    public AdvanceTeamUC(AdvanceTeamOB outputBoundary, AdvanceTeamGateway gateway,
//                         BracketRepo bracketRepo, AccountRepo accountRepo, int bracketID, String username) {
//        this.outputBoundary = outputBoundary;
//        this.gateway = gateway;
//        this.bracketRepo = bracketRepo;
//        this.bracket = bracketRepo.getBracket(bracketID);
//        this.user = accountRepo.getUser(username);
//        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
////        this.treeMethodAccess = new BracketMethods(bracketType);
//        this.bracketMethods = new DefaultBracketMethods((DefaultBracket) bracket); //possibly changing
//    }

    public AdvanceTeamUC(AdvanceTeamOB outputBoundary, AdvanceTeamGateway gateway,
                         InformationRecord informationRecord, int bracketID, String username) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        this.accountRepo = informationRecord.getAccountData();
        this.bracketRepo = informationRecord.getBracketData();
        this.user = this.accountRepo.getUser(username);
//        try{
//            this.bracketRepo = (BracketRepo) bracketRepo;
//        } catch (ClassCastException e) {
//            throw new ClassCastException("bracketRepo must be of type BracketRepo");
//        }
//
//        try{
//            this.accountRepo = (AccountRepo) accountRepo;
//            this.user = this.accountRepo.getUser(username);
//        } catch (ClassCastException e) {
//            throw new ClassCastException("accountRepo must be of type AccountRepo");
//        }
        this.bracket = this.bracketRepo.getBracket(bracketID);
        //this.bracketRepo = bracketRepo;
        //this.bracket = bracketRepo.getBracket(bracketID);
        this.user = this.accountRepo.getUser(username);
        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
//        this.treeMethodAccess = new BracketMethods(bracketType);
//        this.bracketMethods = new DefaultBracketMethods((DefaultBracket) bracket); //possibly changing

//    private void findGame(int gameID, Game head) {
//        this.game = this.treeMethodAccess.findGame(gameID, head);
//    }
    }

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

    private boolean checkGame(Game game) {
        return game != null;
    }

    private boolean checkGameWinner(Game game) {
        return game.getGameStatus();
    }

//    private int getTreeHeight(Game head){
//        return this.treeMethodAccess.findHeight(head);
//    }

    private ArrayList<Game> returnLevelGames(Game head, int roundNum){
//        return this.treeMethodAccess.levelNodes(head, roundNum);
//        if (bracket instanceof DefaultBracket){
//            RoundGames<DefaultBracket> roundGames = new TreeRoundGames<>();
//            return roundGames.getGamesInRound(head, roundNum);
//        } else {
//            RoundGames<Bracket> roundGames = new GeneralisedRoundGames<>();
//            return roundGames.getGamesInRound(head, roundNum);
//        }
        return bracket.getGamesInRound(roundNum);
    }

    // This method is used to insert the winning team into the next game in the bracket
    private Game insertTeam(Team team, Game game){
        ArrayList<Game> games = returnLevelGames(this.bracket.getFinalGame(), this.game.getGameRound() + 1);
        for (Game g : games){
            if (g.getPrevGame1().getGameID() == game.getGameID() || g.getPrevGame2().getGameID() == game.getGameID()){
                g.setTeam(team, 0);
                g.setNumTeams(g.getNumTeams() + 1); // Increases the number of teams in the game by 1
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
//        findGame(inputData.getGameIDAT(), this.bracket.getFinalGame());
//        if (bracket instanceof DefaultBracket){
//            GameFinder<DefaultBracket> gameFinder = new TreeGameFinder<>();
//            this.game = gameFinder.getGame(inputData.getGameIDAT(), this.bracket.getFinalGame());
//        } else {
//            GameFinder<Bracket> gameFinder = new GeneralisedGameFinder<>();
//            this.game = gameFinder.getGame(inputData.getGameIDAT(), this.bracket.getFinalGame());
//        }
        this.game = bracket.getGame(inputData.getGameIDAT());

//        if (this.game.getGameRound() > getTreeHeight(this.bracket.getFinalGame())) {
//            return this.outputBoundary.presentError("This game is in the final round.");
//        }
//        if (this.game.getGameRound() >= this.bracket.getFinalGame().getGameRound()) {
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

        if (!checkGame(this.game)) {
            return this.outputBoundary.presentError("This game does not exist.");
        }

        if (!checkGameWinner(this.game)) {
            return this.outputBoundary.presentError("This game has not been completed.");
        }

        Team winningTeam = this.game.getWinner();
        User winningObserver = this.game.getObserver();
        Game advancedGame = insertTeam(winningTeam, this.game);

        // Advances the observer refereeing the winning team to the next round.
//        try {
//            advancedGame.setObserver(winningObserver);
//        } catch (NullPointerException e) {
//            return this.outputBoundary.presentError("Next game does not exist.");
//        }

        // This is where we would save the bracket to the database, but we don't have a database. We save locally.
        AdvanceTeamDSID dsInputData = new AdvanceTeamDSID(this.bracketRepo);
        try {
            this.gateway.save(dsInputData);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the information.");
        }

        ArrayList<Team> teams = advancedGame.getTeams();

        BundleBracketData bundleBracketData = new BundleBracketData();
        bundleBracketData.bundleBracket(this.bracket);
        LinkedHashMap<Integer, ArrayList<String>> gameTeamMap =  bundleBracketData.getGameToTeams();
        LinkedHashMap<Integer, ArrayList<Integer>> gameScoresMap = bundleBracketData.getGameToScores();

//        ArrayList<String> teamNames = new ArrayList<>(Arrays.asList("", ""));
//        for (Team team : teams) {
//            if (team != null) {
//                teamNames.set(teams.indexOf(team), team.getTeamName());
//            }
//        }

        AdvanceTeamOD outputData = new AdvanceTeamOD(advancedGame.getGameID(), gameTeamMap, gameScoresMap);
        return this.outputBoundary.presentSuccess(outputData);
    }
}
