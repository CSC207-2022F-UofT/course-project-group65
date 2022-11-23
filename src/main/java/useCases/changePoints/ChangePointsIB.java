package useCases.changePoints;

import entities.Bracket;

public interface ChangePointsIB<T extends Bracket> {

    ChangePointsOD changePoints(ChangePointsID changePointsID);

}
