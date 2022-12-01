package useCases.teamCreation;

/**
 * the input data in teamCreation use case
 */
public class teamCreationID {
    private String teamName;

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
