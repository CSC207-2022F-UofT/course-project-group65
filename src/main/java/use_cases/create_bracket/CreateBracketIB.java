package use_cases.create_bracket;

/** An interface that allows for user input for the create bracket use case.
 * This interface is used for dependency inversion, allowing the use case to be independent of the view.
 */
public interface CreateBracketIB {

    /** Creates the bracket when the user clicks the create bracket button
     * @param createBracketID - the ID of the bracket being created
     */
    CreateBracketOD create(CreateBracketID createBracketID);
}
