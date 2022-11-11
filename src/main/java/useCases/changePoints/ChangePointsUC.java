package useCases.changePoints;

import entities.Bracket;
import entities.User;
import entities.Team;
import entities.Game;
import useCases.generalInterfaces.CheckUserPermissionIF;
import java.util.List;
import java.util.ArrayList;
import entities.AccountRepo;
import entities.BracketRepo;


public class ChangePointsUC implements CheckUserPermissionIF{
    public Bracket bracket;
    public int newPoints;
    public Team team;
    public User user;
    public String username;
    public Game game;
    public int bracketID;
    public int gameID;


    public void changePoints(int bracketID, Team team, String username, int gameID, int points) {
        this.newPoints = points;
        this.team = team;
        this.username = username;
        this.bracketID = bracketID;
        this.gameID = gameID;
        }

    public void findUser(AccountRepo accountRepo) {
        this.user = accountRepo.getUser(this.username);
    }

    public void findBracket(BracketRepo bracketRepo) {
        this.bracket = bracketRepo.getBracket(this.bracketID);
    }

    public void findGame(int gameID, Game head) {
        if (head == null) {
            return;
        } else if (head.getGameID() == gameID) {
            this.game = head;
        } else {
            findGame(gameID, head.getPrevGame1());
            findGame(gameID, head.getPrevGame2());
        }
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
        List<Team> teams = this.bracket.getTeams();
        return teams.contains(team);
    }
    public boolean checkGame(Game game) {
        return this.game != null;
    }

    public List<Game> returnLevelGames(Game head, int roundNum){
        List<Game> games = new ArrayList<>();
        if (head == null) {
            return games;
        } else if (head.getPrevGame1() == null && head.getPrevGame2() == null) {
            if (head.getGameRound() == roundNum) {
                games.add(head);
            }
            return games;
        } else {
            if (head.getGameRound() == roundNum) {
                games.add(head);
            }
            games.addAll(returnLevelGames(head.getPrevGame1(), roundNum));
            games.addAll(returnLevelGames(head.getPrevGame2(), roundNum));
            return games;
        }
    }

    public boolean checkAllGamesFull(Game game){
        int teamRound = game.getGameRound();
        List<Game> games = returnLevelGames(this.bracket.getFinalGame(), teamRound);
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
