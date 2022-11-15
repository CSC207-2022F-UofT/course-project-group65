package useCases.teamCreation;

public class teamCreationID {
    private String teamName;
    private int teamSize;

    public teamCreationID(String teamName, int teamSize) {
        this.teamName = teamName;
        this.teamSize = teamSize;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public int getTeamSize(){
        return this.teamSize;
    }

    public void setTeamSize(int teamSize){
        this.teamSize = teamSize;
    }

}
