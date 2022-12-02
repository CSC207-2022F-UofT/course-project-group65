package use_cases.team_creation;
/**
 * This is a class representing the input data from the user to create a new team
 */
public class teamCreationID {
    /** The name of the team being created */
    private String teamName;
    /** Constructor for the teamCreationID class
     * @param teamName - the inputed team name from the creator
     */
    public teamCreationID(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }


}
