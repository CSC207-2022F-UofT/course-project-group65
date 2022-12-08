package interface_adapters.data_interface_adapters.create_account_data;

import use_cases.create_account.CreateAccountDSID;
import use_cases.create_account.CreateAccountGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for writing the updated program information to a file after running the use case.
 */
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

    /**
     * Write the given input to the file.
     *
     * @param data The CreateAccountDSID to write to the file
     */
    public void save(CreateAccountDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getAccounts());
        out.close();
        fileOut.close();
    }
}

