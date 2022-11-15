package useCases.endTourn;

import java.util.Objects;
import java.lang.Math;

import static java.lang.Math.log;

public class EndTournUC implements EndTournIB{
    public EndTournOB outputBoundary;

    public EndTournUC(EndTournOB outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public boolean checkUserRole(EndTournID inputData) {
        return (Objects.equals(inputData.getUserRole(), "Overseer"));
    }

    // Calculating the total # of rounds in the tournament using log base 2.
    public boolean checkGame(EndTournID inputData) {
        return (inputData.getFinalGame().getGameRound() ==
                Math.log(inputData.getTeams().size()) / Math.log(2));
    }

    public boolean checkFinalWinner(EndTournID inputData) {
        return (inputData.getFinalWinner() != null);
    }

    // TODO: implement the check part after finishing front end.
    @Override
    public EndTournOD endTourn(EndTournID inputData) {
//        if (!checkGame(inputData)) {
//            return this.outputBoundary.presentError("This round is not the final round.");
//        }
//
//        if (!checkFinalWinner(inputData)) {
//            return this.outputBoundary.presentError("No final winner has been decided yet.");
//        }
//
//        if (!checkUserRole(inputData)) {
//            return this.outputBoundary.presentError("You do not have permission to end the tournament.");
//        }

        inputData.getBracket().setTournamentCondition(false);
        return this.outputBoundary.presentSuccess("The tournament has been ended successfully.");
    }


}
