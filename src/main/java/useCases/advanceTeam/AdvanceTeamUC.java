package useCases.advanceTeam;

import com.sun.source.tree.Tree;
import entities.*;
import useCases.generalInterfaces.CheckUserPermissionIF;
import useCases.generalClasses.*;

import java.util.ArrayList;

public class AdvanceTeamUC implements CheckUserPermissionIF {

    public Bracket bracket;
    public User user;
    public String username;
    public Game game;
    public int bracketID, gameID;
    public TreeMethods treeMethodAccess;

    public void advanceTeam(int bracketID, String username, int gameID) {
        this.username = username;
        this.bracketID = bracketID;
        this.gameID = gameID;
    }
    public void findUser(AccountRepo accountRepo) {
        this.user = accountRepo.getUser(username);
    }

    // This method finds the bracket, and the game in the bracket using the treeMethods class
    public void findBracket(BracketRepo bracketRepo) {
        this.bracket = bracketRepo.getBracket(this.bracketID);
        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
        this.treeMethodAccess = new TreeMethods(bracketType);
        findGame(this.bracketID, this.bracket.getFinalGame()); //I NEED TO ADD THIS TO ALL OF MY PERSONAL USECASES
    }

    // Quick testing of base case.
//    public static void main(String[] args) {
//        AdvanceTeamUC advanceTeamUC = new AdvanceTeamUC();
//        advanceTeamUC.advanceTeam(0, "user1", 1);
//        DefaultGame game = new DefaultGame();
//        BracketRepo bracketRepo = new BracketRepo();
//        DefaultBracket bracket = new DefaultBracket();
//        bracket.setFinalGame(game);
//        bracketRepo.addBracket(bracket);
//        System.out.println(bracketRepo.getBracket(0));
//
//        advanceTeamUC.findBracket(bracketRepo);
//        System.out.println(advanceTeamUC.bracket);
//        System.out.println(advanceTeamUC.game);
//    }

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

    public void insertTeam(Team team, Game game){
        // We are inserting the team to the round immediately after the round the game is in. That is, the current
        // round plus 1 - rounds are counted backwards in the tree.
        ArrayList<Game> games = returnLevelGames(game, this.game.getGameRound() + 1);
        for (Game g : games){
            // Find the node in tree s.t. its previous node(1/2) == game. Insert team into that node.
            if (g.getPrevGame1().getGameID() == game.getGameID() || g.getPrevGame2().getGameID() == game.getGameID()){
                g.setTeam(team, 0);
            }
        }
    }


    public boolean advanceWinner(){
        // You cannot advance a team from the final.
        if (this.game.getGameRound() + 1 >= getTreeHeight(this.game)){
            return false;
        }
        // Standard checks
        if (!checkUserPermission(this.user) || !checkGame(this.game) || !checkGameWinner(this.game)) {
            return false;
        }
        Team winner = this.game.getWinner();
        insertTeam(winner, this.game);
        return true;
    }

}
