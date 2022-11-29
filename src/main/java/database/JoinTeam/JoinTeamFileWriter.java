package database.JoinTeam;

import useCases.joinTeam.JoinTeamDSID;
import useCases.joinTeam.JoinTeamGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JoinTeamFileWriter implements JoinTeamGateway {
    private final String filename;

    public JoinTeamFileWriter(String filename) {
        this.filename = filename;
    }
    @Override
    public void save(JoinTeamDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedBracketRepoDSID());
        out.close();
        fileOut.close();
    }
}
