package interface_adapters.data_interface_adapters.end_tournament_data;

import use_cases.end_tournament.EndTournDSID;
import use_cases.end_tournament.EndTournGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for writing the bracket information to a file after ending a tournament.
 */
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

    /**
     * Write the given bracket information to the file.
     *
     * @param data The bracket information to write
     */
    @Override
    public void save(EndTournDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getBracketRepo());
        out.close();
        fileOut.close();
    }
}
