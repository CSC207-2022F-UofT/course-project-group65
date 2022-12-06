package use_cases.join_tournament;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.User;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

/**
 * A use case class that handles joining a tournament for the first time for a user. It will make the
 * changes required to allow the user to interact with the desired tournament and save and update the
 * bracket and account repositories afterwards.
 */
public class JoinTournamentUC implements JoinTournamentIB{
    private final JoinTournamentOB outputBound;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;
    private final User currUser;
    private final JoinTournamentGateway gateway;

    /**
     * Creates a new JoinTournamentUC object.
     *
     * @param outputBound       The output boundary to use
     * @param gateway           The gateway to access the database to store info
     * @param informationRecord The information record containing the bracket and account repositories
     * @param currUser          The username of the user who is joining the tournament
     */
    public JoinTournamentUC(JoinTournamentOB outputBound, JoinTournamentGateway gateway,
                            InformationRecord informationRecord, String currUser){
        this.outputBound = outputBound;
        bracketRepo = informationRecord.getBracketData();
        accountRepo = informationRecord.getAccountData();
        this.currUser = this.accountRepo.getUser(currUser);
        this.gateway = gateway;
    }

    /**
     * Allows the user to access a tournament for the first time
     *
     * @param input the inputData containing the invite
     * @return the output data to change the view
     */
    public JoinTournamentOD joinBracket(JoinTournamentID input){
        String invite = input.getInvite();
        if(invite == null || invite.equals("")){
            return outputBound.prepareFailView("Please enter an invite.");
        }
        String role = invite.substring(0, 2);
        String idAndName = input.getInvite().substring(2);
        try {
            int tournamentID = Integer.parseInt(idAndName.split("(?<=\\d)(?=\\D)")[0]);

            if (!bracketRepo.getBrackets().containsKey(tournamentID)){
                return outputBound.prepareFailView("Tournament does not exist.");
            }
            else if (currUser.getAllTournaments().contains(tournamentID)){
                return outputBound.prepareFailView("You have already joined this tournament.");
            }
            else if (!role.equals("PL") && !role.equals("OB")){
                return outputBound.prepareFailView("Role does not exist within tournament.");
            }
            currUser.setCurrentTournament(tournamentID);
            currUser.addTournament(tournamentID);
            JoinTournamentOD output;
            BundleBracketData bracketData = new BundleBracketData();
            if (role.equals("PL")){
                currUser.setBracketRole(tournamentID, "Player");
            }
            else {
                currUser.setBracketRole(tournamentID, "Observer");
                bracketRepo.getBracket(tournamentID).addReferee(currUser);
            }

            JoinTournamentDSID dsInput = new JoinTournamentDSID(bracketRepo, accountRepo);
            try{
                this.gateway.save(dsInput);
            } catch (Exception e){
                return outputBound.prepareFailView("Error saving to database.");
            }
            bracketData.bundleBracket(bracketRepo.getBracket(tournamentID));
            output = new JoinTournamentOD(currUser.getUsername(), bracketData);
            return outputBound.prepareSuccessView(output);
        }
        catch (NumberFormatException nex){
            return outputBound.prepareFailView("Invalid invite format.");
        }
    }
}
