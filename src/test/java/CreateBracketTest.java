import entities.*;
import interface_adapters.create_bracket.CreateBracketFailed;
import interface_adapters.create_bracket.CreateBracketPresenter;
import interface_adapters.data_interface_adapters.create_bracket_data.CreateBracketFileWriter;
import org.junit.Test;
import use_cases.create_bracket.*;
import use_cases.general_classes.InformationRecord;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of CreateBracketUC (interactor).
 */
public class CreateBracketTest {
    /**
     * This tests CreateBracketUC, given a bracket type, bracket name, number of teams, max team size, and win condition
     * (the points needed to advance to the next round). It tests that its output data matches our expected values.
     */
    @Test
    public void create() {
        CreateBracketPresenter createBracketPresenter = new CreateBracketPresenter() {
            @Override
            public CreateBracketOD presentSuccess(CreateBracketOD viewData) {
                assertNotNull(viewData.getBrackets().getBracket(1));
                assertEquals(viewData.getUsername(), "Mario");
                assertEquals(viewData.getAccounts().getAllPasswords().get(0), "mario123");
                assertEquals(viewData.getBracketID(), 1);
                assertEquals(viewData.getTeams().get(0), "BlankTeam 1");
                return null;
            }
            @Override
            public CreateBracketOD presentError(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };


        AccountRepo accountRepo = new AccountRepo();
        BracketRepo bracketRepo = new BracketRepo();
        String currentUser = "Mario";
        String password = "mario123";
        String accountsFile = "filename1.txt";
        String bracketsFile = "filename2.txt";
        User mario = new DefaultUser();
        mario.setUsername(currentUser);
        mario.setPassword(password);
        accountRepo.addUser(mario);
        InformationRecord informationRecord = new InformationRecord(accountRepo, bracketRepo);
        CreateBracketGateway gateway = new CreateBracketFileWriter(accountsFile, bracketsFile);
        CreateBracketIB userInput = new CreateBracketUC(createBracketPresenter, gateway, currentUser, informationRecord);
        CreateBracketID createBracketID = new CreateBracketID("Default", "Mario's tournament",
                8, 7, 1);
        userInput.create(createBracketID);
    }

    /**
     * This tests CreateBracketUC which should present error when given an empty string ("") for its team name.
     */
    @Test
    public void createBracketEmptyTeamName(){
        CreateBracketOB presenter = new CreateBracketPresenter(){
            @Override
            public CreateBracketOD presentSuccess(CreateBracketOD outputData) {
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public CreateBracketOD presentError(String error) {
                throw new CreateBracketFailed(error);
            }
        };

        AccountRepo accountRepo = new AccountRepo();
        BracketRepo bracketRepo = new BracketRepo();
        String currentUser = "Mario";
        String password = "mario123";
        String accountsFile = "filename1.txt";
        String bracketsFile = "filename2.txt";
        User mario = new DefaultUser();
        mario.setUsername(currentUser);
        mario.setPassword(password);
        accountRepo.addUser(mario);
        InformationRecord informationRecord = new InformationRecord(accountRepo, bracketRepo);
        CreateBracketGateway gateway = new CreateBracketFileWriter(accountsFile, bracketsFile);
        CreateBracketIB interactor = new CreateBracketUC(presenter, gateway, currentUser, informationRecord);
        CreateBracketID createBracketID = new CreateBracketID("Default", "",
                9, 7, 1);



        Exception exception = assertThrows(CreateBracketFailed.class, () ->
                interactor.create(createBracketID));
        assertEquals("Please enter a name for your bracket.", exception.getMessage());
    }
}
