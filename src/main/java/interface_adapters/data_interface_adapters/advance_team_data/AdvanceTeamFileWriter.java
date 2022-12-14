package interface_adapters.data_interface_adapters.advance_team_data;
import use_cases.advance_team.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for writing the advance team data to a file.
 */
public class AdvanceTeamFileWriter implements AdvanceTeamGateway {

    private final String filename;

    /**
     * Construct a new AdvanceTeamFileWriter that will write to the given file.
     *
     * @param filename The file to write to
     */

    public AdvanceTeamFileWriter(String filename) {
        this.filename = filename;
    }

    /**
     * Write the given advance team data to the file.
     *
     * @param data The advance team data to write
     */
    public void save(AdvanceTeamDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedBracketRepo());
        out.close();
        fileOut.close();
    }
}
