package useCases.createBracket;

import entities.DefaultBracket;
import entities.Game;
import entities.Team;
import entities.User;

public class DefaultBracketBuilder implements BracketBuilder {
    /*
     * This is a class for creating a default bracket.
     */
    private DefaultBracket bracket;

    public DefaultBracketBuilder() {
        this.bracket = new DefaultBracket();
    }

    public void setName(String tournamentName) {
        this.bracket.setTournamentName(tournamentName);
    }

    public void setID(int tournamentID) {
        this.bracket.setTournamentID(tournamentID);
    }

    public void buildTeams(int numTeams) {
        TeamFactory teamFactory = new DefaultTeamFactory();
        for (int i = 0; i < numTeams; i++) {
            Team team = teamFactory.getTeam("Default");
            this.bracket.addTeam(team);
        }
    }

    public void setMaxTeamSize(int maxTeamSize) {
        this.bracket.setTeamSize(maxTeamSize);
        for (Team team : this.bracket.getTeams()) {
            team.setTeamSize(maxTeamSize);
        }
    }

    public void buildGames(int numTeams) {
        GamesFactory gamesFactory = new DefaultGamesFactory();
        Game finalGame = gamesFactory.getGames(numTeams, this.bracket.getTeams());
        this.bracket.setFinalGame(finalGame);
    }

    public void addOverseer(User overseer, int tournamentID) {
        overseer.setBracketRole(tournamentID, "Overseer");
        bracket.addReferee(overseer);
    }

    public void setWinCon(int winCondition) {
        bracket.setWinCondition(winCondition);
    }

    public DefaultBracket getBracket() {
        return bracket;
    }

}
