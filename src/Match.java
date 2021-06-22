import java.io.Serializable;

public class Match implements Serializable {

    private int team1NumbersOfPoint;
    private int team2NumbersOfPoint;
    private int team1NumberOfGoalsReceived;
    private int team2NumberOfGoalsReceived;
    private int team1Score;
    private int team2Score;
    private int team1GoalsDifference;
    private int team2GoalsDifference;
    private String team1;
    private String team2;
    private Date date;


    public Match(int team1NumbersOfPoint,int team2NumbersOfPoint,int team1GoalsDifference,int team2GoalsDifference,int team1NumberOfGoalsReceived, int team2NumberOfGoalsReceived, int team1Score, int team2Score, String team1, String team2, Date date) {

        this.team1NumbersOfPoint=team1NumbersOfPoint;
        this.team2NumbersOfPoint=team2NumbersOfPoint;
        this.team1GoalsDifference=team1GoalsDifference;
        this.team2GoalsDifference=team2GoalsDifference;
        this.team1NumberOfGoalsReceived = team1NumberOfGoalsReceived;
        this.team2NumberOfGoalsReceived = team2NumberOfGoalsReceived;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Match{" + "team1Point=" + team1NumbersOfPoint + ", team2Point=" + team2NumbersOfPoint + ", team1GoalsReceived=" + team1NumberOfGoalsReceived + ", team2GoalsReceived=" + team2NumberOfGoalsReceived + ", team1Score=" + team1Score + ", team2Score=" + team2Score + ", team1GoalsDifference=" + team1GoalsDifference + ", team2GoalsDifference=" + team2GoalsDifference + ", team1='" + team1 + '\'' + ", team2='" + team2 + '\'' + ", date=" + date + '}';
    }

    Match(){

    }

    public int getTeam1GoalsDifference() {
        return team1GoalsDifference;
    }

    public void setTeam1GoalsDifference(int team1GoalsDifference) {
        this.team1GoalsDifference = team1GoalsDifference;
    }

    public int getTeam2GoalsDifference() {
        return team2GoalsDifference;
    }

    public void setTeam2GoalsDifference(int team2GoalsDifference) {
        this.team2GoalsDifference = team2GoalsDifference;
    }

    public int getTeam1NumbersOfPoint() {
        return team1NumbersOfPoint;
    }

    public void setTeam1NumbersOfPoint(int team1NumbersOfPoint) {
        this.team1NumbersOfPoint = team1NumbersOfPoint;
    }

    public int getTeam1NumberOfGoalsReceived() {
        return team1NumberOfGoalsReceived;
    }

    public void setTeam1NumberOfGoalsReceived(int team1NumberOfGoalsReceived) {
        this.team1NumberOfGoalsReceived = team1NumberOfGoalsReceived;
    }

    public int getTeam2NumbersOfPoint() {
        return team2NumbersOfPoint;
    }

    public void setTeam2NumbersOfPoint(int team2NumbersOfPoint) {
        this.team2NumbersOfPoint = team2NumbersOfPoint;
    }

    public int getTeam2NumberOfGoalsReceived() {
        return team2NumberOfGoalsReceived;
    }

    public void setTeam2NumberOfGoalsReceived(int team2NumberOfGoalsReceived) {
        this.team2NumberOfGoalsReceived = team2NumberOfGoalsReceived;
    }


    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMonth() {
        return date.getMonth();
    }
    public int getYear() {
        return date.getYear();
    }
    public int getDay() {
        return date.getDay();
    }

}