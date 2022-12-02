package screens;

import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundleBracketData.BundleBracketData;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NextScreenData {
    private String currentUser;
    private int currentBracketID;
//    private AccountRepo accounts;
//    private BracketRepo brackets;
    private BundleBracketData bundleBracketData;
    private final InformationRecord informationRecord;

    public NextScreenData(InformationRecord informationRecord) {
        this.currentUser = "";
        this.currentBracketID = 0;
//        this.accounts = informationRecord.getAccountData();
//        this.brackets = informationRecord.getBracketData();
        this.bundleBracketData = new BundleBracketData();
        this.informationRecord = informationRecord;

    }

    public InformationRecord getInformationRecord() {
        return informationRecord;
    }

    public void bundleData(){
        this.bundleBracketData = new BundleBracketData();
        this.bundleBracketData.bundleBracket(this.informationRecord.getBracket(this.currentBracketID));
    }

    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams() {
        return this.bundleBracketData.getGameToTeams();
    }

    public LinkedHashMap<Integer, ArrayList<Integer>> getGameToScores() {
        return this.bundleBracketData.getGameToScores();
    }

    public LinkedHashMap<Integer, String> getGameToWinner() {
        return this.bundleBracketData.getGameToWinner();
    }

    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers() {
        return this.bundleBracketData.getTeamToPlayers();
    }

    public ArrayList<String> getReferees() {
        return this.bundleBracketData.getReferees();
    }

    public LinkedHashMap<Integer, String> getGameToReferee() {
        return this.bundleBracketData.getGameToReferee();
    }

    public LinkedHashMap<String, String> getRoleToInvite() {
        return this.bundleBracketData.getRoleToInvite();
    }

    public int getTournamentID() {
        return this.bundleBracketData.getTournamentID();
    }

    public String getTournamentName() {
        return this.bundleBracketData.getTournamentName();
    }

    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    public String getCurrentUser() {
        return this.currentUser;
    }
    public String getUserPassword(String username){
        return this.informationRecord.getUserPassword(username);
    }


    public ArrayList<Integer> getUsersTournaments(String username) {
        return this.informationRecord.getUserTournaments(this.currentUser);
    }

    public int getMaxTeamSize(int bracketID) {
        return this.informationRecord.getMaxTeamSize(bracketID);
    }

    public void setCurrentBracketID(int bracketID) {
        this.currentBracketID = bracketID;
    }

    public int getCurrentBracketID() {
        return this.currentBracketID;
    }

//    public void setAccounts(AccountRepo accounts) {
//        this.accounts = accounts;
//    }

//    public AccountRepo getAccounts() {
//        return this.accounts;
//    }

//    public void setBrackets(BracketRepo brackets) {
//        this.brackets = brackets;
//    }

//    public BracketRepo getBrackets() {
//        return this.brackets;
//    }
}
