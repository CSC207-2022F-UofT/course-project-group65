package database.CreateBracket;

import useCases.createBracket.CreateBracketDSID;
import useCases.createBracket.CreateBracketGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateBracketFileWriter implements CreateBracketGateway {
    /*
    A class for the gateway of the create bracket use case.
     */

    private final String accountsFile;
    private final String bracketsFile;

    public CreateBracketFileWriter(String accountsFile, String bracketsFile) {
        this.accountsFile = accountsFile;
        this.bracketsFile = bracketsFile;
    }

    @Override
    public void save(CreateBracketDSID dsid) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.bracketsFile);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(dsid.getBracketRepo());
        out.close();
        fileOut.close();

        FileOutputStream fileOut2 = new FileOutputStream(this.accountsFile);
        ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
        out2.writeObject(dsid.getAccountRepo());
        out2.close();
        fileOut2.close();
    }
}
