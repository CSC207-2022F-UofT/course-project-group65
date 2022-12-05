package interface_adapters.end_tournament;

/**
 * A class for exception to the end tournament use case.
 */
public class EndTournFailed extends RuntimeException {
    /**
     * Prepares the fail view if the use case has been failed
     *
     * @param error The string error message
     */
    public EndTournFailed(String error) {
        super(error);
    }
}
