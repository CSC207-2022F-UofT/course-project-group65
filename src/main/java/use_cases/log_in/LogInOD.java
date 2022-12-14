package use_cases.log_in;

/**
 * This class represents the output data for the LogIn use case.
 * This data is used in updating/creating the view after the user has successfully logged in.
 */
public class LogInOD {
    private final String username;

    public LogInOD(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }
}
