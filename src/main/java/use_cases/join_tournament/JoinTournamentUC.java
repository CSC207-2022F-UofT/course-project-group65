package use_cases.join_tournament;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.User;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

public class JoinTournamentUC implements JoinTournamentIB{
    final JoinTournamentOB outputBound;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;
    private final User CURR_USER;
    public JoinTournamentGateway gateway;

    /**
     * Construct a JoinTournamentUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param outputBound The output boundary to use
//     * @param bracketRepo The BracketRepo to use
//     * @param accountRepo The AccountRepo to use
     * @param currUser    The username of the user who is joining the tournament
     */
    public JoinTournamentUC(JoinTournamentOB outputBound, JoinTournamentGateway gateway,
                            InformationRecord informationRecord, String currUser){
        this.outputBound = outputBound;
        this.bracketRepo = informationRecord.getBracketData();
        this.accountRepo = informationRecord.getAccountData();
        CURR_USER = this.accountRepo.getUser(currUser);
        this.gateway = gateway;
    }

    /**
     * Allows the user to access a tournament for the first time
     *
     * @param input the inputData to use
     * @return the output data
     */
    public JoinTournamentOD joinBracket(JoinTournamentID input){
        String role = input.getInvite().substring(0, 2);
        String idAndName = input.getInvite().substring(2);
        try {
            int tournamentID = Integer.parseInt(idAndName.split("(?<=\\d)(?=\\D)")[0]);

            if (!bracketRepo.getBrackets().containsKey(tournamentID)){
                return outputBound.prepareFailView("Tournament does not exist.");
            }
            else if (CURR_USER.getAllTournaments().contains(tournamentID)){
                return outputBound.prepareFailView("You have already joined this tournament.");
            }
            else if (!role.equals("PL") && !role.equals("OB")){
                return outputBound.prepareFailView("Role does not exist within tournament.");
            }
            CURR_USER.setCurrentTournament(tournamentID);
            CURR_USER.addTournament(tournamentID);
            JoinTournamentOD output;
            BundleBracketData bracketData = new BundleBracketData();
            if (role.equals("PL")){
                CURR_USER.setBracketRole(tournamentID, "Player");
            }
            else {
                CURR_USER.setBracketRole(tournamentID, "Observer");
                bracketRepo.getBracket(tournamentID).addReferee(CURR_USER);
            }

            JoinTournamentDSID dsInput = new JoinTournamentDSID(bracketRepo, accountRepo);
            try{
                this.gateway.save(dsInput);
            } catch (Exception e){
                return outputBound.prepareFailView("Error saving to database.");
            }
            bracketData.bundleBracket(bracketRepo.getBracket(tournamentID));
            output = new JoinTournamentOD(CURR_USER.getUsername(), bracketData, bracketRepo, accountRepo); //Temp fix
            return outputBound.prepareSuccessView(output);
        }
        catch (NumberFormatException nex){
            return outputBound.prepareFailView("Invalid invite format.");
        }
    }
}
