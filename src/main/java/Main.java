import database.AdvanceTeam.AdvanceTeamFileWriter;
import entities.*;
import database.*;
import java.io.*;

import screens.advanceTeam.AdvanceTeamPresenter;
import useCases.advanceTeam.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        AccountRepo repo = new AccountRepo();
//        DefaultUser user = new DefaultUser();
//        user.setUsername("test");
//        user.setPassword("test2");
//        repo.addUser(user);
//
//        DefaultUser user2 = new DefaultUser();
//        user2.setUsername("test3");
//        user2.setPassword("test4");
//        repo.addUser(user2);
//
//        FileOutputStream fileOutputStream
//                = new FileOutputStream("yourfile.txt");
//        ObjectOutputStream objectOutputStream
//                = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(repo);
//        objectOutputStream.flush();
//        objectOutputStream.close();
//
//        FileInputStream fileInputStream
//                = new FileInputStream("yourfile.txt");
//        ObjectInputStream objectInputStream
//                = new ObjectInputStream(fileInputStream);
//        AccountRepo p2 = (AccountRepo) objectInputStream.readObject();
//        objectInputStream.close();
//        System.out.println(p2.getAllUsernames());


//        AccountReadWriter writer = new AccountReadWriter();
//        writer.saveToFile("storage.ser", repo);
//        AccountRepo repo2 = writer.readAccountsFromFile("storage.ser");
//        System.out.println(repo2.getAllUsers().get("test").getUsername());
//        System.out.println(repo.getAllPasswords());
//        System.out.println(repo2.getAllPasswords());




        //Advance Team use case test case

        BracketRepo repo = new BracketRepo();
        AdvanceTeamGateway userRepository = new AdvanceTeamFileWriter("UC Test.txt");
        AdvanceTeamOB outputBoundary = new AdvanceTeamPresenter(); // dummy class
        AdvanceTeamIB interactor = new AdvanceTeamUC(outputBoundary, userRepository);

        DefaultBracket bracket = new DefaultBracket();
        DefaultGame game = new DefaultGame();
        DefaultGame game2 = new DefaultGame();
        DefaultGame game3 = new DefaultGame();
        bracket.setFinalGame(game);
        game.setPrevGame1(game2);
        game.setPrevGame2(game3);
        repo.addBracket(bracket);
        game.setGameStatus(true);

        AccountRepo repo1 = new AccountRepo();
        DefaultUser user2 = new DefaultUser();
        user2.setUsername("test");
        user2.setPassword("test2");
        user2.setBracketRole(bracket.getTournamentID(), "Overseer");
        repo1.addUser(user2);

        AdvanceTeamID inputData = new AdvanceTeamID(0, "test", 0, repo, repo1);
//
        interactor.advanceWinner(inputData);

        FileInputStream fileInputStream
                = new FileInputStream("UC Test.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        BracketRepo p2 = (BracketRepo) objectInputStream.readObject();
        objectInputStream.close();
//
        System.out.println(p2.getBracket(0).getFinalGame().getPrevGame1().getGameStatus());



    }
}

