package useCases.createBracket;

public interface CreateBracketOB {
    /*
    An interface for the output boundary of the create bracket use case.
     */
    public CreateBracketOD presentSuccess(CreateBracketOD viewData);

    public CreateBracketOD presentError(String error);
}
