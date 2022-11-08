package useCases.changePoints;

//import entities.Bracket, entities.User, entities.Team;
import useCases.generalInterfaces.CheckUserPermissionIF;

public class ChangePointsUC implements CheckUserPermissionIF{
    public Bracket bracket;
    public int points;
    public Team team;
    public User user;
    public Game game;
    /**
     * Change the points that a team has in a bracket.
     *
     * @param bracket Bracket that will be changed
     * @param team  Team that will have points changed
     * @param user  User that will be changing the points
     * @param points  Points that will be changed
     */
    public void changePoints(Bracket bracket, Team team, User user, Game game, int points) {
        this.bracket = bracket;
        this.points = points;
        this.team = team;
        this.user = user;
        this.game = game;
    }

    // Assume that there is a getter for the user's role in the bracket.
    public boolean checkUserPermission(User user) {
        String userRole = user.getBracketRole(this.bracket.tournamentID);
        return userRole.equals("Overseer") || userRole.equals("Observer");
    }

    // Assume that there is a getter for the team's points in the bracket.
    public boolean checkTeam(Team team) {
        List<Team> teams = this.bracket.getTeams();
        return teams.contains(team);
    }

    public boolean checkAllGamesFull(Team team){
        int teamRound = this.game.roundNum;
        List<Game> games = traverseTreeGames(this.bracket.getFinalGame(), teamRound);
        for (Game game: games){
            if (game.getNumTeams() < 2){
                return false;
            }
        }
        return true;
    }

    public boolean validPoints(int points){
        return points >= 0;
    }

    public boolean changePoints() {
        List<Team> teams = this.game.getTeams();
        if (checkUserPermission(this.user) && checkTeam(this.team) && checkAllGamesFull(this.team) &&
                validPoints(this.points)) {
            this.game.setPoints(this.points);
            return true;
        }
        return false;
    }




}
