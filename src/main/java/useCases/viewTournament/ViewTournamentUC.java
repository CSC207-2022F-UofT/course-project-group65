package useCases.viewTournament;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.User;
import useCases.generalClasses.bundleBracketData.BundleBracketData;

public class ViewTournamentUC implements ViewTournamentIB {
    final ViewTournamentOB outputBound;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;
    private User currUser;
    public ViewTournamentGateway gateway;

    /**
     * Construct a ViewTournamentUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param outputBound The output boundary to use
     * @param bracketRepo The BracketRepo to use
     * @param accountRepo The AccountRepo to use
     * @param currUser    The username of the user who is joining the tournament
     */
    public ViewTournamentUC(ViewTournamentOB outputBound, ViewTournamentGateway gateway, Object bracketRepo,
                            Object accountRepo, String currUser){
        this.outputBound = outputBound;
        this.gateway = gateway;
        try{
            this.bracketRepo = (BracketRepo) bracketRepo;
            this.accountRepo = (AccountRepo) accountRepo;
            this.currUser = this.accountRepo.getUser(currUser);
        } catch (ClassCastException e) {
            throw new ClassCastException("bracketRepo must be of type BracketRepo");
        }
//        this.bracketRepo =bracketRepo;
//        this.accountRepo = accountRepo;
//        this.currUser = accountRepo.getUser(currUser);
    }

    /**
     * Allows the user to access a tournament that they have already joined
     *
     * @param input the inputData to use
     * @return the output data
     */
    public ViewTournamentOD viewBracket(ViewTournamentID input){
        int tournamentID = input.getTournamentID();

        if (!currUser.getAllTournaments().contains(tournamentID)){
            return outputBound.prepareFailView("You have not joined this tournament yet.");
        }

        String role = currUser.getBracketRole(tournamentID);
        BundleBracketData bracketData = new BundleBracketData();
        bracketData.bundleBracket(bracketRepo.getBracket(tournamentID));

        ViewTournamentDSID dsid = new ViewTournamentDSID(this.bracketRepo);
        try {
            this.gateway.save(dsid);
        } catch (Exception e) {
            return outputBound.prepareFailView("Error saving data");
        }

        ViewTournamentOD output = new ViewTournamentOD(currUser.getUsername(), bracketData, currUser.getAllTournaments(),
                bracketRepo, accountRepo); //Temp Fix

        return outputBound.prepareSuccessView(output);
    }
}
