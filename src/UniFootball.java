import java.io.Serializable;

public class UniFootball extends FootballClub implements Serializable {
    private String uniName;
    UniFootball(){

    }

    public UniFootball(String clubName, String clubLocation, String uniName) {
        super(clubName, clubLocation);
        this.uniName = uniName;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }
    public String toString(){
        return getClubName()+" "+getClubLocation()+" "+uniName+" "+getCountOfWins()+" "+getCountOfDraws()+" "+getNumbersOfPoint()+" "+getNumberOfGoalsScore()+" "+getNumberOfGoalsReceived()+" "+getNumOfDefeats()+" "+getMatchesPlayed();
    }

}