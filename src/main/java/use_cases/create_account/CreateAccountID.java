package use_cases.create_account;

/**
 * This is a class representing the input data from the user used in creating a new account.
 */
public class CreateAccountID {
    private final String username;
    private final String password;

    public CreateAccountID(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}
