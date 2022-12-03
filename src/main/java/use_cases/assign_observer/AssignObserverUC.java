package use_cases.assign_observer;

import entities.*;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;
import use_cases.general_classes.perm_restriction_strategies.PermissionChecker;
import java.util.ArrayList;
import java.util.List;

public class AssignObserverUC implements AssignObserverIB {
    private final AssignObserverOB outputBound;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;
    private Bracket bracket;
    private User currUser;
    public AssignObserverGateway gateway;

    public AssignObserverUC(AssignObserverOB outputBound, AssignObserverGateway gateway, InformationRecord informationRecord, String currUser){
        this.outputBound = outputBound;
        this.gateway = gateway;
        this.bracketRepo = informationRecord.getBracketData();
        this.accountRepo = informationRecord.getAccountData();
        this.currUser = accountRepo.getUser(currUser);
//        try{
//            this.bracketRepo = (BracketRepo) bracketRepo;
//            this.accountRepo = (AccountRepo) accountRepo;
//            this.currUser = this.accountRepo.getUser(currUser);
//        } catch (ClassCastException e){
//            throw new ClassCastException("AssignObserverUC: Invalid repo type");
//        }
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
//        Game game;
//        if (bracket instanceof DefaultBracket){
//            GameFinder<DefaultBracket> gameFinder = new TreeGameFinder<>();
//            game = gameFinder.getGame(input.getGameID(), this.bracket.getFinalGame());
//        } else {
//            GameFinder<Bracket> gameFinder = new GeneralisedGameFinder<>();
//            game = gameFinder.getGame(input.getGameID(), this.bracket.getFinalGame());
//        }

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

        AssignObserverDSID dsInput = new AssignObserverDSID(bracketRepo);
        try{
            gateway.save(dsInput);
        } catch (Exception e){
            return outputBound.prepareFailView("Error saving to database.");
        }

        BundleBracketData bundleBracketData = new BundleBracketData();
        bundleBracketData.bundleBracket(bracket);

        AssignObserverOD output = new AssignObserverOD(ref.getUsername(), game.getGameID(), game.getGameRound(), bundleBracketData.getGameToReferee());
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
        ArrayList<String> permittedUsers = new ArrayList<>(List.of("Overseer"));
        return permissionChecker.checkUserPermission(permittedUsers, user, this.bracket.getTournamentID());
    }
}
