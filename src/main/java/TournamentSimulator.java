import entities.*;
import screens.NextScreenData;
import screens.createAccount.CreateAccountController;
import screens.homeScreen;
import screens.logIn.LogInController;
import use_cases.general_classes.InformationRecord;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class TournamentSimulator {
    public static void main(String[] args) {
//        Object accountDatabase = null;
//        Object bracketDatabase = null;
        Object accountDatabase = new AccountRepo();
        Object bracketDatabase = new BracketRepo();

        try{
            FileInputStream fileInputStream
                    = new FileInputStream("accounts.txt");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            accountDatabase = objectInputStream.readObject();
            objectInputStream.close();

            FileInputStream fileInputStreamBracket
                    = new FileInputStream("brackets.txt");
            ObjectInputStream objectInputStreamBracket
                    = new ObjectInputStream(fileInputStreamBracket);
            bracketDatabase = objectInputStreamBracket.readObject();
            objectInputStreamBracket.close();

        } catch (Exception e) {
            System.out.println("Persistence comes into effect after first run");
        }

        InformationRecord informationRecord = new InformationRecord(accountDatabase, bracketDatabase);
        NextScreenData nextScreenData = new NextScreenData(informationRecord);

        // CreateAccountOB createAccountOB = new CreateAccountPresenter();
//        CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB, mainUserFactory,
//                mainAccountRepo, mainBracketRepo);
        // CreateAccountGateway gateway = new CreateAccountFileWriter("accounts.txt");
//        CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB,
//                accountDatabase, bracketDatabase);
        CreateAccountController createAccountController = new CreateAccountController(informationRecord);

        LogInController logInController = new LogInController(informationRecord);
        //LogInOB logInOB = new LogInPresenter();
        //LogInIB logInIB = new LogInUC(logInOB, accountDatabase, bracketDatabase);
        //LogInController logInController = new LogInController(logInIB);

        homeScreen homeScreen = new homeScreen(createAccountController, logInController, nextScreenData);
        homeScreen.setVisible(true);

    }
}
