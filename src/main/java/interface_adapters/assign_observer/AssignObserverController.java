package interface_adapters.assign_observer;

import frameworks_and_drivers.ExtendedView;
import interface_adapters.data_interface_adapters.assign_observer_data.AssignObserverFileWriter;
import use_cases.assign_observer.*;
import use_cases.general_classes.InformationRecord;

public class AssignObserverController {
    private final AssignObserverIB assignObsIB;
    private final AssignObserverPresenter presenter;

    public AssignObserverController(InformationRecord informationRecord, String currUser) {
        presenter = new AssignObserverPresenter();
        AssignObserverGateway gateway = new AssignObserverFileWriter("brackets.txt");
        this.assignObsIB = new AssignObserverUC(presenter, gateway, informationRecord, currUser);
    }

    public void setPresenterView(ExtendedView view){
        presenter.setView(view);
    }

    public void assignObserver(String assignee, int gameID){
        AssignObserverID inputData = new AssignObserverID(assignee, gameID);
        assignObsIB.assignObserver(inputData);
    }
}
