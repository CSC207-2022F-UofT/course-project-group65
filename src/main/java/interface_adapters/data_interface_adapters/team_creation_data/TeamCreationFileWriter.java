package interface_adapters.data_interface_adapters.team_creation_data;

import use_cases.team_creation.teamCreationDSID;
import use_cases.team_creation.teamCreationGateway;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TeamCreationFileWriter implements teamCreationGateway{

    private final String filename;

    public TeamCreationFileWriter(String filename) {
        this.filename = filename;
    }
    @Override
    public void save(teamCreationDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedBracketRepoDSID());
        out.close();
        fileOut.close();

    }
}
