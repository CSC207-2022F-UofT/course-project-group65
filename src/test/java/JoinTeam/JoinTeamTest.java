package JoinTeam;

import interface_adapters.data_interface_adapters.join_team_data.JoinTeamFileWriter;
import interface_adapters.join_team.JoinTeamPresenter;
import org.junit.Test;

import static org.junit.Assert.*;

import entities.*;

import use_cases.general_classes.InformationRecord;
import use_cases.join_team.*;

import java.util.ArrayList;

public class JoinTeamTest {
    /** This tests if a player can join an existed team with the input data teamName
     * then running the use case to check that the output data passed to the Presenter is correct */
    @Test
    public void testJoinATeam() {
        JoinTeamGateway gateway = new JoinTeamFileWriter("tests.txt");

        AccountRepo newAR = new AccountRepo();
        DefaultUser creator = new DefaultUser();
        creator.setUsername("t1");
        newAR.addUser(creator);
        DefaultUser joiner = new DefaultUser();
        joiner.setUsername("t2");
        newAR.addUser(joiner);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("team1");
        newTeam.setTeamSize(2);
        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        creator.setBracketRole(1, "Player");
        joiner.setBracketRole(1, "Player");
        newTeam.addTeamMember(creator);

        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        JoinTeamUC joinTeamUC = new JoinTeamUC(outputBoundary, gateway, "joiner", 1, newInfoRec);

        JoinTeamID inputData = new JoinTeamID("team1");

        JoinTeamOD outputData = joinTeamUC.joinTeam(inputData);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("t1");
        expected.add("t2");

        assertEquals(expected, outputData.getMembersNames());
    }
    /** This tests the team exists check in the use case */
    @Test
    public void testTeamExistence(){
        JoinTeamGateway gateway = new JoinTeamFileWriter("tests.txt");

        AccountRepo newAR = new AccountRepo();
        DefaultUser creator = new DefaultUser();
        creator.setUsername("t1");
        newAR.addUser(creator);
        DefaultUser joiner = new DefaultUser();
        joiner.setUsername("t2");
        newAR.addUser(joiner);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam trueTeam = new DefaultTeam();
        trueTeam.setTeamName("team1");
        trueTeam.setTeamSize(2);
        newBracket.addTeam(trueTeam);
        DefaultTeam falseTeam = new DefaultTeam();
        falseTeam.setTeamName("BlankTeam2");
        falseTeam.setTeamSize(2);
        newBracket.addTeam(falseTeam);
        newBR.addBracket(newBracket);

        creator.setBracketRole(1, "Player");
        joiner.setBracketRole(1, "Player");
        trueTeam.addTeamMember(creator);

        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        JoinTeamUC joinTeamUC = new JoinTeamUC(outputBoundary, gateway, "joiner", 1, newInfoRec);
        JoinTeamID inputData1 = new JoinTeamID("team1");
        JoinTeamID inputData2 = new JoinTeamID("BlankTeam2");
        boolean existence1 = joinTeamUC.checkTeamExistence(inputData1);
        boolean existence2 = joinTeamUC.checkTeamExistence(inputData2);

        assertTrue(existence1);
        assertFalse(existence2);
    }
}
