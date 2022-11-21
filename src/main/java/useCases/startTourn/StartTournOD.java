package useCases.startTourn;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;

import java.util.ArrayList;

/**
 * The output data class for the start tournament output boundary.
 */
public class StartTournOD {
    private ArrayList<String> errors;
    public StartTournOD(ArrayList<String> errors) {
        this.errors = errors;
    }

    public ArrayList<String> getErrors() {
        return this.errors;
    }
}
