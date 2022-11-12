package database;

import java.io.*;
import entities.BracketRepo;

public class BracketsReadWriter implements BracketReadWriterIF {

    @Override
    public void saveToFile(String filename, Object o) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(o);
        out.close();
        fileOut.close();
    }

    public BracketRepo readBracketFromFile(String filename) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(filename);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        BracketRepo repo = (BracketRepo) input.readObject();
        input.close();
        return repo;
    }
}

