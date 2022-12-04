package interface_adapters.assign_observer;

import interface_adapters.view_interfaces.main_view_interfaces.AssignObserverView;
import use_cases.assign_observer.AssignObserverOB;
import use_cases.assign_observer.AssignObserverOD;

import java.util.LinkedHashMap;

public class AssignObserverPresenter implements AssignObserverOB {

    public AssignObserverView view;
    @Override
    public AssignObserverOD prepareSuccessView(AssignObserverOD outputData) {
        this.view.updateObserverAssignments(outputData.getGameToReferee());
        return outputData;
    }

    public void setView(AssignObserverView view){
        this.view = view;
    }

    @Override
    public AssignObserverOD prepareFailView(String error) {
        throw new AssignObserverFailed(error);
    }
}
