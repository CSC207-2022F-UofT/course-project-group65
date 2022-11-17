package useCases.joinTeam;
import entities .*;
import java.util.List;

public class joinTeamOD {
    private String success;
    private String username;
    private List<User> teamMembers;
    private Bracket updatedBracket;

    public joinTeamOD(String success, String username, List<User> teamMembers, Bracket updatedBracket){
        this.success = success;
        this.username = username;
        this.teamMembers = teamMembers;
        this.updatedBracket = updatedBracket;
    }

    public String getSuccess(){
        return this.success;
    }

    public void setSuccess(String success){
        this.success = success;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public List<User> getTeamMembers(){
        return teamMembers;
    }
    public void setTeamMembers(List<User> teamMembers){
        this.teamMembers = teamMembers;
    }

    public Bracket getUpdatedBracket(){
        return this.updatedBracket;
    }

    public void setUpdatedBracket(Bracket updatedBracket){
        this.updatedBracket = updatedBracket;
    }
}
