package interface_adapters.data_interface_adapters.view_tournament_data;

import use_cases.view_tournament.ViewTournamentDSID;
import use_cases.view_tournament.ViewTournamentGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Saves information after a user joins a tournament they were part of before.
 */
public class ViewTournamentFileWriter implements ViewTournamentGateway {

    private final String fileName;

    public ViewTournamentFileWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves the information of a tournament.
     *
     * @param dsid the information of a tournament.
     */
    @Override
    public void save(ViewTournamentDSID dsid) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(dsid.getBracketRepo());
        out.close();
        fileOut.close();
    }
}

