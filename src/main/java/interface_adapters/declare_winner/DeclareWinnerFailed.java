package interface_adapters.declare_winner;

public class DeclareWinnerFailed extends RuntimeException {
    public DeclareWinnerFailed(String errorMessage) {
        super(errorMessage);
    }
}
