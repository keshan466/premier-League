public class FootballClub  extends SportClub implements  Comparable<FootballClub>  {
    private int countOfWins;
    private int countOfDraws;
    private int numOfDefeats;
    private int numbersOfPoint;
    private int numberOfGoalsScore;
    private int numberOfGoalsReceived;
    private int matchesPlayed;
    private int goalsDifference;

    @Override
    public String toString() {
        return "FootballClub{" + "Wins=" + countOfWins + ", Draws=" + countOfDraws + ", Defeats=" + numOfDefeats + ", Point=" + numbersOfPoint + ", GoalsScore=" + numberOfGoalsScore + ", GoalsReceived=" + numberOfGoalsReceived + ", matchesPlayed=" + matchesPlayed + ", goalsDifference=" + goalsDifference + '}';
    }

    public FootballClub(String clubName, String clubLocation, int countOfWins, int countOfDraws, int numOfDefeats, int numbersOfPoint, int numberOfGoalsScore, int numberOfGoalsReceived, int matchesPlayed,int goalsDifference) {
        super(clubName, clubLocation);
        this.countOfWins = countOfWins;
        this.countOfDraws = countOfDraws;
        this.numOfDefeats = numOfDefeats;
        this.numbersOfPoint = numbersOfPoint;
        this.numberOfGoalsScore = numberOfGoalsScore;
        this.numberOfGoalsReceived = numberOfGoalsReceived;
        this.matchesPlayed = matchesPlayed;
        this.goalsDifference=goalsDifference;
    }




    public FootballClub(String clubName, String clubLocation) {
        super(clubName, clubLocation);
    }

    FootballClub() {

    }

    @Override
    public int compareTo(FootballClub o) {
        if (this.numbersOfPoint == o.numbersOfPoint) {
            // if goalsScored same check goal difference here
            if ((this.numberOfGoalsScore - this.numberOfGoalsReceived) > ((o.numberOfGoalsScore - o.numberOfGoalsReceived))) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.numbersOfPoint > o.numbersOfPoint) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getGoalsDifference() {
        return goalsDifference;
    }

    public void setGoalsDifference(int goalsDifference) {
        this.goalsDifference = goalsDifference;
    }

    public int getCountOfWins() {
        return countOfWins;
    }
    public void setCountOfWins(int countOfWins) {
        this.countOfWins = countOfWins;
    }

    public int getNumbersOfPoint() {
        return numbersOfPoint;
    }

    public void setNumbersOfPoint(int numbersOfPoint) {
        this.numbersOfPoint = numbersOfPoint;
    }

    public int getCountOfDraws() {
        return countOfDraws;
    }

    public void setCountOfDraws(int countOfDraws) {
        this.countOfDraws = countOfDraws;
    }

    public int getNumOfDefeats() {
        return numOfDefeats;
    }

    public void setNumOfDefeats(int numOfDefeats) {
        this.numOfDefeats = numOfDefeats;
    }


    public int getNumberOfGoalsScore() {
        return numberOfGoalsScore;
    }

    public void setNumberOfGoalsScore(int numberOfGoalsScore) {
        this.numberOfGoalsScore = numberOfGoalsScore;
    }

    public int getNumberOfGoalsReceived() {
        return numberOfGoalsReceived;
    }

    public void setNumberOfGoalsReceived(int numberOfGoalsReceived) {
        this.numberOfGoalsReceived = numberOfGoalsReceived;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
}
