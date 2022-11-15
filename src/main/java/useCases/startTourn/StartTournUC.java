package useCases.startTourn;

import entities.Game;

import java.util.Objects;

public class StartTournUC implements StartTournIB{
    public StartTournOB outputBoundary;

    public StartTournUC(StartTournOB outputBoundary){
        this.outputBoundary = outputBoundary;
    }

    public boolean checkUserRole(StartTournID inputData) {
        return (Objects.equals(inputData.getUserRole(), "Overseer"));
    }

    public boolean checkNumTeams(StartTournID inputData) {
        return helperCheckNumTeams(inputData.getFinalGame());
    }

    // Recursive helper method for checkNumTeams.
    public boolean helperCheckNumTeams(Game game) {
        if (game.getNumTeams() != 2) {
            return false;
        } else if (game.getPrevGame1() == null) {
            return true;
        } else {
            return (helperCheckGameObserver(game.getPrevGame1()) &&
                    helperCheckGameObserver(game.getPrevGame2()));
        }
    }

    public boolean checkTeamFull(StartTournID inputData) {
        return (inputData.getMaxNumTeams() == inputData.getTeams().size());
    }

    public  boolean checkGameObserver(StartTournID inputData) {
        return helperCheckGameObserver(inputData.getFinalGame());
    }

    // Recursive helper method for checkGameObserver.
    public boolean helperCheckGameObserver(Game game) {
        if (game.getObserver() == null) {
            return false;
        } else if (game.getPrevGame1() == null) {
            return true;
        } else {
            return (helperCheckGameObserver(game.getPrevGame1()) &&
                    helperCheckGameObserver(game.getPrevGame2()));
        }
    }

    // TODO: implement the check part after finishing front end.
    /// TODO: after returning the error message to the user, we want to know whether the user still wants to
    /// start the tournament anyway. but for now I don't know how to implement that so I'm just leaving it.
    @Override
    public StartTournOD startTourn(StartTournID inputData) {
//        if (!checkUserRole(inputData)) {
//            return this.outputBoundary.presentError("You do not have permission to start the tournament.");
//        }
//
//        if (!checkNumTeams(inputData)) {
//            return this.outputBoundary.presentError("");
//        }
//
//        if (!checkGameObserver(inputData)) {
//
//        }
//
//        if (!checkTeamFull(inputData)) {
//
//        }

        inputData.getBracket().setTournamentCondition(true);

        return this.outputBoundary.presentSuccess("The tournament has been started successfully.");
    }
}
