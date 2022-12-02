package useCases.createBracket;

/** An interface for the output boundary of the create bracket use case. It is used by the presenter.
 * This interface is used for dependency inversion, allowing the use case to be independent of the view.
 */
public interface CreateBracketOB {

    /** Returns the output data from the create bracket use case, used by the presenter
     * @return the output data from the create bracket use case (new bracket and account info)
     */
    CreateBracketOD presentSuccess(CreateBracketOD viewData);

    /** Returns an error if there was a problem with creating the bracket
     * @return the error from the create bracket use case (error message)
     */
    CreateBracketOD presentError(String error);
}
