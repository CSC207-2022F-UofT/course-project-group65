package interface_adapters.change_points;

/** Defining an exception for when the change points fails. */
public class ChangePointsFailed extends RuntimeException {
    public ChangePointsFailed(String errorMessage) {
        super(errorMessage);
    }
}
