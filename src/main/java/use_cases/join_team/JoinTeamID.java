package use_cases.join_team;

/**
 * The data class for the join team input data.
 */
public class JoinTeamID {
    /**
     * The name of the team to join
     */
    private String teamName;

    /**
     * Constructor for the JoinTeamID class
     *
     * @param teamName - the input team name from the user chose.
     */
    public JoinTeamID(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Return the TeamName which the user wants to join
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * The setter for the TeamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
