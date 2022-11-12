package useCases.assignObserver;

public interface AssignObserverOB {
    AssignObserverOD prepareSuccessView(AssignObserverOD outputData);

    AssignObserverOD prepareFailView(String error);
}
