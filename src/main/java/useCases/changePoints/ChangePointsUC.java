package useCases.changePoints;

import entities.*;
import useCases.generalClasses.traversalStrategies.TreeMethods;

import java.util.ArrayList;


public class ChangePointsUC implements ChangePointsIB{
    public Bracket bracket;
    public int newPoints;
    public Team team;
    public User user;
    public Game game;
    public TreeMethods treeMethodAccess;
    public ChangePointsOB outputBoundary;

    public ChangePointsUC(ChangePointsOB outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void findUser(ChangePointsID inputData){
        this.user = inputData.getUserCP();
    }

    public void findBracket(ChangePointsID inputData) {
        this.bracket = inputData.getBracketCP();
        String bracketType = "Default"; // This can be changed later to accomodate different types of brackets
        this.treeMethodAccess = new TreeMethods(bracketType);
        findGame(inputData.getGameIDCP(), this.bracket.getFinalGame());
    }

    public void findGame(int gameID, Game head) {
        this.game = this.treeMethodAccess.findGame(gameID, head);
    }

    public boolean checkUserPermission(User user) {
        String userRole = user.getBracketRole(this.bracket.getTournamentID());
        User assignedObserver = this.game.getObserver();
        String username = user.getUsername();
        String assignedObserverUsername = assignedObserver.getUsername();

        if (userRole.equals("Overseer")) {
            return true;
        } else if (userRole.equals("Observer")) {
            return username.equals(assignedObserverUsername);
        } else {
            return false;
        }
    }

    public boolean checkTeam(Team team) {
        ArrayList<Team> teams = (ArrayList<Team>) this.bracket.getTeams();
        return teams.contains(team);
    }

    public boolean checkGame(Game game) {
        return this.game != null;
    }

    public ArrayList<Game> returnLevelGames(Game head, int roundNum){
        return this.treeMethodAccess.levelNodes(head, roundNum);
    }

    public boolean checkAllGamesFull(Game game){
        int teamRound = game.getGameRound();
        ArrayList<Game> games = returnLevelGames(this.bracket.getFinalGame(), teamRound);
        for (Game g: games){
            if (g.getNumTeams() < 2){
                return false;
            }
        }
        return true;
    }

    public boolean validPoints(Game game, int points){
        int changedPoints = points + this.game.getTeamPoints(this.team);
        // Adding an additional condition that adding points is valid if there isn't a team that has already won
        // There is one issue with this type of implementation. The game being finished is not the same as only one
        // team matching the win condition. So we could still end up in a scenario where there are two teams matching
        // the win condition.
        if (!game.getGameStatus()) {
            return changedPoints <= this.bracket.getWinCondition() && changedPoints >= 0;
        } else {
            return false;
        }
    }

    // This is the main function. Each of the previous methods can be thought of as "helper" methods so that I
    // have all the requisite information to run this method. This method is the one that actually changes the points.

    public ChangePointsOD changePoints(ChangePointsID inputData) {
        findUser(inputData);
        findBracket(inputData);
        this.newPoints = inputData.getNewPointsCP();
        this.team = inputData.getTeamCP();

        if (!checkUserPermission(this.user)) {
            return this.outputBoundary.presentError("You do not have permission to change points.");
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
        ChangePointsOD outputData = new ChangePointsOD(this.game, this.team, this.game.getTeamPoints(this.team),
                this.bracket);
        return this.outputBoundary.presentSuccess(outputData);
    }
}
