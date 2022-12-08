package use_cases.log_in;
/**
 * This is a class representing the input data from the user when logging in.
 */
public class LogInID {
    private final String username;
    private final String password;

    public LogInID(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}
