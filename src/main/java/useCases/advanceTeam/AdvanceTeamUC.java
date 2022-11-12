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
    // ^ This is actually going to be the output data (AdvanceTeamOD), but it is of type AdvanceTeamOB for CA purposes.
    public AdvanceTeamGateway gateway;

    public AdvanceTeamUC(AdvanceTeamOB outputBoundary, AdvanceTeamGateway gateway) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
    }


    // Notice how we are using the same input data class that was passed in from the controller and NOT directly
    // taking in data from main or the view. This is because we want to keep the use case independent of the
    // view and main.
    public void findUser(AdvanceTeamID inputData){
        this.user = inputData.getUserAT();
    }

    // This method finds the bracket, and the game in the bracket using the treeMethods class
    public void findBracket(AdvanceTeamID inputData) {
        this.bracket = inputData.getBracketAT();
        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
        this.treeMethodAccess = new TreeMethods(bracketType);
        findGame(inputData.getGameIDAT(), this.bracket.getFinalGame()); //I NEED TO ADD THIS TO ALL OF MY PERSONAL USECASES

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
    // If any of the checks fail, the output data will be set to null (you'll see this in the presenter) and an
    // exception with the string error message will be thrown.
    // If all the checks pass, the use case runs, and an instance of the output data is returned via the output
    // boundary - again for CA purposes.
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

        // This is where we would save the bracket to the database, but we don't have a database yet.
        AdvanceTeamDSID dsInputData = new AdvanceTeamDSID(inputData.getBracketRepoAT());
        try {
            this.gateway.save(dsInputData);
        } catch (Exception e) {
            return this.outputBoundary.presentError("There was an error saving the bracket to the database.");
        }

        AdvanceTeamOD outputData = new AdvanceTeamOD(this.bracket, advancedGame, winningTeam);
        return this.outputBoundary.presentSuccess(outputData);
    }

}
