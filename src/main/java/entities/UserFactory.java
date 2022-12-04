package entities;

import java.util.ArrayList;
import java.util.HashMap;
/** An interface for a user factory.
 * It is part of the factory design pattern, and in our program is implemented
 * by the DefaultUserFactory class.
 */
public interface UserFactory {
    /** A method to create a new user.
     * @param username - The name of the user
     * @param password - The password of the account
     * @param bracketRole - The bracket role
     * @param currentTournament - The current tournament ID
     * @param currentTournaments - All the current tournaments
     * @return a new user
     */
    User create(String username, String password, HashMap<Integer, String> bracketRole, int currentTournament, ArrayList<Integer> currentTournaments);
}
