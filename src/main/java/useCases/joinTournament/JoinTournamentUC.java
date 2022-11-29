package useCases.joinTournament;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.User;
import useCases.generalClasses.bundleBracketData.BundleBracketData;

public class JoinTournamentUC implements JoinTournamentIB{
    final JoinTournamentOB outputBound;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;
    private User currUser;
    public JoinTournamentGateway gateway;

    /**
     * Construct a JoinTournamentUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param outputBound The output boundary to use
     * @param bracketRepo The BracketRepo to use
     * @param accountRepo The AccountRepo to use
     * @param currUser    The username of the user who is joining the tournament
     */
    public JoinTournamentUC(JoinTournamentOB outputBound, JoinTournamentGateway gateway, Object bracketRepo,
                            Object accountRepo, String currUser){
        this.outputBound = outputBound;
        try{
            this.bracketRepo = (BracketRepo) bracketRepo;
            this.accountRepo = (AccountRepo) accountRepo;
            this.currUser = this.accountRepo.getUser(currUser);
        } catch (ClassCastException e){
            throw new ClassCastException("The given bracketRepo or accountRepo is not of type BracketRepo or AccountRepo");
        }
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
            output = new JoinTournamentOD(currUser.getUsername(), bracketData, bracketRepo, accountRepo); //Temp fix
            return outputBound.prepareSuccessView(output);
        }
        catch (NumberFormatException nex){
            return outputBound.prepareFailView("Invalid invite format.");
        }
    }
}
