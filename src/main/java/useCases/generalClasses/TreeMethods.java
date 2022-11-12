package useCases.generalClasses;
import entities.*;
import java.util.ArrayList;

public interface TreeMethods {
    Game findGame(int gameID, Game head);

    ArrayList<Game> levelNodes(Game head, int roundNum);

    int findHeight(Game head);
}
