package use_cases.create_account;
/**
 * This class represents the output data for the CreateAccount use case.
 * This data is used in updating/creating the view after the account has been
 * successfully created.
 */
public class CreateAccountOD {
    private final String USERNAME;
    private final String PASSWORD;

    public CreateAccountOD(String username, String password) {
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public String getUsername() { return USERNAME; }

    public String getPassword() { return PASSWORD; }
}
