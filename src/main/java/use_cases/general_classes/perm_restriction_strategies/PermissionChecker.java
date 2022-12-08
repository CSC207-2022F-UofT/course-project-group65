package use_cases.general_classes.perm_restriction_strategies;

import entities.User;
import java.util.ArrayList;

/**
 * This class is used to check if a user has permission to perform an action.
 */
public class PermissionChecker {

    public boolean checkUserPermission(ArrayList<String> permittedUsers, User user, int bracketID) {
        String userRole = user.getBracketRole(bracketID);
        return permittedUsers.contains(userRole);
    }
}

