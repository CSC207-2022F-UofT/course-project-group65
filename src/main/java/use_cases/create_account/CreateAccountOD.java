package use_cases.create_account;
/**
 * This class represents the output data for the CreateAccount use case.
 * This data is used in updating/creating the view after the account has been
 * successfully created.
 */
public class CreateAccountOD {
    private final String username;
    private final String password;

    public CreateAccountOD(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}
