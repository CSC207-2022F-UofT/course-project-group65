package screens.assignObserver;

import database.AssignObserver.AssignObserverFileWriter;
import useCases.assignObserver.*;
import useCases.generalClasses.InformationRecord;

public class AssignObserverController {
    private final AssignObserverIB assignObsIB;

    public AssignObserverController(InformationRecord informationRecord, String currUser) {
        AssignObserverOB outputBound = new AssignObserverPresenter();
        AssignObserverGateway gateway = new AssignObserverFileWriter("brackets.txt");
        this.assignObsIB = new AssignObserverUC(outputBound, gateway, informationRecord, currUser);
    }

    public AssignObserverOD assignObserver(String assignee, int gameID){
        AssignObserverID inputData = new AssignObserverID(assignee, gameID);
        return assignObsIB.assignObserver(inputData);
    }
}
