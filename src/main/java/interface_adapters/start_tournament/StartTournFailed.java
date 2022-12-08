package interface_adapters.start_tournament;

/**
 * A class for exception to the start tournament use case.
 */
public class StartTournFailed extends RuntimeException {
    /**
     * Prepares exception if the use case has been failed
     *
     * @param error The string error message
     */
    public StartTournFailed(String error) {
        super(error);
    }
}
