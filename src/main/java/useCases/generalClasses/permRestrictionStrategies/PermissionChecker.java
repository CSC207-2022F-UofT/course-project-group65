package useCases.generalClasses.permRestrictionStrategies;

import entities.User;
import java.util.ArrayList;

public class PermissionChecker {

    // This is the permission checker, which checks if the user is a permitted user to perform the action.
    public boolean checkUserPermission(ArrayList<String> permittedUsers, User user, int bracketID) {
        String userRole = user.getBracketRole(bracketID);
        return permittedUsers.contains(userRole);
    }
}

