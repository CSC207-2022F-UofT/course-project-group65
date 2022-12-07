package end_tournament;

import entities.*;
import interface_adapters.data_interface_adapters.end_tournament_data.EndTournFileWriter;
import interface_adapters.end_tournament.EndTournFailed;
import interface_adapters.end_tournament.EndTournPresenter;
import org.junit.After;
import org.junit.Test;
import use_cases.end_tournament.*;
import use_cases.general_classes.InformationRecord;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of EndTournUC (interactor).
 */
public class EndTournTest {
    /**
     * This tests EndTournUC, given a AccountRepo, a BracketRepo, username, and a gateway which pass all the checks
     * together (all the conditions needed are met to end the tournament).
     */
    @Test
    public void end() {
        EndTournPresenter endTournPresenter = new EndTournPresenter() {
            @Override
            public EndTournOD presentSuccess(EndTournOD outputData) {
                System.out.println("Success expected");
                return null;
            }

            @Override
            public EndTournOD presentError(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        int bracketId = 123;
        String userName = "Mario";
        String password = "Mario123";
        Game game = new DefaultGame();
        AccountRepo accountRepo = new AccountRepo();
        User user = new DefaultUser();
        user.setUsername(userName);
        user.setPassword(password);
        user.setBracketRole(123, "Overseer");
        user.setCurrentTournament(123);
        accountRepo.addUser(user);
        BracketRepo bracketRepo = new BracketRepo();
        Bracket bracket = new DefaultBracket();
        bracket.setTournamentID(123);
        bracket.setTeamSize(4);
        bracket.setTournamentName("207 Tournament");
        bracket.setFinalGame(game);
        bracket.setTournamentCondition(true);
        Team winner = new DefaultTeam();
        game.setNumTeams(2);
        game.setWinner(winner);
        bracketRepo.addBracket(bracket);
        InformationRecord informationRecord = new InformationRecord(accountRepo, bracketRepo);
        String filename = "filename.txt";
        EndTournGateway gateway = new EndTournFileWriter(filename);
        EndTournIB userInput = new EndTournUC(endTournPresenter, userName, informationRecord, bracketId, gateway);


        userInput.endTourn();
    }

    /**
     * This tests EndTournUC which should present error when at least one of the necessary conditions to end the
     * tournament is not met (in this case, the round is not the final round).
     */
    @Test
    public void endTournamentNotFinalRound() {


        EndTournGateway testGateway = new EndTournFileWriter("filename");

        EndTournOB presenter = new EndTournPresenter() {
            @Override
            public EndTournOD presentSuccess(EndTournOD outputData) {
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public EndTournOD presentError(String error) {
                throw new EndTournFailed(error);
            }
        };

        int bracketId = 123;
        String userName = "Mario";
        String password = "Mario123";
        Game game = new DefaultGame();
        AccountRepo accountRepo = new AccountRepo();
        User user = new DefaultUser();
        user.setUsername(userName);
        user.setPassword(password);
        user.setBracketRole(123, "Player");
        user.setCurrentTournament(123);
        accountRepo.addUser(user);
        BracketRepo bracketRepo = new BracketRepo();
        Bracket bracket = new DefaultBracket();
        bracket.setTournamentID(123);
        bracket.setTeamSize(4);
        bracket.setTournamentName("207 Tournament");
        bracket.setFinalGame(game);
        bracketRepo.addBracket(bracket);
        InformationRecord informationRecord = new InformationRecord(accountRepo, bracketRepo);
        EndTournIB interactor = new EndTournUC(presenter, userName, informationRecord, bracketId, testGateway);


        Exception exception = assertThrows(EndTournFailed.class, interactor::endTourn);
        assertEquals("This round is not the final round.", exception.getMessage());
    }

    @After
    public void tearDown() {
        java.io.File file = new java.io.File("filename.txt");
        boolean deletion = file.delete();
    }

}
