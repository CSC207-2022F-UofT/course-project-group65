package screens.change_points;

public class ChangePointsFailed extends RuntimeException {
    public ChangePointsFailed(String errorMessage) {
        super(errorMessage);
    }
}
