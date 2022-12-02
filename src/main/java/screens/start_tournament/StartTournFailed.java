package screens.start_tournament;

/**
 * A class for the output data of the start tournament use case.
 */
public class StartTournFailed extends RuntimeException {
    public StartTournFailed(String error) {
        super(error);
    }
}
