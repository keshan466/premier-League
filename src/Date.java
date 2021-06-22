import java.io.Serializable;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }


    public Date(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }


    @Override
    public String toString() {
        return day+" ."+ month+" ."+year;
    }

    public void setDay(int day) {
        switch (getMonth()) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day>=1 && day<=31){
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("!Invalid Day");
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day>=1 && day<=30){
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("!Invalid Day");
                }
                break;
            case 2:
                if (getYear()%4 == 0){
                    if (day>=1 && day<=29){
                        this.day = day;
                    } else {
                        throw new IllegalArgumentException("!Invalid Day");
                    }
                } else {
                    if (day>=1 && day<=28){
                        this.day = day;
                    } else {
                        throw new IllegalArgumentException("!Invalid Day");
                    }
                }
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month>=1 && month<=12) {
            this.month = month;
        } else {
            throw new IllegalArgumentException("!Invalid Month");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

