import database.AdvanceTeam.AdvanceTeamFileWriter;
import entities.*;
import database.*;
import java.io.*;

import screens.advanceTeam.AdvanceTeamPresenter;
import useCases.advanceTeam.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Test case for overwriting the file - and yes, it works.
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
//                = new FileOutputStream("overwrite.txt");
//        ObjectOutputStream objectOutputStream
//                = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(repo);
//        objectOutputStream.flush();
//        objectOutputStream.close();
//
//        FileInputStream fileInputStream
//                = new FileInputStream("overwrite.txt");
//        ObjectInputStream objectInputStream
//                = new ObjectInputStream(fileInputStream);
//        AccountRepo p2 = (AccountRepo) objectInputStream.readObject();
//        objectInputStream.close();
//        System.out.println(p2.getAllUsernames());
//
//        DefaultUser user3 = new DefaultUser();
//        user3.setUsername("test5");
//        user3.setPassword("test6");
//        repo.addUser(user3);
//
//        FileOutputStream fileOutputStream2
//                = new FileOutputStream("overwrite.txt");
//        ObjectOutputStream objectOutputStream2
//                = new ObjectOutputStream(fileOutputStream2);
//        objectOutputStream2.writeObject(repo);
//        objectOutputStream2.flush();
//        objectOutputStream2.close();
//
//        FileInputStream fileInputStream2
//                = new FileInputStream("overwrite.txt");
//        ObjectInputStream objectInputStream2
//                = new ObjectInputStream(fileInputStream2);
//        AccountRepo p3 = (AccountRepo) objectInputStream2.readObject();
//        objectInputStream2.close();
//        System.out.println(p3.getAllUsernames());




        //Advance Team use case test case

//        BracketRepo repo = new BracketRepo();
//        AdvanceTeamGateway userRepository = new AdvanceTeamFileWriter("UC Test.txt");
//        AdvanceTeamOB outputBoundary = new AdvanceTeamPresenter(); // dummy class
//        AdvanceTeamIB interactor = new AdvanceTeamUC(outputBoundary, userRepository);
//
//        DefaultBracket bracket = new DefaultBracket();
//        DefaultGame game = new DefaultGame();
//        DefaultGame game2 = new DefaultGame();
//        DefaultGame game3 = new DefaultGame();
//        bracket.setFinalGame(game);
//        game.setPrevGame1(game2);
//        game.setPrevGame2(game3);
//        repo.addBracket(bracket);
//        game.setGameStatus(true);
//
//        AccountRepo repo1 = new AccountRepo();
//        DefaultUser user2 = new DefaultUser();
//        user2.setUsername("test");
//        user2.setPassword("test2");
//        user2.setBracketRole(bracket.getTournamentID(), "Overseer");
//        repo1.addUser(user2);
//
//        AdvanceTeamID inputData = new AdvanceTeamID(0, "test", 0, repo, repo1);
////
//        interactor.advanceWinner(inputData);
//
//        FileInputStream fileInputStream
//                = new FileInputStream("UC Test.txt");
//        ObjectInputStream objectInputStream
//                = new ObjectInputStream(fileInputStream);
//        BracketRepo p2 = (BracketRepo) objectInputStream.readObject();
//        objectInputStream.close();
//
//        // So it saves all the information
//        System.out.println(p2.getBracket(0).getFinalGame().getGameStatus());
//
//        // But it saves it in a completely new object.
//        System.out.println(p2.getBracket(0).getFinalGame() == game);



    }
}

