package useCases.createBracket;

import entities.Bracket;
import entities.User;

/**
 * This is a class for assembling a bracket. It makes use of the bracket builder.
 * It is used by the create bracket use case and is part of the Builder Design Pattern.
 */

public class BracketAssembler {
    /** The bracket builder used to create the bracket */
    private BracketBuilder builder;

    /** Determines which bracket builder to use based on the bracket type
     * Currently, only single elimination is supported (Default) but this class is open to
     * extension thanks to the Builder Design pattern.
     * @param bracketType - determines which builder is used, currently only supports "Default"
     * @param creator - the User who is creating this bracket, used to assign the Overseer
     * @param bracketName - the name of the bracket
     * @param tournamentID - the ID of the bracket, this value is unique to each bracket
     * @param numTeams - the number of teams in the bracket
     * @param maxTeamSize - the maximum number of players per team
     * @param winCondition - the number of wins required to win the bracket
     */
    public void assembleBracket(String bracketType, User creator, String bracketName,
                      int tournamentID, int numTeams, int maxTeamSize, int winCondition) {
        if (bracketType.equals("Default")) {
            this.builder = new DefaultBracketBuilder();
        }

        if (this.builder != null) {
            buildBracket(creator, bracketName, tournamentID, numTeams, maxTeamSize, winCondition);
        }
    }

    /** Builds the bracket using the builder (needs to be called after this.builder has been assigned)
     * @param creator - the User who is creating this bracket, used to assign the Overseer
     * @param bracketName - the name of the bracket
     * @param tournamentID - the ID of the bracket, this value is unique to each bracket
     * @param numTeams - the number of teams in the bracket
     * @param maxTeamSize - the maximum number of players per team
     * @param winCondition - the number of wins required to win the bracket
     */
    public void buildBracket(User creator, String bracketName, int tournamentID, int numTeams, int maxTeamSize, int winCondition) {
        this.builder.setName(bracketName);
        this.builder.setID(tournamentID);
        this.builder.buildTeams(numTeams);
        this.builder.setMaxTeamSize(maxTeamSize);
        this.builder.buildGames(numTeams);
        this.builder.addOverseer(creator, tournamentID);
        this.builder.setWinCon(winCondition);
        this.builder.setPlayerInvite();
        this.builder.setObserverInvite();
    }

    /** Returns the bracket that was built (needs to be called after buildBracket)
     * @return the bracket that was built, based on the users specification
     */
    public Bracket getBracket() {
        return builder.getBracket();
    }
}
