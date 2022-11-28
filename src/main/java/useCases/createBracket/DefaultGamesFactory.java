package useCases.createBracket;

import entities.DefaultGame;
import entities.DefaultTeam;
import entities.Game;
import entities.Team;

import java.util.*;

public class DefaultGamesFactory implements GamesFactory {
    /*
     * This is a factory for creating a game.
     */

    public void insertValue(Game root, int id, int rounds) {
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

    public void setRounds(Game root, int rounds){
        if (root == null)
            return;
        else if (root.getPrevGame1() == null && root.getPrevGame2() == null)
            root.setGameRound(rounds);
        else {
            root.setGameRound(rounds);
            setRounds(root.getPrevGame1(), rounds - 1);
            setRounds(root.getPrevGame2(), rounds - 1);
        }
    }

    public void setTeams(Game root, List<Team> teams){
        if (root == null)
            return;
        else if (root.getPrevGame1() == null && root.getPrevGame2() == null) {
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

    public DefaultGame getGames(int numTeams, List<Team> teams)
    {
        DefaultGame root = new DefaultGame();
        int rounds = (int) (Math.log(numTeams) / Math.log(2));
        int numGames = (int) (Math.pow(2, rounds) - 1);
        for (int i = 1; i < numGames; i++) {
            insertValue(root, i, rounds);
        }
        setRounds(root, rounds);
        List<Team> clonedTeams = new ArrayList<>(teams);
        setTeams(root, clonedTeams);
        return root;
    }

//    public DefaultGame getGames(int numTeams, List<Team> teams) {
//        if (numTeams == 2) {
//            DefaultGame headGame = new DefaultGame();
//
//            headGame.setGameRound(1);
//            headGame.setGameID(1);
//
//            headGame.setTeam(teams.get(0), 0);
//            headGame.setTeam(teams.get(1), 0);
//            headGame.setNumTeams(2);
//            return headGame;
//
//        } else if (numTeams == 4) {
//            DefaultGame headGame = new DefaultGame();
//            DefaultGame leftGame = new DefaultGame();
//            DefaultGame rightGame = new DefaultGame();
//            headGame.setPrevGame1(leftGame);
//            headGame.setPrevGame2(rightGame);
//
//            headGame.setGameRound(2);
//            headGame.setGameID(1);
//
//            leftGame.setGameRound(1);
//            leftGame.setGameID(2);
//            rightGame.setGameRound(1);
//            rightGame.setGameID(3);
//
//            leftGame.setTeam(teams.get(0), 0);
//            leftGame.setTeam(teams.get(1), 0);
//            leftGame.setNumTeams(2);
//
//            rightGame.setTeam(teams.get(2), 0);
//            rightGame.setTeam(teams.get(3), 0);
//            rightGame.setNumTeams(2);
//            return headGame;
//
//        } else if (numTeams == 8) {
//            DefaultGame headGame = new DefaultGame();
//            DefaultGame leftGame = new DefaultGame();
//            DefaultGame rightGame = new DefaultGame();
//            headGame.setPrevGame1(leftGame);
//            headGame.setPrevGame2(rightGame);
//            DefaultGame leftLeftGame = new DefaultGame();
//            DefaultGame leftRightGame = new DefaultGame();
//            DefaultGame rightLeftGame = new DefaultGame();
//            DefaultGame rightRightGame = new DefaultGame();
//            leftGame.setPrevGame1(leftLeftGame);
//            leftGame.setPrevGame2(leftRightGame);
//            rightGame.setPrevGame1(rightLeftGame);
//            rightGame.setPrevGame2(rightRightGame);
//
//            headGame.setGameRound(3);
//            headGame.setGameID(1);
//
//            leftGame.setGameRound(2);
//            leftGame.setGameID(2);
//            rightGame.setGameRound(2);
//            rightGame.setGameID(3);
//
//            leftLeftGame.setGameRound(1);
//            leftLeftGame.setGameID(4);
//            leftRightGame.setGameRound(1);
//            leftRightGame.setGameID(5);
//            rightLeftGame.setGameRound(1);
//            rightLeftGame.setGameID(6);
//            rightRightGame.setGameRound(1);
//            rightRightGame.setGameID(7);
//
//            leftLeftGame.setTeam(teams.get(0), 0);
//            leftLeftGame.setTeam(teams.get(1), 0);
//            leftLeftGame.setNumTeams(2);
//            leftRightGame.setTeam(teams.get(2), 0);
//            leftRightGame.setTeam(teams.get(3), 0);
//            leftRightGame.setNumTeams(2);
//            rightLeftGame.setTeam(teams.get(4), 0);
//            rightLeftGame.setTeam(teams.get(5), 0);
//            rightLeftGame.setNumTeams(2);
//            rightRightGame.setTeam(teams.get(6), 0);
//            rightRightGame.setTeam(teams.get(7), 0);
//            rightRightGame.setNumTeams(2);
//            return headGame;
//
//        } else if (numTeams == 16) {
//            DefaultGame headGame = new DefaultGame();
//            DefaultGame leftGame = new DefaultGame();
//            DefaultGame rightGame = new DefaultGame();
//            headGame.setPrevGame1(leftGame);
//            headGame.setPrevGame2(rightGame);
//            DefaultGame leftLeftGame = new DefaultGame();
//            DefaultGame leftRightGame = new DefaultGame();
//            DefaultGame rightLeftGame = new DefaultGame();
//            DefaultGame rightRightGame = new DefaultGame();
//            leftGame.setPrevGame1(leftLeftGame);
//            leftGame.setPrevGame2(leftRightGame);
//            rightGame.setPrevGame1(rightLeftGame);
//            rightGame.setPrevGame2(rightRightGame);
//            DefaultGame leftLeftLeftGame = new DefaultGame();
//            DefaultGame leftLeftRightGame = new DefaultGame();
//            DefaultGame leftRightLeftGame = new DefaultGame();
//            DefaultGame leftRightRightGame = new DefaultGame();
//            DefaultGame rightLeftLeftGame = new DefaultGame();
//            DefaultGame rightLeftRightGame = new DefaultGame();
//            DefaultGame rightRightLeftGame = new DefaultGame();
//            DefaultGame rightRightRightGame = new DefaultGame();
//            leftLeftGame.setPrevGame1(leftLeftLeftGame);
//            leftLeftGame.setPrevGame2(leftLeftRightGame);
//            leftRightGame.setPrevGame1(leftRightLeftGame);
//            leftRightGame.setPrevGame2(leftRightRightGame);
//            rightLeftGame.setPrevGame1(rightLeftLeftGame);
//            rightLeftGame.setPrevGame2(rightLeftRightGame);
//            rightRightGame.setPrevGame1(rightRightLeftGame);
//            rightRightGame.setPrevGame2(rightRightRightGame);
//
//            headGame.setGameRound(4);
//            headGame.setGameID(1);
//
//            leftGame.setGameRound(3);
//            leftGame.setGameID(2);
//            rightGame.setGameRound(3);
//            rightGame.setGameID(3);
//
//            leftLeftGame.setGameRound(2);
//            leftLeftGame.setGameID(4);
//            leftRightGame.setGameRound(2);
//            leftRightGame.setGameID(5);
//            rightLeftGame.setGameRound(2);
//            rightLeftGame.setGameID(6);
//            rightRightGame.setGameRound(2);
//            rightRightGame.setGameID(7);
//
//            leftLeftLeftGame.setGameRound(1);
//            leftLeftLeftGame.setGameID(8);
//            leftLeftRightGame.setGameRound(1);
//            leftLeftRightGame.setGameID(9);
//            leftRightLeftGame.setGameRound(1);
//            leftRightLeftGame.setGameID(10);
//            leftRightRightGame.setGameRound(1);
//            leftRightRightGame.setGameID(11);
//            rightLeftLeftGame.setGameRound(1);
//            rightLeftLeftGame.setGameID(12);
//            rightLeftRightGame.setGameRound(1);
//            rightLeftRightGame.setGameID(13);
//            rightRightLeftGame.setGameRound(1);
//            rightRightLeftGame.setGameID(14);
//            rightRightRightGame.setGameRound(1);
//            rightRightRightGame.setGameID(15);
//
//            leftLeftLeftGame.setTeam(teams.get(0), 0);
//            leftLeftLeftGame.setTeam(teams.get(1), 0);
//            leftLeftLeftGame.setNumTeams(2);
//            leftLeftRightGame.setTeam(teams.get(2), 0);
//            leftLeftRightGame.setTeam(teams.get(3), 0);
//            leftLeftRightGame.setNumTeams(2);
//            leftRightLeftGame.setTeam(teams.get(4), 0);
//            leftRightLeftGame.setTeam(teams.get(5), 0);
//            leftRightLeftGame.setNumTeams(2);
//            leftRightRightGame.setTeam(teams.get(6), 0);
//            leftRightRightGame.setTeam(teams.get(7), 0);
//            leftRightRightGame.setNumTeams(2);
//            rightLeftLeftGame.setTeam(teams.get(8), 0);
//            rightLeftLeftGame.setTeam(teams.get(9), 0);
//            rightLeftLeftGame.setNumTeams(2);
//            rightLeftRightGame.setTeam(teams.get(10), 0);
//            rightLeftRightGame.setTeam(teams.get(11), 0);
//            rightLeftRightGame.setNumTeams(2);
//            rightRightLeftGame.setTeam(teams.get(12), 0);
//            rightRightLeftGame.setTeam(teams.get(13), 0);
//            rightRightLeftGame.setNumTeams(2);
//            rightRightRightGame.setTeam(teams.get(14), 0);
//            rightRightRightGame.setTeam(teams.get(15), 0);
//            rightRightRightGame.setNumTeams(2);
//            return headGame;
//        }
//        return null;
//    }

//    public static void main(String[] args) {
//        ArrayList<Team> teams = new ArrayList<Team>();
//        for (int i = 0; i < 2; i++) {
//            Team team = new DefaultTeam();
////            team.setTeamName("Team " + i);
//            teams.add(team);
//        }
//
//        HashMap<String, Integer> games = new HashMap<String, Integer>();
//        for (int i = 0; i <= 1; i++) {
//            games.put(teams.get(i).getTeamName(), i);
//        }
//        System.out.println(games);
//
//        HashMap<Team, Integer> games2 = new HashMap<Team, Integer>();
//        for (int i = 0; i <= 1; i++) {
//            games2.put(teams.get(i), i);
//        }
//        System.out.println(games2);
//
//        ArrayList<Team> teams2 = new ArrayList<Team>();
//        DefaultTeamFactory teamFactory = new DefaultTeamFactory();
//        for (int i = 0; i < 4; i++) {
//            Team team = teamFactory.getTeam("Default");
//            teams2.add(team);
//        }
//
//        DefaultGamesFactory factory = new DefaultGamesFactory();
//        DefaultGame headGame = factory.getGames(4, teams2);
//
//        System.out.println(headGame.getGameID());
//        System.out.println(headGame.getPrevGame1().getGameID());
//        System.out.println(headGame.getPrevGame2().getGameID());
//
//        System.out.println("\n");
//        System.out.print(headGame.getGameRound());
//        System.out.println("\n");
//        System.out.print(headGame.getPrevGame1().getGameRound());
//        System.out.println("\n");
//        System.out.print(headGame.getPrevGame2().getGameRound());
//        System.out.println("\n");
//
//        for (Team team : teams2) {
//            System.out.println(team.getTeamName());
//        }

    public static void main(String[] args) {
//        Testing tree access
        ArrayList<Team> teams = new ArrayList<Team>();
        DefaultTeamFactory teamFactory = new DefaultTeamFactory();
        for (int i = 0; i < 4; i++) {
            Team team = teamFactory.getTeam("Default");
            teams.add(team);
        }

        System.out.println("From List");
        for (Team team : teams) {
            System.out.println(team.getTeamName());
        }

        System.out.println("\n");

        DefaultGamesFactory factory = new DefaultGamesFactory();
        DefaultGame headGame = factory.getGames(4, teams);

        ArrayList<Team> t = headGame.getPrevGame1().getTeams();
        ArrayList<Team> t2 = headGame.getPrevGame2().getTeams();


        Team a = t.get(0);
        Team b = t.get(1);
        Team c = t2.get(0);
        Team d = t2.get(1);

        System.out.println("From Tree");
        System.out.println(a.getTeamName());
        System.out.println(b.getTeamName());
        System.out.println(c.getTeamName());
        System.out.println(d.getTeamName());
        System.out.println("\n");

        a.setTeamName("HELLO");
        b.setTeamName("WORLD");
        c.setTeamName("IT'S");
        d.setTeamName("ME");

        System.out.println("From Tree");
        System.out.println(a.getTeamName());
        System.out.println(b.getTeamName());
        System.out.println(c.getTeamName());
        System.out.println(d.getTeamName());
        System.out.println("\n");

        System.out.println("From List");
        for (Team team : teams) {
            System.out.println(team.getTeamName());
        }

    }
}

