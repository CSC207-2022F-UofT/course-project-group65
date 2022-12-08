package log_in;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.DefaultUser;
import interface_adapters.log_in.LogInFailed;
import interface_adapters.log_in.LogInPresenter;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.general_classes.InformationRecord;
import use_cases.log_in.LogInID;
import use_cases.log_in.LogInOB;
import use_cases.log_in.LogInOD;
import use_cases.log_in.LogInUC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class tests the functionality of the LogIn usecase
 */
public class LogInTest {

    InformationRecord info;

    @BeforeEach
    public void setUp() {
        DefaultUser user = new DefaultUser();
        user.setUsername("test");
        user.setPassword("testPassword");

        DefaultUser user2 = new DefaultUser();
        user2.setUsername("test2");
        user2.setPassword("testPassword2");

        AccountRepo accountRepo = new AccountRepo();
        accountRepo.addUser(user);
        accountRepo.addUser(user2);
        BracketRepo bracketRepo = new BracketRepo();
        info = new InformationRecord(accountRepo, bracketRepo);
    }

    /**
     * This tests that LogInUC throws the correct exception if the username inputed by the user does not exist and
     * the user cannot log in
     */
    @Test
    public void testUsernameDNE() {
        LogInOB presenter = new LogInPresenter() {
            @Override
            public LogInOD prepareSuccessView(LogInOD view) {
                Assertions.fail("Username does not exist, should not be able to log in");
                return null;
            }

            @Override
            public LogInOD prepareFailView(String message) {
                throw new LogInFailed(message);
            }
        };
        System.out.println(info);
        LogInUC useCase = new LogInUC(presenter, info);
        LogInID inputData = new LogInID("test3", "testPassword");
        Exception exception = assertThrows(LogInFailed.class, () -> useCase.logIn(inputData));
        assertEquals("username and/or password is incorrect", exception.getMessage());
    }

    /**
     * This tests that LogInUC throws the correct exception if the password inputed by the user does not macth the
     * password that map to the username so the user cannot log in
     */
    @Test
    public void testPasswordIncorrect() {
        LogInOB presenter = new LogInPresenter() {
            @Override
            public LogInOD prepareSuccessView(LogInOD view) {
                Assertions.fail("Password is incorrect, should not be able to log in");
                return null;
            }

            @Override
            public LogInOD prepareFailView(String message) {
                throw new LogInFailed(message);
            }
        };
        LogInUC useCase = new LogInUC(presenter, info);
        LogInID inputData = new LogInID("test", "testPassword2");
        Exception exception = assertThrows(LogInFailed.class, () -> useCase.logIn(inputData));
        assertEquals("username and/or password is incorrect", exception.getMessage());
    }

    /**
     * This tests that LogInUC runs and user successfully logs in if the password and username both match
     */
    @Test
    public void testLogInSuccess() {
        LogInOB presenter = new LogInPresenter() {
            @Override
            public LogInOD prepareSuccessView(LogInOD view) {
                return view;
            }

            @Override
            public LogInOD prepareFailView(String message) {
                Assertions.fail("Username and password are correct, should be able to log in");
                return null;
            }
        };
        LogInUC useCase = new LogInUC(presenter, info);
        LogInID inputData = new LogInID("test", "testPassword");
        LogInOD outputData = useCase.logIn(inputData);
        assertEquals("test", outputData.getUsername());
    }

    @After
    public void tearDown() {
        java.io.File file = new java.io.File("filename.txt");
        boolean deletion = file.delete();
        System.out.println(deletion);
    }

}
