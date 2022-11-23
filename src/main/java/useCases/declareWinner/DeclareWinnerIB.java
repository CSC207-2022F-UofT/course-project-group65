package useCases.declareWinner;

import entities.Bracket;

public interface DeclareWinnerIB<T extends Bracket> {

    DeclareWinnerOD setWinner(DeclareWinnerID declareWinnerID);

}
