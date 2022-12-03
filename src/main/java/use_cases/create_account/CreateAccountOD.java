package use_cases.create_account;
/**
 * This class represents the output data for the CreateAccount use case.
 * This data is used in updating/creating the view after the account has been
 * successfully created.
 */
public class CreateAccountOD {
    private String username;
    private String password;

    public CreateAccountOD(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
