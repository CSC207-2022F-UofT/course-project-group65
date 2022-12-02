package useCases.createBracket;

import entities.DefaultGame;
import entities.Game;
import entities.Team;

import java.util.*;

/**
 * This class represents the default games factory. This class is used to create
 * a default game which simply keeps track of the teams that are playing in the game, the points
 * of each team, and the winner.
 * This class is part of the factory design pattern, and used in the builder design pattern giving the class
 * much flexibility, allowing us to create games of arbitrary sizes.
 * Note: In our program, the games are the main structure of the actual bracket itself, and are represented
 * in the form of a tree (where the root of the tree/head game represents the final game).
 * When first created all the games that will be used in the tournament are created
 * and default teams are assigned to the leaves (starting games) of the tree. These values are updated
 * as new teams are created, new users join, and as the tournament progresses.
 * This means the default teams have to be created before the games are created.
 */
public class DefaultGamesFactory implements GamesFactory {

    /**
     * A helper method for the getGames method.
     * This method is used to create and insert a new game into the tree/bracket.
     * @param root - the root of the tree/bracket (head game, final round of the bracket)
     * @param id - the unique ID of the game (each game in the bracket has a unique ID)
     *           (Note: this is not the same as the unique ID of the bracket, as games in different brackets
     *           may have the same ID. As long as with in the same bracket, the IDs are unique).
     */
    public void insertValue(Game root, int id) {
        Queue<Game> queue = new LinkedList<>();
        queue.add(root);
        root.setGameID(1);
        DefaultGame newGame = new DefaultGame();

        while (!queue.isEmpty()) {
            root = queue.peek();
            queue.remove();

            if (root.getPrevGame1() == null) {
                root.setPrevGame1(newGame);
                newGame.setGameID(id+1);

                break;
            } else
                queue.add(root.getPrevGame1());

            if (root.getPrevGame2() == null) {
                root.setPrevGame2(newGame);
                newGame.setGameID(id+1);
                break;
            } else
                queue.add(root.getPrevGame2());
        }
    }

    /** A helper method for the getGames method.
     * This method is used to update the round parameter in each game, so
     * that it refers to the correct round of the bracket the game is in.
     * The round a game is in is analogous to the height of the game in the tree/bracket.
     * @param root - the root of the tree/bracket (head game, final round of the bracket)
     * @param rounds - the total number of rounds in the bracket, equivalent to the height of the tree
     */
    public void setRounds(Game root, int rounds){
        if (root.getPrevGame1() == null && root.getPrevGame2() == null)
            root.setGameRound(rounds);
        else {
            root.setGameRound(rounds);
            setRounds(root.getPrevGame1(), rounds - 1);
            setRounds(root.getPrevGame2(), rounds - 1);
        }
    }

    /** A helper method for the getGames method.
     * This method is used to put the default teams into the starting games of the bracket.
     * (This is the first round of the bracket, which are the leaves of the tree).
     * @param root - the root of the tree/bracket (head game, final round of the bracket)
     * @param teams - the list of teams in the bracket
     */
    public void setTeams(Game root, List<Team> teams){
        if (root.getPrevGame1() == null && root.getPrevGame2() == null) {
            root.setTeam(teams.get(1), 0);
            root.setTeam(teams.get(0), 0);
            root.setNumTeams(2);
            teams.remove(0);
            teams.remove(0);
        }
        else {
            setTeams(root.getPrevGame1(), teams);
            setTeams(root.getPrevGame2(), teams);
        }
    }

    /**
     * This is the main method which creates all the games in the bracket based on the number of teams.
     * It also correctly assigns all the teams to the starting games of the bracket, and updates the round
     * parameter in each game, so we can identify all the games in a specific round of the bracket.
     * @param numTeams - the number of teams in the bracket
     * @param teams - the list of teams in the bracket
     * @return - the head game of the bracket (the final game of the bracket)
     */
    public DefaultGame getGames(int numTeams, List<Team> teams)
    {
        DefaultGame root = new DefaultGame();
        int rounds = (int) (Math.log(numTeams) / Math.log(2));
        int numGames = (int) (Math.pow(2, rounds) - 1);
        for (int i = 1; i < numGames; i++) {
            insertValue(root, i);
        }
        setRounds(root, rounds);
        List<Team> clonedTeams = new ArrayList<>(teams);
        setTeams(root, clonedTeams);
        return root;
    }
}

