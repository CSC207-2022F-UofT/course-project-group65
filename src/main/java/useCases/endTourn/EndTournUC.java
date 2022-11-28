package useCases.endTourn;

import entities.*;

import java.util.Objects;


/**
 * A use case for ending the tournament.
 */
public class EndTournUC implements EndTournIB{
    private final EndTournOB outputBoundary;
    private final AccountRepo accounts;
    private final BracketRepo brackets;
    private final int bracketId;
    private final Bracket bracket;
    private final User user;

    public EndTournUC(EndTournOB outputBoundary, String currentUser, AccountRepo accounts, BracketRepo brackets,
                      int bracketId) {
        this.outputBoundary = outputBoundary;
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
        EndTournOD outputData = new EndTournOD();
        return this.outputBoundary.presentSuccess(outputData);
    }


    public AccountRepo getAccounts() {
        return accounts;
    }

    public BracketRepo getBrackets() {
        return brackets;
    }
}
