package useCases.endTourn;

import entities.BracketRepo;

public class EndTournDSID {
    BracketRepo bracketRepo;

    public EndTournDSID(BracketRepo bracketRepo) { this.bracketRepo = bracketRepo;}
    public BracketRepo getBracketRepo() { return this.bracketRepo; }
    public void setBracketRepo(BracketRepo bracketRepo) {this.bracketRepo = bracketRepo;}
}
