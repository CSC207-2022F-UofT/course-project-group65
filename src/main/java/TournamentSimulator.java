import entities.*;
import screens.createAccount.CreateAccountController;
import screens.createAccount.CreateAccountPresenter;
import screens.createBracket.CreateBracketPresenter;
import screens.homeScreen;
import screens.logIn.LogInController;
import screens.logIn.LogInPresenter;
import useCases.CreateAccount.CreateAccountIB;
import useCases.CreateAccount.CreateAccountOB;
import useCases.CreateAccount.CreateAccountUC;
import useCases.LogIn.LogInIB;
import useCases.LogIn.LogInOB;
import useCases.LogIn.LogInUC;
import useCases.createBracket.CreateBracketOB;
import useCases.createBracket.CreateBracketUC;

public class TournamentSimulator {
    public static void main(String[] args) {
        AccountRepo mainAccountRepo = new AccountRepo();
        BracketRepo mainBracketRepo = new BracketRepo();

//        DefaultBracket defaultBracket = new DefaultBracket();
//        defaultBracket.setTournamentName("test");
//        defaultBracket.setPlayerInvite();
//        mainBracketRepo.addBracket(defaultBracket);
//
//        Team team1 = new DefaultTeam();
//        team1.setTeamName("test 1");
//        Team team2 = new DefaultTeam();
//        team2.setTeamName("test 2");
//        Team team3 = new DefaultTeam();
//        team3.setTeamName("test 3");
//        Team team4 = new DefaultTeam();
//        team4.setTeamName("test 4");
//
//        team1.setTeamSize(2);
//        team2.setTeamSize(2);
//        team3.setTeamSize(2);
//        team4.setTeamSize(2);
//
//        defaultBracket.addTeam(team1);
//        defaultBracket.addTeam(team2);
//        defaultBracket.addTeam(team3);
//        defaultBracket.addTeam(team4);

//        CreateBracketOB presenter = new CreateBracketPresenter();
//
//        CreateBracketUC createBracketUC = new CreateBracketUC(presenter, mainBracketRepo);

//        UserFactory mainUserFactory = new DefaultUserFactory();

        CreateAccountOB createAccountOB = new CreateAccountPresenter();
//        CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB, mainUserFactory,
//                mainAccountRepo, mainBracketRepo);
        CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB,
                mainAccountRepo, mainBracketRepo);
        CreateAccountController createAccountController = new CreateAccountController(createAccountIB);

        LogInOB logInOB = new LogInPresenter();
        LogInIB logInIB = new LogInUC(logInOB, mainAccountRepo, mainBracketRepo);
        LogInController logInController = new LogInController(logInIB);

        homeScreen homeScreen = new homeScreen(createAccountController, logInController);
        homeScreen.setVisible(true);

    }
}
