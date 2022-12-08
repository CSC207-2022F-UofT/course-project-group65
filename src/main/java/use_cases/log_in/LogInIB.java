package use_cases.log_in;
/** An interface that allows for user input for the LogIn use case.
 * This interface is used for dependency inversion, allowing the use case to be independent of the view.
 */
public interface LogInIB {
    LogInOD logIn(LogInID requestModel);
}
