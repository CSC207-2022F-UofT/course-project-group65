package use_cases.assign_observer;

import entities.BracketRepo;

public class AssignObserverDSID {

    private final BracketRepo BRACKET_REPO;

    public AssignObserverDSID(BracketRepo bracketRepo){
        BRACKET_REPO = bracketRepo;
    }

    public BracketRepo getBracketRepo(){
        return BRACKET_REPO;
    }
}
