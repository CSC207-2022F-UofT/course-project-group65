package screens.assignObserver;

import useCases.assignObserver.AssignObserverIB;
import useCases.assignObserver.AssignObserverID;
import useCases.assignObserver.AssignObserverOD;

public class AssignObserverController {
    private final AssignObserverIB assignObsIB;

    public AssignObserverController(AssignObserverIB assignObsIB) {
        this.assignObsIB = assignObsIB;
    }

    public AssignObserverOD assignObserver(String assignee, int gameID){
        AssignObserverID inputData = new AssignObserverID(assignee, gameID);
        return assignObsIB.assignObserver(inputData);
    }
}
