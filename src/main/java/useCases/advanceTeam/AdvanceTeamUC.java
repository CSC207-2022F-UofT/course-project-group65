package useCases.advanceTeam;
import entities.*;
import useCases.generalInterfaces.CheckUserPermissionIF;
import useCases.generalClasses.*;

import java.util.ArrayList;

public class AdvanceTeamUC implements CheckUserPermissionIF, AdvanceTeamIB {

    public Bracket bracket;
    public User user;
    public Game game;
    public TreeMethods treeMethodAccess;
    public AdvanceTeamOB outputBoundary;

    public AdvanceTeamUC(AdvanceTeamOB outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    public void findUser(AdvanceTeamID inputData){
        this.user = inputData.getUser();
    }

    // This method finds the bracket, and the game in the bracket using the treeMethods class
    public void findBracket(AdvanceTeamID inputData) {
        this.bracket = inputData.getBracket();
        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
        this.treeMethodAccess = new TreeMethods(bracketType);
        findGame(inputData.getGameID(), this.bracket.getFinalGame()); //I NEED TO ADD THIS TO ALL OF MY PERSONAL USECASES

    }

    public void findGame(int gameID, Game head) {
        this.game = this.treeMethodAccess.findGame(gameID, head);
    }


    public boolean checkUserPermission(User user) {
        String userRole = user.getBracketRole(this.bracket.getTournamentID());
        User assignedObserver = this.game.getObserver();
        if (userRole.equals("Overseer")) {
            return true;
        } else if (userRole.equals("Observer")) {
            return user.getUsername().equals(assignedObserver.getUsername());
        } else {
            return false;
        }
    }

    public boolean checkGame(Game game) {
        return this.game != null;
    }

    public boolean checkGameWinner(Game game) {
        return game.getGameStatus();
    }

    public int getTreeHeight(Game head){
        return this.treeMethodAccess.findHeight(head);
    }

    public ArrayList<Game> returnLevelGames(Game head, int roundNum){
        return this.treeMethodAccess.levelNodes(head, roundNum);
    }

    public Game insertTeam(Team team, Game game){
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

    // This is the method that runs the use case.
    public AdvanceTeamOD advanceWinner(AdvanceTeamID inputData) {
        findUser(inputData);
        findBracket(inputData);
        if (this.game.getGameRound() + 1 >= getTreeHeight(this.game)) {
            return this.outputBoundary.presentError("This game is in the final round.");
        }

        if (!checkUserPermission(this.user)) {
            return this.outputBoundary.presentError("You do not have permission to advance this team.");
        }

        if (!checkGame(this.game)) {
            return this.outputBoundary.presentError("This game does not exist.");
        }

        if (!checkGameWinner(this.game)) {
            return this.outputBoundary.presentError("This game has not been completed.");
        }

        Team winningTeam = this.game.getWinner();
        Game advancedGame = insertTeam(winningTeam, this.game);

        AdvanceTeamOD outputData = new AdvanceTeamOD(this.bracket, advancedGame, winningTeam);
        return this.outputBoundary.presentSuccess(outputData);
    }

}
