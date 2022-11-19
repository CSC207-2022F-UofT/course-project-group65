import entities.AccountRepo;
import entities.BracketRepo;
import entities.DefaultUserFactory;
import entities.UserFactory;
import screens.createAccount.CreateAccountController;
import screens.createAccount.CreateAccountPresenter;
import screens.logIn.LogInPresenter;
import useCases.CreateAccount.CreateAccountIB;
import useCases.CreateAccount.CreateAccountOB;
import useCases.CreateAccount.CreateAccountUC;
import useCases.LogIn.LogInOB;

public class TournamentSimulator {
    public static void main(String[] args) {
        AccountRepo mainAccountRepo = new AccountRepo();
        BracketRepo mainBracketRepo = new BracketRepo();
        UserFactory mainUserFactory = new DefaultUserFactory();

        CreateAccountOB createAccountOB = new CreateAccountPresenter();
        CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB, mainUserFactory,
                mainAccountRepo, mainBracketRepo);
        CreateAccountController createAccountController = new CreateAccountController(createAccountIB);

        LogInOB logInOB = new LogInPresenter();

    }
}
