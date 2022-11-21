package screens.teamCreation;

public class TeamCreationFailed extends RuntimeException{
    public TeamCreationFailed(String error){
        super(error);
    }
}
