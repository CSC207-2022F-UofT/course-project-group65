package useCases.changePoints;

import entities.*;
import useCases.advanceTeam.AdvanceTeamID;
import useCases.generalInterfaces.CheckUserPermissionIF;
import java.util.ArrayList;
import useCases.generalClasses.*;



public class ChangePointsUC implements CheckUserPermissionIF{
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


    // Assume that there is a getter for the team's points in the bracket.
    // Make helpers private.
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
            // See if we can generalise this. This restricts us to only 2 teams per game.
            if (g.getNumTeams() < 2){ // compare against maxTeamSize instead of 2
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
    public boolean changePoints() {
        //List<Team> teams = this.game.getTeams();
        int prevPoints = this.game.getTeamPoints(this.team);
        if (checkUserPermission(this.user) && checkTeam(this.team) && checkAllGamesFull(this.game) &&
                validPoints(this.game, this.newPoints) && checkGame(this.game)) {
            this.game.setTeam(this.team, this.newPoints + prevPoints);
            return true;
        }
        // Think about throwing an exception here
        // Have to let the user know the reason that their request was not fulfilled.
        return false;
    }




}
