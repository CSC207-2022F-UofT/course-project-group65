package screens.assignObserver;

import database.AssignObserver.AssignObserverFileWriter;
import useCases.assignObserver.*;

public class AssignObserverController {
    private final AssignObserverIB assignObsIB;

    public AssignObserverController(Object bracketRepo, Object accountRepo, String currUser) {
        AssignObserverOB outputBound = new AssignObserverPresenter();
        AssignObserverGateway gateway = new AssignObserverFileWriter("brackets.txt");
        this.assignObsIB = new AssignObserverUC(outputBound, gateway, bracketRepo, accountRepo, currUser);
    }

    public AssignObserverOD assignObserver(String assignee, int gameID){
        AssignObserverID inputData = new AssignObserverID(assignee, gameID);
        return assignObsIB.assignObserver(inputData);
    }
}
