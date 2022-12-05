package use_cases.create_account;

/**
 * This is a class representing the input data from the user used in creating a new account.
 */
public class CreateAccountID {
    private final String USERNAME;
    private final String PASSWORD;

    public CreateAccountID(String username, String password) {
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public String getUsername() { return USERNAME; }

    public String getPassword() { return PASSWORD; }
}
