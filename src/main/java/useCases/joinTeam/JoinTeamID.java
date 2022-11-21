package useCases.joinTeam;

/**
 The data class for the join team input data.
 */
public class JoinTeamID {
    private String teamName;

    public JoinTeamID(String teamName){
        this.teamName = teamName;
    }


    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public String getTeamName(){
        return this.teamName;
    }
}
