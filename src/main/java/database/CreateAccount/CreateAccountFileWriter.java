package database.CreateAccount;

import useCases.CreateAccount.CreateAccountDSID;
import useCases.CreateAccount.CreateAccountGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateAccountFileWriter implements CreateAccountGateway {

    private final String filename;

    /**
     * Construct a new CreateAccountFileWriter that will write to the given file.
     *
     * @param filename The file to write to
     */

    public CreateAccountFileWriter(String filename) {
        this.filename = filename;
    }

    public void save(CreateAccountDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getAccounts());
        out.close();
        fileOut.close();
    }
}

