package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultUserFactory implements UserFactory{
    @Override
    public User create(String username, String password, HashMap<Integer, String> bracketRole, int currentTournament, ArrayList<Integer> currentTournaments) {
        return new DefaultUser();
    }
}
