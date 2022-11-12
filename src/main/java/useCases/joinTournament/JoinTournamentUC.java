package useCases.joinTournament;

import entities.BracketRepo;
import entities.User;

public class JoinTournamentUC implements JoinTournamentIB{
    final JoinTournamentOB outputBound;

    public JoinTournamentUC(JoinTournamentOB outputBound){
        this.outputBound = outputBound;
    }

    public JoinTournamentOD joinBracket(JoinTournamentID input){
        String role = input.getInvite().substring(0, 2);
        String idAndName = input.getInvite().substring(2);
        int tournamentID = Integer.parseInt(idAndName.split("(?<=\\d)(?=\\D)")[0]);
        User currUser = input.getCurrUser();
        BracketRepo repo = input.getRepo();

        if (!repo.getBrackets().containsKey(tournamentID)){
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
        if (role.equals("PL")){
            currUser.setBracketRole(tournamentID, "Player");
            output = new JoinTournamentOD(tournamentID, "Player");
        }
        else {
            currUser.setBracketRole(tournamentID, "Observer");
            repo.getBracket(tournamentID).addReferee(currUser);
            output = new JoinTournamentOD(tournamentID, "Observer");
        }
        return outputBound.prepareSuccessView(output);
    }
}
