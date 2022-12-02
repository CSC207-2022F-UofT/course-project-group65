package use_cases.start_tournament;



import java.util.ArrayList;

/**
 * The output data class for the start tournament output boundary.
 */
public class StartTournOD {
    private final ArrayList<String> errors;
    public StartTournOD(ArrayList<String> errors) {
        this.errors = errors;
    }

    public ArrayList<String> getErrors() {
        return this.errors;
    }
}
