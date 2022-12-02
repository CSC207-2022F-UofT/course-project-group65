package database.EndTourn;

import useCases.endTourn.EndTournDSID;
import useCases.endTourn.EndTournGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EndTournFileWriter implements EndTournGateway {
    private final String filename;

    /**
     * Construct a new EndTournFileWriter that will write to the given file.
     *
     * @param filename The file to write to
     */

    public EndTournFileWriter(String filename) {
        this.filename = filename;
    }


    @Override
    public void save(EndTournDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getBracketRepo());
        out.close();
        fileOut.close();
    }
}
