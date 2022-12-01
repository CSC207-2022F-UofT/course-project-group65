package database.StartTourn;

import useCases.startTourn.StartTournDSID;
import useCases.startTourn.StartTournGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StartTournFileWriter implements StartTournGateway {
    private final String filename;

    public StartTournFileWriter(String filename) {
        this.filename = filename;
    }
    @Override
    public void save(StartTournDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getBracketRepo());
        out.close();
        fileOut.close();
    }
}
