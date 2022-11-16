package useCases.advanceTeam;
import entities.*;
import useCases.generalClasses.permRestrictionStrategies.PermissionChecker;
import useCases.generalClasses.traversalStrategies.TreeMethods;
import java.util.ArrayList;
import java.util.Arrays;

public class AdvanceTeamUC implements AdvanceTeamIB {

    public Bracket bracket;
    public User user;
    public Game game;
    public TreeMethods treeMethodAccess;
    public AdvanceTeamOB outputBoundary;
    public AdvanceTeamGateway gateway;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;

    /**
     * Construct an AdvanceTeamUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param bracketRepo    The BracketRepo to use
     * @param accountRepo    The AccountRepo to use
     * @param gateway        The gateway to use
     * @param outputBoundary The output boundary to use
     */

    public AdvanceTeamUC(AdvanceTeamOB outputBoundary, AdvanceTeamGateway gateway,
                         BracketRepo bracketRepo, AccountRepo accountRepo) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

    private void findUser(AdvanceTeamID inputData){
        this.user = this.accountRepo.getUser(inputData.getUsernameAT());
    }

    private void findBracket(AdvanceTeamID inputData) {
        int bracketID = inputData.getBracketIDAT();
        this.bracket = this.bracketRepo.getBracket(bracketID);
        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
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
        if (user.getBracketRole(this.bracket.getTournamentID()).equals("Observer")) {
            return assignedObserver.getUsername().equals(user.getUsername());
        }
        return true;
    }

    private boolean checkGame(Game game) {
        return game != null;
    }

    private boolean checkGameWinner(Game game) {
        return game.getGameStatus();
    }

    private int getTreeHeight(Game head){
        return this.treeMethodAccess.findHeight(head);
    }

    private ArrayList<Game> returnLevelGames(Game head, int roundNum){
        return this.treeMethodAccess.levelNodes(head, roundNum);
    }

    // This method is used to insert the winning team into the next game in the bracket
    private Game insertTeam(Team team, Game game){
        // We are inserting the team to the round immediately after the round the game is in. That is, the current
        // round plus 1 - rounds are counted backwards in the tree.
        ArrayList<Game> games = returnLevelGames(game, this.game.getGameRound() + 1);
        for (Game g : games){
            // Find the node in tree s.t. its previous node(1/2) == game. Insert team into that node.
            if (g.getPrevGame1().getGameID() == game.getGameID() || g.getPrevGame2().getGameID() == game.getGameID()){
                g.setTeam(team, 0);
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
        findUser(inputData);
        findBracket(inputData);
        findGame(inputData.getGameIDAT(), this.bracket.getFinalGame());
        if (this.game.getGameRound() + 1 >= getTreeHeight(this.game)) {
            return this.outputBoundary.presentError("This game is in the final round.");
        }

        if (!checkUserPermission(this.user)) {
            return this.outputBoundary.presentError("You do not have permission to advance this team.");
        }

        if (!checkObserverAssigned(this.user)) {
            return this.outputBoundary.presentError("You are not assigned to this game.");
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
        try {
            advancedGame.setObserver(winningObserver);
        } catch (NullPointerException e) {
            return this.outputBoundary.presentError("Next game does not exist.");
        }

        // This is where we would save the bracket to the database, but we don't have a database. We save locally.
        AdvanceTeamDSID dsInputData = new AdvanceTeamDSID(this.bracketRepo);
        try {
            this.gateway.save(dsInputData);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the information.");
        }

        AdvanceTeamOD outputData = new AdvanceTeamOD(this.bracket, advancedGame, winningTeam);
        return this.outputBoundary.presentSuccess(outputData);
    }
}
