package interface_adapters.data_interface_adapters.declare_winner_data;

import use_cases.declare_winner.DeclareWinnerDSID;
import use_cases.declare_winner.DeclareWinnerGateway;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DeclareWinnerFileWriter implements DeclareWinnerGateway {

    private final String filename;

    /**
     * Construct a new DeclareWinnerFileWriter that will write to the given file.
     *
     * @param filename The file to write to
     */

    public DeclareWinnerFileWriter(String filename) {
        this.filename = filename;
    }

    /**
     * Write the given data to the file.
     *
     * @param data The data to write
     * @throws IOException If an error occurs while writing to the file
     */

    public void save(DeclareWinnerDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedBracketRepoDSID());
        out.close();
        fileOut.close();
    }
}
