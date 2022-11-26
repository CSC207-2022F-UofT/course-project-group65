package useCases.assignObserver;

import entities.*;
import useCases.generalClasses.permRestrictionStrategies.PermissionChecker;
import java.util.ArrayList;
import java.util.Arrays;

public class AssignObserverUC implements AssignObserverIB {
    private final AssignObserverOB outputBound;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;
    private Bracket bracket;
    private User currUser;

    public AssignObserverUC(AssignObserverOB outputBound, BracketRepo bracketRepo, AccountRepo accountRepo, String currUser){
        this.outputBound = outputBound;
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
        this.currUser = accountRepo.getUser(currUser);
    }

    /**
     * Assigns a user as an observer to a particular game in a bracket.
     *
     * @param input the input data to use
     * @return the output data
     */
    @Override
    public AssignObserverOD assignObserver(AssignObserverID input){
        bracket = bracketRepo.getBracket(currUser.getCurrentTournament());
        if (!checkUserPermission(currUser)){
            return outputBound.prepareFailView("You do not have permission to preform this action.");
        }
        User ref = findReferee(bracket, input.getAssignee());
        Game game = bracket.getGame(input.getGameID());
        if (ref == null){
            return outputBound.prepareFailView("Assignee is not an Observer.");
        }
        else if (game == null) {
            return outputBound.prepareFailView("Game ID is invalid.");
        }
        else if (game.getObserver() != null) {
            return outputBound.prepareFailView("Game already has an Observer.");
        }
        game.setObserver(ref);
        AssignObserverOD output = new AssignObserverOD(ref.getUsername(), game.getGameID(), game.getGameRound());
        return outputBound.prepareSuccessView(output);
    }

    private User findReferee(Bracket tournament, String user){
        for (User ref: tournament.getReferees()){
            if (ref.getUsername().equals(user)){
                return ref;
            }
        }
        return null;
    }

    private boolean checkUserPermission(User user) {
        PermissionChecker permissionChecker = new PermissionChecker();
        ArrayList<String> permittedUsers = new ArrayList<>(Arrays.asList("Overseer"));
        return permissionChecker.checkUserPermission(permittedUsers, user, this.bracket.getTournamentID());
    }
}
