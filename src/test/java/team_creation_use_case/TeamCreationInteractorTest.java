package team_creation_use_case;


import entities.*;
import interface_adapters.data_interface_adapters.team_creation_data.TeamCreationFileWriter;
import interface_adapters.team_creation.TeamCreationFailed;
import interface_adapters.team_creation.TeamCreationPresenter;
import org.junit.Test;
import use_cases.general_classes.InformationRecord;
import use_cases.team_creation.*;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of teamCreationUC
 */

public class TeamCreationInteractorTest {
    /**
     * This tests if a new team can be created by creating a TeamCreationInteractor and input data
     * then running the use case to check that the output data passed to the Presenter is correct
     */
    @Test
    public void testNewTeamCreated() {
        teamCreationGateway gateway = new TeamCreationFileWriter("tests.txt");


        AccountRepo newAR = new AccountRepo();
        DefaultUser newUser = new DefaultUser();
        newUser.setUsername("t1");
        newAR.addUser(newUser);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("BlankTeam11");
        newTeam.setTeamSize(2);
        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        newUser.setBracketRole(1, "Player");

        teamCreationOB outputBoundary = new TeamCreationPresenter(new TestView());
        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        teamCreationUC uc = new teamCreationUC(outputBoundary, gateway, "t1", 1, newInfoRec);


        teamCreationID inputData = new teamCreationID(
                "myTeam");


        teamCreationOD output = uc.createNewTeam(inputData);
        assertEquals("t1", output.getUsername());
        assertEquals("myTeam", output.getNewTeam());
    }

    /**
     * This tests the team name exists check in the use case
     */
    @Test
    public void testNameExists() {
        teamCreationGateway gateway = new TeamCreationFileWriter("tests.txt");


        AccountRepo newAR = new AccountRepo();
        DefaultUser newUser = new DefaultUser();
        newUser.setUsername("t1");
        newAR.addUser(newUser);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("BlankTeam11");
        newTeam.setTeamSize(2);
        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        newUser.setBracketRole(1, "Player");

        teamCreationOB outputBoundary = new TeamCreationPresenter(new TestView());
        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        teamCreationUC uc = new teamCreationUC(outputBoundary, gateway, "t1", 1, newInfoRec);


        teamCreationID inputData = new teamCreationID(
                "BlankTeam11");


        boolean exists = uc.checkTeamNameExists(inputData);
        assertTrue(exists);
    }

    /**
     * This tests finding a blank team in the use case, ensure it returns null if there are no blank teams
     */
    @Test
    public void fullBracket() {
        teamCreationGateway gateway = new TeamCreationFileWriter("tests.txt");
        AccountRepo newAR = new AccountRepo();
        DefaultUser newUser = new DefaultUser();
        newUser.setUsername("t1");
        newAR.addUser(newUser);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("teamOne");
        newTeam.setTeamSize(2);
        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        newUser.setBracketRole(1, "Player");

        teamCreationOB outputBoundary = new TeamCreationPresenter(new TestView());
        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        teamCreationUC uc = new teamCreationUC(outputBoundary, gateway, "t1", 1, newInfoRec);

        assertNull(uc.findBlankTeam());
    }

    /**
     * This tests that exception is thrown if the teamName already exists
     */
    @Test
    public void testNameExistsException() {
        teamCreationGateway gateway = new TeamCreationFileWriter("tests.txt");


        AccountRepo newAR = new AccountRepo();
        DefaultUser newUser = new DefaultUser();
        newUser.setUsername("t1");
        newAR.addUser(newUser);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("BlankTeam11");
        newTeam.setTeamSize(2);
        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        teamCreationOB presenter = new TeamCreationPresenter(new TestView()) {
            @Override
            public teamCreationOD prepareSuccessView(teamCreationOD outputData) {
                fail("Team already exists.");
                return null;
            }

            @Override
            public teamCreationOD prepareFailView(String error) {
                throw new TeamCreationFailed(error);
            }
        };

        teamCreationID inputData = new teamCreationID(
                "BlankTeam11");

        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        teamCreationUC uc = new teamCreationUC(presenter, gateway, "t1", 1, newInfoRec);

        Exception exception = assertThrows(TeamCreationFailed.class, () ->
                uc.createNewTeam(inputData));
        assertEquals("Team already exists.", exception.getMessage());

    }

    /**
     * This tests that exception is thrown if there are no blank teams in the bracket
     */
    @Test
    public void testBracketFullException() {
        teamCreationGateway gateway = new TeamCreationFileWriter("tests.txt");
        AccountRepo newAR = new AccountRepo();
        DefaultUser newUser = new DefaultUser();
        newUser.setUsername("t1");
        newAR.addUser(newUser);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("teamOne");
        newTeam.setTeamSize(2);
        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        newUser.setBracketRole(1, "Player");

        teamCreationOB presenter = new TeamCreationPresenter(new TestView()) {
            @Override
            public teamCreationOD prepareSuccessView(teamCreationOD outputData) {
                fail("The bracket is full, please join an existing team.");
                return null;
            }

            @Override
            public teamCreationOD prepareFailView(String error) {
                throw new TeamCreationFailed(error);
            }
        };

        teamCreationID inputData = new teamCreationID(
                "BlankTeam11");

        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        teamCreationUC uc = new teamCreationUC(presenter, gateway, "t1", 1, newInfoRec);

        Exception exception = assertThrows(TeamCreationFailed.class, () ->
                uc.createNewTeam(inputData));
        assertEquals("The bracket is full, please join an existing team.", exception.getMessage());

    }

    /**
     * This tests that exception is thrown if the creator is not a player
     */
    @Test
    public void testNonPlayerException() {
        teamCreationGateway gateway = new TeamCreationFileWriter("tests.txt");
        AccountRepo newAR = new AccountRepo();
        DefaultUser newUser = new DefaultUser();
        newUser.setUsername("t1");
        newAR.addUser(newUser);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("BlankTeam1");
        newTeam.setTeamSize(2);
        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        newUser.setBracketRole(1, "Observer");

        teamCreationOB presenter = new TeamCreationPresenter(new TestView()) {
            @Override
            public teamCreationOD prepareSuccessView(teamCreationOD outputData) {
                fail("Only players can create a new team.");
                return null;
            }

            @Override
            public teamCreationOD prepareFailView(String error) {
                throw new TeamCreationFailed(error);
            }
        };

        teamCreationID inputData = new teamCreationID(
                "BlankTeam11");

        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        teamCreationUC uc = new teamCreationUC(presenter, gateway, "t1", 1, newInfoRec);

        Exception exception = assertThrows(TeamCreationFailed.class, () ->
                uc.createNewTeam(inputData));
        assertEquals("Only players can create a new team.", exception.getMessage());

    }

}
