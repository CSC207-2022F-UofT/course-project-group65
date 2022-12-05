package interface_adapters.start_tournament;

/**
 * A class for the fail view of the start tournament use case.
 */
public class StartTournFailed extends RuntimeException {
    /**
     * Prepares the fail view if the use case has been failed
     *
     * @param error The string error message
     */
    public StartTournFailed(String error) {
        super(error);
    }
}
