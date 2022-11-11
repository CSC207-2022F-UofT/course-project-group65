package useCases.generalClasses;
import entities.*;

import java.util.ArrayList;

public class TreeMethods {

    String bracketType;

    public TreeMethods(String bracketType) {
        this.bracketType = bracketType;
    }

    public Game findGame(int gameID, Game head) {
        if (this.bracketType.equals("Default")) {
            return new DefaultBracketMethods().defaultFindGame(gameID, head);
            //return defaultFindGame(gameID, head);
        }
        return null; // Throw bracketType notimplmented exception
    }

    public ArrayList<Game> levelNodes(Game head, int roundNum) {
        if (this.bracketType.equals("Default")) {
            //return defaultLevelNodes(head, roundNum);
            return new DefaultBracketMethods().defaultLevelNodes(head, roundNum);
        }
        return new ArrayList<Game>(); // Throw bracketType notimplmented exception
    }

    public int findHeight(Game head) {
        if (this.bracketType.equals("Default")) {
            //return defaultFindHeight(head);
            return new DefaultBracketMethods().defaultFindHeight(head);
        }
        return -1; // Throw bracketType notimplmented exception
    }
}




