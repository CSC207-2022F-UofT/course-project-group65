package screens.changePoints;

public class ChangePointsFailed extends RuntimeException {
    public ChangePointsFailed(String errorMessage) {
        super(errorMessage);
    }
}
