package start_tournament;

import entities.*;
import interface_adapters.data_interface_adapters.start_tournament_data.StartTournFilerWriter;
import interface_adapters.start_tournament.StartTournPresenter;
import org.junit.After;
import org.junit.Test;
import use_cases.general_classes.InformationRecord;
import use_cases.start_tournament.StartTournGateway;
import use_cases.start_tournament.StartTournIB;
import use_cases.start_tournament.StartTournOD;
import use_cases.start_tournament.StartTournUC;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class tests the functionality of StartTournUC (interactor).
 */
public class StartTournTest {
    /**
     * This tests StartTournUC, given a AccountRepo, a BracketRepo, username, and a gateway which does not
     * pass all the checks together (all the conditions needed are not met to start the tournament). Then it
     * tests its output data matches our expected values, especially in this case, the type of errors.
     */
    @Test
    public void start() {
        String errorType2 = "NUMTEAMS";
        String errorType3 = "NOOBSERVER";
        String errorType4 = "TEAMNOTFULL";

        StartTournPresenter startTournPresenter = new StartTournPresenter() {
            @Override
            public StartTournOD presentSuccess(StartTournOD outputData) {
                assertTrue(outputData.getErrors().contains(errorType2));
                assertTrue(outputData.getErrors().contains(errorType3));
                assertTrue(outputData.getErrors().contains(errorType4));
                return null;
            }

            @Override
            public StartTournOD presentError(String errorMessage) {
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
        bracketRepo.addBracket(bracket);
        InformationRecord informationRecord = new InformationRecord(accountRepo, bracketRepo);
        String filename = "filename.txt";
        StartTournGateway gateway = new StartTournFilerWriter(filename);
        StartTournIB userInput = new StartTournUC(startTournPresenter, userName, informationRecord, bracketId, gateway);


        userInput.startTourn();
    }

    @After
    public void tearDown() {
        java.io.File file = new java.io.File("filename.txt");
        boolean deletion = file.delete();
        System.out.println("File deleted: " + deletion);
    }

}
