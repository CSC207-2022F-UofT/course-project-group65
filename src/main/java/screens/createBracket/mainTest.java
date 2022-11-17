package screens.createBracket;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.DefaultUser;
import entities.User;
import screens.bracketView;
import useCases.createBracket.CreateBracketIB;
import useCases.createBracket.CreateBracketOB;
import useCases.createBracket.CreateBracketUC;

import java.util.ArrayList;
import java.util.Arrays;

public class mainTest {
    public static void main(String[] args) {
        BracketRepo brackets = new BracketRepo();
        AccountRepo accounts = new AccountRepo();
        User user = new DefaultUser();
        user.setUsername("test");
        user.setPassword("password");
        accounts.addUser(user);

        CreateBracketOB createBracketOB = new CreateBracketPresenter();
        CreateBracketIB interactor = new CreateBracketUC(createBracketOB, "test", accounts, brackets);
        CreateBracketController controller = new CreateBracketController(interactor);

        enterBracketInfo frame = new enterBracketInfo(controller);
        frame.setCurrentUser(user.getUsername()); // This would instead be set using Output Data from prev screen
        bracketView view = new bracketView();

//        view.setVisible(true);
        frame.setVisible(true);

//        Example of setting up an ongoing Bracket View, you would get the display data from
//        a previous screen (likely through output data)
        bracketView view2 = new bracketView();
        view2.setCurrentUser("test user");
        view2.setBracketName("Test Bracket");
        view2.setCurrentTournament(12);
//        view2.setGame1Label("Team 3", "", 0, 0); would do this after advancing a team
//        view2.setGame1Label("Team 3", "Team 4", 0, 0); caveats: you need both teams if your adding
//        the second team <- for Aditya Khan, on view update
        view2.setGame2Label("Team 1", "Team 2", 12, 13 );
        view2.setGame3Label("Team 3", "Team 4", 11, 5 );
        view2.setTeam1Name("Team 1");
        view2.setTeam1PlayerList(new String[]{"Player 1", "Player 2", "Player 3"});
        view2.setTeam2Name("Team 2");
        view2.setTeam2PlayerList(new String[]{"Player 4", "Player 5", "Player 6"});
        view2.setTeam3Name("Team 3");
        view2.setTeam3PlayerList(new String[]{"Player 7", "Player 8", "Player 9"});
        view2.setTeam4Name("Team 4");
        view2.setTeam4PlayerList(new String[]{"Player 10", "Player 11", "Player 12"});
        view2.setObserverListGame1(new String[]{"Observer 1", "Observer 2", "Observer 3"});
        view2.setObserverListGame2(new String[]{"Observer 1", "Observer 2", "Observer 3"});
        view2.setObserverListGame3(new String[]{"Observer 1", "Observer 2", "Observer 3"});
//        All above is needed for the bracket view to be set up, however some fields may be blank
//        So your output data may have some "blank" or meaningless fields, you have to check for
//        this before you set the field, as we want to field to be left as the default view, if
//        that part of the tournament has yet to be played.

//        view2.setVisible(true);

//        Note that things like the PlayerList and ObserverList are String arrays, so you must convert ArrayLists
//        to String arrays before you set them, you can do this with the following code:
        // Creating an empty ArrayList of string type
        ArrayList<String> al = new ArrayList<String>();

        // Populating the ArrayList by custom elements
        al.add("Anshul Aggarwal");
        al.add("Mayank Solanki");
        al.add("Abhishek Kelenia");
        al.add("Vivek Gupta");

        String[] str = new String[al.size()];

        for (int i = 0; i < al.size(); i++) {
            str[i] = al.get(i);
        }

//        System.out.println("ArrayList to String array : " + Arrays.toString(str));
        // Printing using for each loop
//        for (String k : str) {
//            System.out.println(k);
//        }

    }
}
