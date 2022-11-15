package database.AdvanceTeam;
import database.BracketsReadWriter;
import useCases.advanceTeam.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

    public void save(AdvanceTeamDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedBracketRepo());
        out.close();
        fileOut.close();
    }
}
