package database.ChangePoints;

import use_cases.change_points.ChangePointsDSID;
import use_cases.change_points.ChangePointsGateway;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ChangePointsFileWriter implements ChangePointsGateway {

    private final String filename;

    /**
     * Construct a new ChangePointsFileWriter that will write to the given file.
     *
     * @param filename The file to write to
     */

    public ChangePointsFileWriter(String filename) {
        this.filename = filename;
    }

    public void save(ChangePointsDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedBracketRepoCP());
        out.close();
        fileOut.close();
    }
}
