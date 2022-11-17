package entities;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserFactory {
    User create(String username, String password, HashMap<Integer, String> bracketRole, int currentTournament, ArrayList<Integer> currentTournaments);
}
