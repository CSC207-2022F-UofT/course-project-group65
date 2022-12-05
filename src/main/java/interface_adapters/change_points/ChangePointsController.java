package interface_adapters.change_points;

import interface_adapters.data_interface_adapters.change_points_data.ChangePointsFileWriter;
import interface_adapters.view_interfaces.bracket_operation_interface.ChangePointsBOView;
import use_cases.change_points.*;
import use_cases.general_classes.InformationRecord;

/**
 * This class is the controller for the ChangePoints use case.
 * It is responsible for the communication between the ChangePoints use case and the GUI.
 * It is also responsible for the communication between the ChangePoints use case and the data adapters.
 */
public class ChangePointsController {

    ChangePointsIB userInput;
    ChangePointsPresenter presenter;

    /**
     * This constructor creates a new ChangePointsController object.
     * @param informationRecord The information record that the ChangePoints use case will use.
     * @param bracketID The bracket ID that the ChangePoints use case will use.
     * @param username The username of the user.
     */
    public ChangePointsController(ChangePointsPresenter presenter, InformationRecord informationRecord, int bracketID, String username) {
        this.presenter = presenter;
        ChangePointsGateway gateway = new ChangePointsFileWriter("brackets.txt");
        this.userInput = new ChangePointsUC(this.presenter, gateway, informationRecord, bracketID, username);
    }

    public void setView(ChangePointsBOView view) {
        this.presenter.setView(view);
    }

    /**
     * This method is responsible for the communication between the ChangePoints use case and the GUI.
     * It is responsible for the communication between the ChangePoints use case and the data adapters.
     * @param gameID The game ID that the ChangePoints use case will use.
     * @param newPoints The new points that the user wants to set for the bracket.
     * @param teamName The team name that the user wants to change points for in the bracket.
     */
    public ChangePointsOD create(int gameID, int newPoints, String teamName) {
        ChangePointsID inputData = new ChangePointsID(gameID, newPoints, teamName);
        return this.userInput.changePoints(inputData);
    }

}
