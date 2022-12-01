package useCases.assignObserver;

import entities.AccountRepo;
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
