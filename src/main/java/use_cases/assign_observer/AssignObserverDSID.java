package use_cases.assign_observer;

import entities.BracketRepo;

public class AssignObserverDSID {

    private BracketRepo bracketRepo;

    public AssignObserverDSID(BracketRepo bracketRepo){
        this.bracketRepo = bracketRepo;
    }


    public BracketRepo getBracketRepo(){
        return bracketRepo;
    }

    public void setBracketRepo(BracketRepo bracketRepo){
        this.bracketRepo = bracketRepo;
    }

}