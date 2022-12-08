import entities.AccountRepo;
import entities.BracketRepo;
import frameworks_and_drivers.HomeScreen;
import interface_adapters.NextScreenData;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.log_in.LogInController;
import use_cases.general_classes.InformationRecord;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class TournamentSimulator {
    public static void main(String[] args) {
        Object accountDatabase = new AccountRepo();
        Object bracketDatabase = new BracketRepo();

        // Reads data about the repositories from the files for the program to use.
        try {
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

        // Initialises controllers and classes that keep the state of the program.
        InformationRecord informationRecord = new InformationRecord(accountDatabase, bracketDatabase);
        NextScreenData nextScreenData = new NextScreenData(informationRecord);

        CreateAccountController createAccountController = new CreateAccountController(informationRecord);
        LogInController logInController = new LogInController(informationRecord);

        HomeScreen homeScreen = new HomeScreen(createAccountController, logInController, nextScreenData);
        homeScreen.setVisible(true);

    }
}
