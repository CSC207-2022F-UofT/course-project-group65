package interface_adapters.data_interface_adapters.assign_observer_data;

import use_cases.assign_observer.AssignObserverDSID;
import use_cases.assign_observer.AssignObserverGateway;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class writes the updated bracket information post-assign observer to a file.
 */
public class AssignObserverFileWriter implements AssignObserverGateway {
    private final String bracketFilename;

    public AssignObserverFileWriter(String bracketFilename) {
        this.bracketFilename = bracketFilename;
    }

    /**
     * Writes the updated bracket information post-assign observer to a file.
     *
     * @param input the updated bracket information post-assign observer
     */
    public void save(AssignObserverDSID input) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.bracketFilename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(input.getBracketRepo());
        out.close();
        fileOut.close();
    }
}
