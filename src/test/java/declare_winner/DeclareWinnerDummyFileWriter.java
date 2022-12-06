package declare_winner;

import entities.BracketRepo;
import use_cases.declare_winner.DeclareWinnerDSID;
import use_cases.declare_winner.DeclareWinnerGateway;

import java.io.IOException;
import java.util.HashMap;

public class DeclareWinnerDummyFileWriter implements DeclareWinnerGateway {

    final private HashMap<BracketRepo, DeclareWinnerDSID> bracketRepos = new HashMap<>();

    @Override
    public void save(DeclareWinnerDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getUpdatedBracketRepoDSID());
        bracketRepos.put(dsid.getUpdatedBracketRepoDSID(), dsid);
    }
}
