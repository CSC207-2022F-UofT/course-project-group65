package useCases.endTourn;

import entities.*;

import java.util.List;
import java.util.Objects;
import java.lang.Math;

/**
 * A use case for ending the tournament.
 */
public class EndTournUC implements EndTournIB{
    private EndTournOB outputBoundary;
    private String currentUser;
    private AccountRepo accounts;
    private BracketRepo brackets;
    private int bracketId;
    private Bracket bracket;
    private User user;

    public EndTournUC(EndTournOB outputBoundary, String currentUser, AccountRepo accounts, BracketRepo brackets,
                      int bracketId) {
        this.outputBoundary = outputBoundary;
        this.currentUser = currentUser;
        this.accounts = accounts;
        this.brackets = brackets;
        this.bracketId = bracketId;
        this.bracket = brackets.getBracket(bracketId);
        this.user = accounts.getUser(currentUser);

    }

    public boolean checkUserRole() {
        return (Objects.equals(this.user.getBracketRole(this.bracketId), "Overseer"));
    }

    // Calculating the total # of rounds in the tournament using log base 2.
    public boolean checkGame() {
        Game finalGame = this.bracket.getFinalGame();
        return (finalGame.getNumTeams() < 2);
    }

    public boolean checkFinalWinner() {
        return (bracket.getFinalGame().getWinner() != null);
    }

    @Override
    public EndTournOD endTourn() {

        if (checkGame()) {
            return this.outputBoundary.presentError("This round is not the final round.");
        }

        if (!checkFinalWinner()) {
            return this.outputBoundary.presentError("No final winner has been decided yet.");
        }

        if (!checkUserRole()) {
            return this.outputBoundary.presentError("You do not have permission to end the tournament.");
        }

        this.bracket.setTournamentCondition(false);
        EndTournOD outputData = new EndTournOD(currentUser, accounts, brackets, bracketId);
        return this.outputBoundary.presentSuccess(outputData);
    }


}
