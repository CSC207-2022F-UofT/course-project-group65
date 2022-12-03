package use_cases.create_account;
/** An interface that allows for user input for the CreateAccount use case.
 * This interface is used for dependency inversion, allowing the use case to be independent of the view.
 */
public interface CreateAccountIB {
    CreateAccountOD create(CreateAccountID requestModel);
}
