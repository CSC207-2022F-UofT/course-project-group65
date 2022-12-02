package database.CreateBracket;

import useCases.createBracket.CreateBracketDSID;
import useCases.createBracket.CreateBracketGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/** A class for writing the data to the database.
 * Implements the interface CreateBracketGateway, keeping in line with Clean Architecture.
 * This class allows for persistence of bracket/user data between runs of our program.
 */
public class CreateBracketFileWriter implements CreateBracketGateway {
    /** The file path for the account database */
    private final String accountsFile;
    /** The file path for the bracket database */
    private final String bracketsFile;

    /** Constructor for the CreateBracketFileWriter class
     * @param accountsFile - the file path for the account database
     * @param bracketsFile - the file path for the bracket database
     */
    public CreateBracketFileWriter(String accountsFile, String bracketsFile) {
        this.accountsFile = accountsFile;
        this.bracketsFile = bracketsFile;
    }

    /** Updates the repositories with the newly created Bracket and updated User info.
     * Writes the info to the requisite database
     * @param dsid - the data source interface data
     */
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
