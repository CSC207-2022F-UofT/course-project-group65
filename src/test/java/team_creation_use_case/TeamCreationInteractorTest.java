package team_creation_use_case;


import interface_adapters.data_interface_adapters.team_creation_data.TeamCreationFileWriter;
import interface_adapters.team_creation.TeamCreationPresenter;
import interface_adapters.view_interfaces.main_view_interfaces.TeamCreationView;
import org.junit.Test;

import static org.junit.Assert.*;


import entities.*;

import use_cases.general_classes.InformationRecord;
import use_cases.team_creation.*;



public class TeamCreationInteractorTest {
    /** This tests if a new team can be created by creating a TeamCreationInteractor and input data
     * then running the use case to check that the output data passed to the Presenter is correct */
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
        teamCreationUC uc = new teamCreationUC(outputBoundary, gateway,"t1", 1, newInfoRec);


        teamCreationID inputData = new teamCreationID(
                "myTeam");


        teamCreationOD output = uc.createNewTeam(inputData);
        assertEquals("t1", output.getUsername());
        assertEquals("myTeam", output.getNewTeam());
    }
    /** This tests the team name exists check in the use case */
    @Test
    public void testNameExists(){
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
        teamCreationUC uc = new teamCreationUC(outputBoundary, gateway,"t1", 1, newInfoRec);


        teamCreationID inputData = new teamCreationID(
                "BlankTeam11");


        boolean exists = uc.checkTeamNameExists(inputData);
        assertTrue(exists);
    }
    /** This tests finding a blank team in the use case, ensure it returns null if there are no blank teams */
    @Test
    public void fullBracket(){
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
        teamCreationUC uc = new teamCreationUC(outputBoundary, gateway,"t1", 1, newInfoRec);



        assertNull(uc.findBlankTeam());
    }
}
