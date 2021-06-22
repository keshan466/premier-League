import java.io.Serializable;

public class SchoolFootball extends FootballClub implements Serializable {
    private String schoolName;


    public SchoolFootball(String clubName, String clubLocation, String schoolName) {
        super(clubName, clubLocation);
        this.schoolName = schoolName;
    }

    SchoolFootball(){

    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String toString(){
        return getClubName()+" "+getClubLocation()+" "+schoolName+" "+getCountOfWins()+" "+getCountOfDraws()+" "+getNumbersOfPoint()+" "+getNumberOfGoalsScore()+" "+getNumberOfGoalsReceived()+" "+getNumOfDefeats()+" "+getMatchesPlayed();
    }


}