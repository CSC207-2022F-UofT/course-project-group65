package interface_adapters.data_interface_adapters.start_tournament_data;

import use_cases.start_tournament.StartTournDSID;
import use_cases.start_tournament.StartTournGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Saves program information to a file after starting a tournament.
 */
public class StartTournFilerWriter implements StartTournGateway {
    private final String filename;

    /**
     * Construct a new EndTournFileWriter that will write to the given file.
     *
     * @param filename The file to write to
     */

    public StartTournFilerWriter(String filename) {this.filename = filename;}

    /**
     * Save the given data to the file.
     *
     * @param data The data to save
     */
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
