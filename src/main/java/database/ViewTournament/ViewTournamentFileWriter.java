package database.ViewTournament;

import useCases.viewTournament.ViewTournamentDSID;
import useCases.viewTournament.ViewTournamentGateway;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ViewTournamentFileWriter implements ViewTournamentGateway {

    private final String fileName;

    public ViewTournamentFileWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(ViewTournamentDSID dsid) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(dsid.getBracketRepo());
        out.close();
        fileOut.close();
    }
}

