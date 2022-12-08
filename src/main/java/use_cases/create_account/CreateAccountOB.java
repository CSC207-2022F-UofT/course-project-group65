package use_cases.create_account;
/** An interface for the output boundary of the CreateAccount use case. It is used by the presenter.
 * This interface is used for dependency inversion, allowing the use case to be independent of the view.
 */
public interface CreateAccountOB {
    CreateAccountOD prepareSuccessView(CreateAccountOD user);

    CreateAccountOD prepareFailView(String error);
}
