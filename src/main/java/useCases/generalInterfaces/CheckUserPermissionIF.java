package useCases.generalInterfaces;

import entities.User;

public interface CheckUserPermissionIF {

    boolean checkUserPermission(User user);
}
