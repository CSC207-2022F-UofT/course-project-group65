package useCases.generalClasses.traversalStrategies;
import entities.Game;
import java.util.ArrayList;

public class TreeMethods {

    String bracketType;

    /**
     * Access to for different tree methods based on bracket type.
     * @param bracketType The type of bracket to be used
     */

    public TreeMethods(String bracketType) {
        this.bracketType = bracketType;
    }

    public Game findGame(int gameID, Game head) {
        if (this.bracketType.equals("Default")) {
            return new DefaultBracketMethods().defaultFindGame(gameID, head);
        }
        throw new RuntimeException("Bracket type not implemented");
    }

    public ArrayList<Game> levelNodes(Game head, int roundNum) {
        if (this.bracketType.equals("Default")) {
            return new DefaultBracketMethods().defaultLevelNodes(head, roundNum);
        }
        throw new RuntimeException("Bracket type not implemented");
    }

    public int findHeight(Game head) {
        if (this.bracketType.equals("Default")) {
            //return defaultFindHeight(head);
            return new DefaultBracketMethods().defaultFindHeight(head);
        }
        throw new RuntimeException("Bracket type not implemented");
    }
}




