package interface_adapters.data_interface_adapters.join_tournament_data;

import use_cases.join_tournament.JoinTournamentDSID;
import use_cases.join_tournament.JoinTournamentGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for writing the new account and bracket information to a file after joining a tournament.
 */
public class JoinTournamentFileWriter implements JoinTournamentGateway {
    private final String accountFilename;
    private final String bracketFileName;

    public JoinTournamentFileWriter(String accountFilename, String bracketFileName){
        this.accountFilename = accountFilename;
        this.bracketFileName = bracketFileName;
    }

    /**
     * Writes the new account and bracket information to a file.
     * @param input The data source of the join tournament use case.
     */
    @Override
    public void save(JoinTournamentDSID input) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.bracketFileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(input.getBracketRepo());
        out.close();
        fileOut.close();

        FileOutputStream fileOut2 = new FileOutputStream(this.accountFilename);
        ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
        out2.writeObject(input.getAccountRepo());
        out2.close();
        fileOut2.close();
    }

}
