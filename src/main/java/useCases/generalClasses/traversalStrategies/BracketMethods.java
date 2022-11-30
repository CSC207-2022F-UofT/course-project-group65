package useCases.generalClasses.traversalStrategies;

import entities.Bracket;
import entities.Game;

import java.util.ArrayList;
import java.util.HashSet;

public class BracketMethods {
    Bracket bracket;

    public BracketMethods(Bracket bracket){
        this.bracket = bracket;
    }

//    public ArrayList<Game> getGamesInRound(int roundNum) {
//        HashSet<Game> visited = new HashSet<>();
//        ArrayList<Game> games = new ArrayList<>();
//        getGamesInRound(bracket.getFinalGame(), roundNum, visited, games);
//        return games;
//    }
//
//    private void getGamesInRound(Game head, int roundNum, HashSet<Game> visited, ArrayList<Game> games) {
//        if (head != null) {
//            visited.add(head);
//            if (head.getGameRound() == roundNum) {
//                games.add(head);
//                return;
//            }
//            if (head.getPrevGame1() != null || head.getPrevGame2() != null) {
//                if (head.getPrevGame1() != null && !visited.contains(head.getPrevGame1())) {
//                    getGamesInRound(head.getPrevGame1(), roundNum, visited, games);
//                }
//                if (head.getPrevGame2() != null && !visited.contains(head.getPrevGame2())){
//                    getGamesInRound(head.getPrevGame2(), roundNum, visited, games);
//                }
//            }
//        }
//    }

//    public ArrayList<Game> getAllGames(){
//        HashSet<Game> visited = new HashSet<>();
//        getAllGames(bracket.getFinalGame(), visited);
//        return new ArrayList<>(visited);
//    }
//
//    private void getAllGames(Game head, HashSet<Game> visited){
//        if (head != null) {
//            visited.add(head);
//            if (head.getPrevGame1() != null && !visited.contains(head.getPrevGame1())) {
//                getAllGames(head.getPrevGame1(), visited);
//            }
//            if (head.getPrevGame2() != null && !visited.contains(head.getPrevGame2())) {
//                getAllGames(head.getPrevGame2(), visited);
//            }
//        }
//    }
}



