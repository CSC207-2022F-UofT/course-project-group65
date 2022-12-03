package use_cases.view_tournament;

import entities.BracketRepo;
import entities.User;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

public class ViewTournamentUC implements ViewTournamentIB {
    final ViewTournamentOB outputBound;
    private final BracketRepo bracketRepo;
    private final User CURR_USER;
    public ViewTournamentGateway gateway;

    /**
     * Construct a ViewTournamentUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param outputBound The output boundary to use
     * @param currUser    The username of the user who is joining the tournament
     */
    public ViewTournamentUC(ViewTournamentOB outputBound, ViewTournamentGateway gateway, InformationRecord informationRecord, String currUser){
        this.outputBound = outputBound;
        this.gateway = gateway;
        this.bracketRepo = informationRecord.getBracketData();
        CURR_USER = informationRecord.getAccountData().getUser(currUser);
    }

    /**
     * Allows the user to access a tournament that they have already joined
     *
     * @param input the inputData to use
     * @return the output data
     */
    public ViewTournamentOD viewBracket(ViewTournamentID input){
        int tournamentID = input.getTournamentID();

        if (!CURR_USER.getAllTournaments().contains(tournamentID)){
            return outputBound.prepareFailView("You have not joined this tournament yet.");
        }

        BundleBracketData bracketData = new BundleBracketData();
        CURR_USER.setCurrentTournament(tournamentID);
        bracketData.bundleBracket(bracketRepo.getBracket(tournamentID));

        ViewTournamentDSID dsid = new ViewTournamentDSID(this.bracketRepo);
        try {
            this.gateway.save(dsid);
        } catch (Exception e) {
            return outputBound.prepareFailView("Error saving data");
        }

        ViewTournamentOD output = new ViewTournamentOD(CURR_USER.getUsername(), bracketData);

        return outputBound.prepareSuccessView(output);
    }
}
