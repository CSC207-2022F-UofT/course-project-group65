package interface_adapters.data_interface_adapters.join_team_data;

import use_cases.join_team.JoinTeamDSID;
import use_cases.join_team.JoinTeamGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JoinTeamFileWriter implements JoinTeamGateway {
    private final String filename;
    /**
     * Construct a new JoinTeamFileWriter that will write to the given file.
     *
     * @param filename The file to write to.
     */
    public JoinTeamFileWriter(String filename) {
        this.filename = filename;
    }
    /**
     * Write the given data to the file.
     *
     * @param data The data to write
     * @throws IOException If an error occurs while writing to the file
     */
    @Override
    public void save(JoinTeamDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedBracketRepoDSID());
        out.close();
        fileOut.close();
    }
}