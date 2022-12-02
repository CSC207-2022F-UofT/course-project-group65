package useCases.startTourn;

import entities.BracketRepo;


public class StartTournDSID {
    BracketRepo bracketRepo;
    public StartTournDSID(BracketRepo bracketRepo) {
        this.bracketRepo = bracketRepo;
    }
    public BracketRepo getBracketRepo() {return this.bracketRepo;}
}
