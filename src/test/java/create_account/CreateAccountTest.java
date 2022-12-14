package create_account;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.DefaultUser;
import interface_adapters.create_account.CreateAccountFailed;
import interface_adapters.create_account.CreateAccountPresenter;
import org.junit.Before;
import org.junit.Test;
import use_cases.create_account.*;
import use_cases.general_classes.InformationRecord;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the test class for createAccount use case
 */
public class CreateAccountTest {
    /**
     * The informationRecord variable used to record all the information
     */
    InformationRecord info;

    /**
     * This method is for setting up all the information needed for testing in the bracket
     */
    @Before
    public void setUp() {
        DefaultUser user = new DefaultUser();
        user.setUsername("test");
        user.setPassword("testPassword");
        AccountRepo accountRepo = new AccountRepo();
        accountRepo.addUser(user);
        BracketRepo bracketRepo = new BracketRepo();
        info = new InformationRecord(accountRepo, bracketRepo);
    }

    /**
     * This method tests when the user is already exists, whether the output will be correct
     * for the user wants to create an account
     */
    @Test
    public void testUserAlreadyExists() {
        CreateAccountGateway gateway = new CreateAccountDummyFileWriter();
        CreateAccountOB presenter = new CreateAccountPresenter() {
            @Override
            public CreateAccountOD prepareSuccessView(CreateAccountOD accountOD) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public CreateAccountOD prepareFailView(String message) {
                throw new CreateAccountFailed(message);
            }
        };

        CreateAccountUC createAccountUC = new CreateAccountUC(presenter, info, gateway);
        CreateAccountID requestModel = new CreateAccountID("test", "testPassword");
        Exception exception = assertThrows(CreateAccountFailed.class, () -> createAccountUC.create(requestModel));
        assertEquals("User already exists.", exception.getMessage());
    }

    /**
     * This method tests when the user successfully create an account, whether the output will be correct
     */
    @Test
    public void testCreateAccount() {
        CreateAccountGateway gateway = new CreateAccountDummyFileWriter();
        CreateAccountOB presenter = new CreateAccountPresenter() {
            @Override
            public CreateAccountOD prepareSuccessView(CreateAccountOD accountOD) {
                return accountOD;
            }

            @Override
            public CreateAccountOD prepareFailView(String message) {
                fail("Unexpected failure");
                return null;
            }
        };

        CreateAccountUC createAccountUC = new CreateAccountUC(presenter, info, gateway);
        CreateAccountID requestModel = new CreateAccountID("test2", "testPassword2");
        CreateAccountOD accountOD = createAccountUC.create(requestModel);
        assertEquals("test2", accountOD.getUsername());
        assertEquals("testPassword2", accountOD.getPassword());
    }

    /**
     * This method tests when there are two accounts have the same passwords, whether the output will be correct
     * for the user wants to create an account
     */
    @Test
    public void testCreateTwoAccountsSamePassWords() {
        CreateAccountGateway gateway = new CreateAccountDummyFileWriter();
        CreateAccountOB presenter = new CreateAccountPresenter() {
            @Override
            public CreateAccountOD prepareSuccessView(CreateAccountOD accountOD) {
                return accountOD;
            }

            @Override
            public CreateAccountOD prepareFailView(String message) {
                fail("Unexpected failure");
                return null;
            }
        };

        CreateAccountUC createAccountUC = new CreateAccountUC(presenter, info, gateway);
        CreateAccountID requestModel = new CreateAccountID("test2", "testPassword2");
        CreateAccountOD accountOD = createAccountUC.create(requestModel);
        assertEquals("test2", accountOD.getUsername());
        assertEquals("testPassword2", accountOD.getPassword());

        CreateAccountID requestModel2 = new CreateAccountID("test3", "testPassword2");
        CreateAccountOD accountOD2 = createAccountUC.create(requestModel2);
        assertEquals("test3", accountOD2.getUsername());
        assertEquals("testPassword2", accountOD2.getPassword());
    }
}
