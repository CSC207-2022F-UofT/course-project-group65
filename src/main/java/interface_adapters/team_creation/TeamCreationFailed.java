package interface_adapters.team_creation;
/**
 * The class that prepares failed view for teamCreation if a new team is not successfully created.
 */
public class TeamCreationFailed extends RuntimeException{
    public TeamCreationFailed(String error){
        super(error);
    }
}
