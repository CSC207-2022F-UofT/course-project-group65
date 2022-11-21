package screens.endTourn;

/**
 * A class for the output data of the end tournament use case.
 */
public class EndTournFailed extends RuntimeException {
    public EndTournFailed(String error) {
        super(error);
    }
}
