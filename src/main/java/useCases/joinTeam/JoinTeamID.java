package useCases.joinTeam;

public class JoinTeamID {
    /*
    The data class for the join team input data.
     */
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
