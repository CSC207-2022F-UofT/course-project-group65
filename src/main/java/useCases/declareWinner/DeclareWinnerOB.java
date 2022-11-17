package useCases.declareWinner;

public interface DeclareWinnerOB {

        public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData);

        public DeclareWinnerOD presentError(String errorMessage);
}
