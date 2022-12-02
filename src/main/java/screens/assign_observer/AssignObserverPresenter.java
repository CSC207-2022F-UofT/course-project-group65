package screens.assign_observer;

import use_cases.assign_observer.AssignObserverOB;
import use_cases.assign_observer.AssignObserverOD;

public class AssignObserverPresenter implements AssignObserverOB {
    @Override
    public AssignObserverOD prepareSuccessView(AssignObserverOD outputData) {
        return outputData;
    }

    @Override
    public AssignObserverOD prepareFailView(String error) {
        throw new AssignObserverFailed(error);
    }
}
