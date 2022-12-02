package database.StartTourn;

import useCases.startTourn.StartTournDSID;
import useCases.startTourn.StartTournGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StartTournFilerWriter implements StartTournGateway {
    private final String filename;

    /**
     * Construct a new EndTournFileWriter that will write to the given file.
     *
     * @param filename The file to write to
     */

    public StartTournFilerWriter(String filename) {this.filename = filename;}

    @Override
    public void save(StartTournDSID data) throws IOException {
        {
            FileOutputStream fileOut = new FileOutputStream(this.filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data.getBracketRepo());
            out.close();
            fileOut.close();
        }
    }
}
