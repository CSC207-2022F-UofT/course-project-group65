package useCases.teamCreation;
import entities .*;

public class teamCreationOD {
    private String success;

    private Bracket updatedBracket;

    public teamCreationOD(Bracket updatedBracket, String success){
        this.success = success;
        this.updatedBracket = updatedBracket;
    }

    public String getSuccess(){
        return this.success;
    }

    public void setSuccess(String success){
        this.success = success;
    }

    public Bracket getUpdatedBracket(){
        return this.updatedBracket;
    }

    public void setUpdatedBracket(Bracket updatedBracket){
        this.updatedBracket = updatedBracket;
    }
}
