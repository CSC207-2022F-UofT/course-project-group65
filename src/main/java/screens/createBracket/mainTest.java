package screens.createBracket;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.DefaultUser;
import entities.User;
import useCases.createBracket.CreateBracketIB;
import useCases.createBracket.CreateBracketOB;
import useCases.createBracket.CreateBracketUC;

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
        frame.setVisible(true);
    }
}
