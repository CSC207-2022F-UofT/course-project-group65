package database.AssignObserver;

import useCases.assignObserver.AssignObserverDSID;
import useCases.assignObserver.AssignObserverGateway;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AssignObserverFileWriter implements AssignObserverGateway {
    private final String bracketFilename;

    public AssignObserverFileWriter(String bracketFilename) {
        this.bracketFilename = bracketFilename;
    }

    public void save(AssignObserverDSID input) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(this.bracketFilename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(input.getBracketRepo());
        out.close();
        fileOut.close();
    }
}
