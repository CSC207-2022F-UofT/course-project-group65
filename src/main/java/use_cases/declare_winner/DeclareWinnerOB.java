package use_cases.declare_winner;

public interface DeclareWinnerOB {

        public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData);

        public DeclareWinnerOD presentError(String errorMessage);
}
