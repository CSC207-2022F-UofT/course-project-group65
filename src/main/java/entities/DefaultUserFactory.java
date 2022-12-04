package entities;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class is a factory for creating a default user. A default user simply has a username,
 * and a password.
 * This class is part of the factory design pattern, and used in the builder design pattern giving
 * us a flexible way to extend the functionality of the program.
 * */
public class DefaultUserFactory implements UserFactory{
    /**
     * This method is used to create a new default user. Each user will start with a unique default username
     * and password
     * @param bracketRole - The bracket role
     * @param currentTournament - The current tournament ID
     * @param currentTournaments - All the current tournaments
     * @return a new default user
     */
    @Override
    public User create(String username, String password, HashMap<Integer, String> bracketRole, int currentTournament,
                       ArrayList<Integer> currentTournaments) {
        DefaultUser defaultUser = new DefaultUser();
        defaultUser.setUsername(username);
        defaultUser.setPassword(password);

        return defaultUser;
    }
}
