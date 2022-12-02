package useCases.viewTournament;

import entities.BracketRepo;

public class ViewTournamentDSID {

    private final BracketRepo bracketRepo;

    public ViewTournamentDSID(BracketRepo bracketRepo){
        this.bracketRepo = bracketRepo;
    }

    public BracketRepo getBracketRepo(){
        return bracketRepo;
    }

}
