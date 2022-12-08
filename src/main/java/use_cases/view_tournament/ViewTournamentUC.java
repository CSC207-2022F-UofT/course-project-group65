package use_cases.view_tournament;

import entities.BracketRepo;
import entities.User;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

/**
 * A use case class that handles viewing an already joined tournament for a user.
 * It will change the users current tournament to the one they wish to join.
 */
public class ViewTournamentUC implements ViewTournamentIB {
    final ViewTournamentOB outputBound;
    private final BracketRepo bracketRepo;
    private final User currUser;
    private final ViewTournamentGateway gateway;

    /**
     * Creates a new ViewTournamentUC object.
     *
     * @param outputBound       The output boundary to use
     * @param gateway           The gateway to access the database to store info
     * @param informationRecord The information record containing the bracket repository
     * @param currUser          The username of the user who is joining the tournament
     */
    public ViewTournamentUC(ViewTournamentOB outputBound, ViewTournamentGateway gateway, InformationRecord informationRecord, String currUser){
        this.outputBound = outputBound;
        this.gateway = gateway;
        this.bracketRepo = informationRecord.getBracketData();
        this.currUser = informationRecord.getAccountData().getUser(currUser);
    }

    /**
     * Allows the user to access a tournament that they have already joined.
     *
     * @param input the inputData containing the tournamentID
     * @return the output data to change the view
     */
    public ViewTournamentOD viewBracket(ViewTournamentID input){
        int tournamentID = input.getTournamentID();

        if (!currUser.getAllTournaments().contains(tournamentID)){
            return outputBound.prepareFailView("You have not joined this tournament yet.");
        }

        BundleBracketData bracketData = new BundleBracketData();
        currUser.setCurrentTournament(tournamentID);
        bracketData.bundleBracket(bracketRepo.getBracket(tournamentID));

        ViewTournamentDSID dsid = new ViewTournamentDSID(this.bracketRepo);
        try {
            this.gateway.save(dsid);
        } catch (Exception e) {
            return outputBound.prepareFailView("Error saving data");
        }

        ViewTournamentOD output = new ViewTournamentOD(currUser.getUsername(), bracketData);

        return outputBound.prepareSuccessView(output);
    }
}
