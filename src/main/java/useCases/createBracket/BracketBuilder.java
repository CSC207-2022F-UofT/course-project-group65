package useCases.createBracket;

import entities.Bracket;
import entities.User;

/**
 * This is an interface for a bracket builder. It is used by the create bracket use case
 * and is part of the Builder Design Pattern.
 */
public interface BracketBuilder {

    /** Sets the name of the bracket
     * @param tournamentName - the name of the bracket
     */
    void setName(String tournamentName);

    /** Sets the ID of the bracket
     * @param tournamentID - the ID of the bracket, this value is unique to each bracket
     */
    void setID(int tournamentID);

    /** Builds the teams for the bracket
     * @param numTeams - the number of teams in the bracket
     */
    void buildTeams(int numTeams);

    /** Builds the games for the bracket
     * @param numTeams - the number of teams in the bracket, used to calculate the number of games
     */
    void buildGames(int numTeams);

    /** Sets the maximum number of players per team
     * @param maxTeamSize - the maximum number of players per team
     */
    void setMaxTeamSize(int maxTeamSize);

    /** Adds the overseer to the bracket
     * @param overseer - the User who is creating this bracket, they are assigned as the Overseer
     * @param tournamentID - the ID of the bracket, this value is unique to each bracket
     */
    void addOverseer(User overseer, int tournamentID);

    /** Sets the win condition for the bracket
     * @param winCondition - the number of wins required to win the bracket
     */
    void setWinCon(int winCondition);

    /** Sets the player invite for the bracket. This is the invite that players use to join the bracket
     */
    void setPlayerInvite();

    /** Sets the observer invite for the bracket. This is the invite that observers use to join the bracket
     */
    void setObserverInvite();

    /** Returns the bracket that was built
     */
    Bracket getBracket();

}
