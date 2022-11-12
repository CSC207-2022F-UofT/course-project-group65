package useCases.assignObserver;

import entities.Bracket;
import entities.Game;
import entities.User;
import useCases.generalInterfaces.CheckUserPermissionIF;
import useCases.generalClasses.*;

public class AssignObserverUC implements CheckUserPermissionIF, AssignObserverIB {
    private final AssignObserverOB outputBound;
    private Bracket bracket;

    public AssignObserverUC(AssignObserverOB outputBound){
        this.outputBound = outputBound;
    }

    /**
     * Assigns a user as an observer to a particular game in a bracket.
     * @param input input data containing the logged-in user, username of assignee,
     *              gameID of the game assignee is being assigned to, and the current
     *              tournament the user is viewing
     * @return A message stating whether the observer could not be added or data to
     * create a message saying the observer was successfully added
     */
    public AssignObserverOD assignObserver(AssignObserverID input){
        bracket = input.getTournament();
        if (!checkUserPermission(input.getCurrUser())){
            return outputBound.prepareFailView("You do not have permission to preform this action.");
        }
        User ref = findReferee(bracket, input.getAssignee());
        TreeMethods treeMethodAccess = new DefaultBracketMethods();
        Game game = findGame(treeMethodAccess, input.getGameID(), bracket.getFinalGame());
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

    public Game findGame(TreeMethods treeMethodAccess, int gameID, Game head) {
        return treeMethodAccess.findGame(gameID, head);
    }

    @Override
    public boolean checkUserPermission(User user) {
        return user.getBracketRole(bracket.getTournamentID()).equals("Overseer");
    }
}
