package use_cases.assign_observer;

public interface AssignObserverOB {
    //AssignObserverOD prepareSuccessView(AssignObserverOD outputData);
    AssignObserverOD prepareSuccessView(AssignObserverOD outputData);

    AssignObserverOD prepareFailView(String error);
}
