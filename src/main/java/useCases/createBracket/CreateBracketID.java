package useCases.createBracket;

/**
 * This is a class representing the input data from the user used in creating a new bracket.
 */
public class CreateBracketID {
    /** The type of bracket being created */
    private final String bracketType;
    /** The name of the bracket being created */
    private final String bracketName;
    /** The number of teams in the bracket being created */
    private final int numTeams;
    /** The maximum number of players per team in the bracket being created */
    private final int maxTeamSize;
    /** The number of points required to win a round in the bracket being created */
    private final int winCondition;

    /** Constructor for the CreateBracketID class
     * @param bracketType - the type of bracket being created
     * @param bracketName - the name of the bracket being created
     * @param numTeams - the number of teams in the bracket being created
     * @param maxTeamSize - the maximum number of players per team in the bracket being created
     * @param winCondition - the number of points required to win a round in the bracket being created
     */
    public CreateBracketID(String bracketType, String bracketName, int numTeams, int maxTeamSize, int winCondition) {
        this.bracketType = bracketType;
        this.bracketName = bracketName;
        this.numTeams = numTeams;
        this.maxTeamSize = maxTeamSize;
        this.winCondition = winCondition;
    }

    /** Returns the type of bracket being created
     * @return the type of bracket being created
     */
    public String getBracketType() {
        return bracketType;
    }

    /** Returns the name of the bracket being created
     * @return the name of the bracket being created
     */
    public String getBracketName() {
        return bracketName;
    }

    /** Returns the number of teams in the bracket being created
     * @return the number of teams in the bracket being created
     */
    public int getNumTeams() {
        return numTeams;
    }

    /** Returns the maximum number of players per team in the bracket being created
     * @return the maximum number of players per team in the bracket being created
     */
    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    /** Returns the number of points required to win a round in the bracket being created
     * @return the number of points required to win a round in the bracket being created
     */
    public int getWinCondition() {
        return winCondition;
    }
}
