package interface_adapters.declare_winner;

/** Defines an exception that occurs when a winner cannot be declared. */
public class DeclareWinnerFailed extends RuntimeException {
    public DeclareWinnerFailed(String errorMessage) {
        super(errorMessage);
    }
}
