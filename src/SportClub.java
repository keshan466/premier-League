import java.io.Serializable;
import java.util.Objects;

public class SportClub implements Serializable {
    private String clubName;
    private String clubLocation;

    public SportClub(String clubName, String clubLocation) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }


    SportClub() {


    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportClub)) return false;
        SportClub sportClub = (SportClub) o;
        return getClubName().equals(sportClub.getClubName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClubName());
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String toString(){
        return clubName+" "+clubLocation;
    }

}
