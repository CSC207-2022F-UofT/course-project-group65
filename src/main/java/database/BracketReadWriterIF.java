package database;

import entities.*;

import java.io.IOException;

//UNUSED INTERFACE. KEPT FOR ARCHIVAL PURPOSES

public interface BracketReadWriterIF {
    /**
     * @param filename location of ser file
     * @param o object to be serialized
     */
    void saveToFile(String filename, Object o) throws IOException;

    /**
     * @param filename location of ser file
     */
    BracketRepo readBracketFromFile(String filename) throws IOException, ClassNotFoundException;
}
