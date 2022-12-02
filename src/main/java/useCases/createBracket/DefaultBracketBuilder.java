package useCases.createBracket;

import entities.DefaultBracket;
import entities.Game;
import entities.Team;
import entities.User;

/**
 * This class is a builder for creating the default bracket in our program (single elimination).
 * It is part of the builder design pattern.
 * It is used in the assembler class and is used depending on which type of bracket the user wants to create.
 * (As of now, the only type of bracket that can be created is the default bracket).
 */
public class DefaultBracketBuilder implements BracketBuilder {
    /** The bracket that is being built */
    private final DefaultBracket bracket;

    /**
     * Creates a new DefaultBracketBuilder object. Initializing a new instance of the DefaultBracket entity.
     */
    public DefaultBracketBuilder() {
        this.bracket = new DefaultBracket();
    }

    /** Sets the name of the bracket.
     * @param tournamentName - the name of the bracket
     */
    public void setName(String tournamentName) {
        this.bracket.setTournamentName(tournamentName);
    }

    /** Sets the unique ID of the bracket.
     * @param tournamentID - the unique ID of the bracket
     */
    public void setID(int tournamentID) {
        this.bracket.setTournamentID(tournamentID);
    }

    /** Calls the TeamFactory to build the teams for the bracket.
     * In our program, all the "teams" are created at the start and are updated when
     * a user creates a new team.
     * (Start off blank/default updated based on user (Player's) input).
     * Currently, we only support one type of team, but because of the factory design pattern,
     * we can easily add more types of teams.
     * @param numTeams - the number of teams in the bracket, like Java's default variables.)
     */
    public void buildTeams(int numTeams) {
        TeamFactory teamFactory = new DefaultTeamFactory();
        for (int i = 0; i < numTeams; i++) {
            Team team = teamFactory.getTeam("Default");
            this.bracket.addTeam(team);
        }
    }

    /** Sets the maximum number of players in each team.
     * This information is updated for the bracket and each game.
     * @param maxTeamSize - the maximum number of players in each team
     */
    public void setMaxTeamSize(int maxTeamSize) {
        this.bracket.setTeamSize(maxTeamSize);
        for (Team team : this.bracket.getTeams()) {
            team.setTeamSize(maxTeamSize);
        }
    }

    /** Calls the GameFactory to build the games for the bracket.
     * In our program, all the games are created at the start and are updated when
     * the Overseer/Observer update information about the game.
     * (Start off blank/default updated based on user (Overseer/Observer) input.
     * Ex: increasing points, declaring a winner, etc.).
     * @param numTeams- the number of teams in the bracket, used to determine the number of games.
     */
    public void buildGames(int numTeams) {
        GamesFactory gamesFactory = new DefaultGamesFactory();
        Game finalGame = gamesFactory.getGames(numTeams, this.bracket.getTeams());
        this.bracket.setFinalGame(finalGame);
    }

    /** Sets the user who created the bracket as the Overseer of the tournament.
     * Additionally, adds them to the list of referees
     * for the bracket (Includes observers and overseers).
     * @param overseer - the user who created the bracket
     * @param tournamentID - the unique ID of the bracket which the user created
     */
    public void addOverseer(User overseer, int tournamentID) {
        overseer.setBracketRole(tournamentID, "Overseer");
        bracket.addReferee(overseer);
        overseer.setCurrentTournament(tournamentID);
    }

    /** Sets the win condition for the bracket. This is the number of points needed to win a game.
     * @param winCondition - the number of points needed to win a game
     */
    public void setWinCon(int winCondition) {
        bracket.setWinCondition(winCondition);
    }

    /** Sets the unique Player invite code for the bracket.
     * This is what players use to join the bracket.
     */
    public void setPlayerInvite() {
        bracket.setPlayerInvite();
    }

    /** Sets the unique Observer invite code for the bracket.
     * This is what observers use to join the bracket.
     */
    public void setObserverInvite() {
        bracket.setObserverInvite();
    }

    /** Returns the bracket that was built.
     * @return the bracket that was built
     */
    public DefaultBracket getBracket() {
        return bracket;
    }

}
