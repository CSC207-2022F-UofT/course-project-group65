package use_cases.log_in;

/** An interface for the output boundary of the LogIn use case. It is used by the presenter.
 * This interface is used for dependency inversion, allowing the use case to be independent of the view.
 */

public interface LogInOB {
    LogInOD prepareSuccessView(LogInOD user);

    LogInOD prepareFailView(String error);
}
