package screens.assignObserver;

import useCases.assignObserver.AssignObserverOB;
import useCases.assignObserver.AssignObserverOD;

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
