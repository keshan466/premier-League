import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {


    ArrayList<FootballClub> AddClubDetails = new ArrayList<>();     //Create ArrayList object to  store club details
    ArrayList<Match> AddMatch=new ArrayList<>();                    //Create ArrayList object to  store Add match club details


    @Override
    public void createClub() {
        Scanner sc1 = new Scanner(System.in);
        boolean r = false;
        System.out.println("Enter you Club Name :");
        String name = sc1.next();
        for (FootballClub football : AddClubDetails) {
            if (football.getClubName().equals(name)) {      //checked the same name
                r = true;
                break;
            }
        }
        if (r) {
            System.out.println("The club name is already Included");
            System.out.println();

        } else {
            System.out.println("Enter you Club Location :");
            String address = sc1.next();
            System.out.println("Enter 1-you are University Student");
            System.out.println("Enter 2-You are School Student");
            System.out.println("Enter you choice:");

            String num = sc1.next();
            switch (num) {                  //checked uni or school
                case "1":
                    System.out.println("Enter your University Name:");
                    String uniName = sc1.next();
                    UniFootball c1 = new UniFootball(name, address, uniName);
                    AddClubDetails.add(c1);
                    break;

                case "2":
                    System.out.println("Enter you School Name:");
                    String schoolName = sc1.next();
                    SchoolFootball c2 = new SchoolFootball(name, address, schoolName);
                    AddClubDetails.add(c2);
                    break;

                default:
                    System.out.println("Please refer instruction");
                    System.out.println();
                    break;
            }
        }
    }

    @Override
    public void deleteClub() {

        boolean value = false;
        System.out.println("Do you want to delete the club name :");
        Scanner sc1 = new Scanner(System.in);
        String deleteName = sc1.next();
        for (FootballClub footballClub : AddClubDetails) {
            if (footballClub.getClubName().equals(deleteName)) {  //find the name in loop
                AddClubDetails.remove(footballClub);
                value = true;
                break;
            }
        }
        if (value) {
            System.out.println("Remove successfully...");

        } else {
            System.out.println("The club is not hear");
            System.out.println();
        }
    }

    @Override
    public void displayClubStatistics() {

        Scanner sc1 = new Scanner(System.in);
        boolean b = false;
        System.out.println("Enter you club name :");
        String checkName = sc1.next();
        for (FootballClub footballClub : AddClubDetails) {
            if (footballClub.getClubName().equals(checkName)) {
                System.out.println("Count of won    :" + footballClub.getCountOfWins());
                System.out.println("Count of Draws  :" + footballClub.getCountOfDraws());
                System.out.println("Count of Defeats:" + footballClub.getNumOfDefeats());
                System.out.println("Count of points :" + footballClub.getNumbersOfPoint());
                System.out.println("GoalsScore      :" + footballClub.getNumberOfGoalsScore());
                System.out.println("GoalsReceived   :" + footballClub.getNumberOfGoalsReceived());
                System.out.println("Matches played  :" + footballClub.getMatchesPlayed());
                b = true;
                break;
            }
        }
        if (b) {
            System.out.println();
        } else {
            System.out.println("This club is not include");
            System.out.println();
        }

    }
    @Override
    public void displayPremierLeagueTable() {

        Collections.sort(AddClubDetails, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                int y = o2.getNumberOfGoalsReceived() - o2.getNumberOfGoalsScore();
                int q = o1.getNumberOfGoalsReceived() - o1.getNumberOfGoalsScore();
                return Integer.valueOf(y).compareTo(q);
            }
        });

        Collections.sort(AddClubDetails, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return Integer.valueOf(o2.getNumbersOfPoint()).compareTo(o1.getNumbersOfPoint());                //sort points
            }
        });
        table();
    }

    public void table(){                    //table print
        String divider="-------------------------------------------------------------------------";
        System.out.println(divider);
        String heading1="Club_Name";
        String heading2="Win";
        String heading3="Draws";
        String heading4="Defeats";
        String heading5="Points";
        String heading6="Score";
        String heading7="Received";
        String heading8="Matches";

        System.out.printf("%8s %8s %8s %8s %8s %8s %8s %8s %n",heading1,heading2,heading3,heading4,heading5,heading6,heading7,heading8);
        System.out.println(divider);
        System.out.println("");
        for (FootballClub footballClub : AddClubDetails) {
            String name=footballClub.getClubName();
            int win=footballClub.getCountOfWins() ;
            int draws=footballClub.getCountOfDraws();
            int defeats=footballClub.getNumOfDefeats();
            int point=footballClub.getNumbersOfPoint();
            int score=footballClub.getNumberOfGoalsScore();
            int received=footballClub.getNumberOfGoalsReceived() ;
            int match=footballClub.getMatchesPlayed();
            System.out.printf("%5s %10s %8s %8s %8s %8s %8s %8s %n",name,win,draws,defeats,point,score,received,match);

        }
        System.out.print(divider);
    }

    @Override
    public void saveData() {

        try {
            FileOutputStream writeData = new FileOutputStream("ClubDetails.txt");           //create file and store data ClubDetails
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(AddClubDetails);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream writeData1 = new FileOutputStream("AddMatchDetails.txt");   //create file and store data AddMatchDetails
            ObjectOutputStream writeStream1 = new ObjectOutputStream(writeData1);

            writeStream1.writeObject(AddMatch);
            writeStream1.flush();
            writeStream1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data saving.........");

    }

    public void lordData() {

        try {
            FileInputStream readData = new FileInputStream("ClubDetails.txt");                 //lord the data ClubDetails file
            ObjectInputStream readStream = new ObjectInputStream(readData);

            ArrayList<FootballClub> details = (ArrayList<FootballClub>) readStream.readObject();
            readStream.close();
            for(FootballClub club:details){
                AddClubDetails.add(club);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream readData1 = new FileInputStream("AddMatchDetails.txt");          //lord the data AddMatchDetails file
            ObjectInputStream readStream1 = new ObjectInputStream(readData1);

            ArrayList<Match> AddDetails = (ArrayList<Match>) readStream1.readObject();
            readStream1.close();
            for(Match match:AddDetails){
                AddMatch.add(match);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addVariousStatistics() {

        Scanner sc1 = new Scanner(System.in);
        System.out.println("..Please Enter Integer Value..");
        System.out.println("Year  :");
        int year=sc1.nextInt();

        System.out.println("Month :");
        int month=sc1.nextInt();

        System.out.println("Day  :");
        int day=sc1.nextInt();

        Date date=new Date(day,month,year);                     //Create date object

        int numOfGoals1 = 0;
        int numOfGoals2 = 0;
        String checkName2;
        String checkName1;
        boolean a = false;
        System.out.println("Enter you club 1 Name :");
        checkName1 = sc1.next();
        for (FootballClub footballClub1 : AddClubDetails) {   //Check the name of club include the ArrayList

            if (footballClub1.getClubName().equals(checkName1)) {

                System.out.println("Enter how many GoalsScore club 1 have :");
                numOfGoals1 = sc1.nextInt();
                a = true;
                break;
            }
        }
        boolean b = false;
        if (a) {


            System.out.println("Enter you club 2 Name :");
            checkName2 = sc1.next();
            if(!checkName1.equals(checkName2)){                                               //Check the club 1 name equal club 2 name
                for (FootballClub footballClub2 : AddClubDetails) {                          //Check the name of club include the ArrayList
                    if (footballClub2.getClubName().equals(checkName2)) {

                        System.out.println("Enter how many GoalsScore club 2 have :");
                        numOfGoals2 = sc1.nextInt();

                        if (numOfGoals1 > numOfGoals2) {
                            for (FootballClub footballClub3 : AddClubDetails) {             //Check the name of club include the ArrayList
                                if (footballClub3.getClubName().equals(checkName1)) {
                                    footballClub3.setNumbersOfPoint(footballClub3.getNumbersOfPoint() + 3);
                                    footballClub3.setNumberOfGoalsScore(footballClub3.getNumberOfGoalsScore() + numOfGoals1);
                                    footballClub3.setNumberOfGoalsReceived(footballClub3.getNumberOfGoalsReceived() + numOfGoals2);
                                    footballClub3.setMatchesPlayed(footballClub3.getMatchesPlayed() + 1);
                                    footballClub3.setCountOfWins(footballClub3.getCountOfWins() + 1);
                                    footballClub3.setGoalsDifference(footballClub3.getGoalsDifference()+(numOfGoals2-numOfGoals1));
                                }
                            }

                            footballClub2.setNumberOfGoalsScore(footballClub2.getNumberOfGoalsScore() + numOfGoals2);
                            footballClub2.setNumberOfGoalsReceived(footballClub2.getNumberOfGoalsReceived() + numOfGoals1);
                            footballClub2.setMatchesPlayed(footballClub2.getMatchesPlayed() + 1);
                            footballClub2.setNumOfDefeats(footballClub2.getNumOfDefeats() + 1);
                            footballClub2.setGoalsDifference(footballClub2.getGoalsDifference()+(numOfGoals1-numOfGoals2));


                        } else if (numOfGoals1 == numOfGoals2) {
                            for (FootballClub footballClub4 : AddClubDetails) {             //Check the name of club include the ArrayList
                                if (footballClub4.getClubName().equals(checkName1)) {
                                    footballClub4.setNumbersOfPoint(footballClub4.getNumbersOfPoint() + 1);
                                    footballClub4.setNumberOfGoalsScore(footballClub4.getNumberOfGoalsScore() + numOfGoals1);
                                    footballClub4.setNumberOfGoalsReceived(footballClub4.getNumberOfGoalsReceived() + numOfGoals2);
                                    footballClub4.setCountOfDraws(footballClub4.getCountOfDraws() + 1);
                                    footballClub4.setMatchesPlayed(footballClub4.getMatchesPlayed() + 1);
                                }
                            }
                            footballClub2.setNumbersOfPoint(footballClub2.getNumbersOfPoint() + 1);
                            footballClub2.setNumberOfGoalsScore(footballClub2.getNumberOfGoalsScore() + numOfGoals2);
                            footballClub2.setNumberOfGoalsReceived(footballClub2.getNumberOfGoalsReceived() + numOfGoals1);
                            footballClub2.setCountOfDraws(footballClub2.getCountOfDraws() + 1);
                            footballClub2.setMatchesPlayed(footballClub2.getMatchesPlayed() + 1);

                        } else {
                            for (FootballClub footballClub5 : AddClubDetails) {                 //Check the name of club include the ArrayList
                                if (footballClub5.getClubName().equals(checkName1)) {
                                    footballClub5.setNumberOfGoalsScore(footballClub5.getNumberOfGoalsScore() + numOfGoals1);
                                    footballClub5.setNumberOfGoalsReceived(footballClub5.getNumberOfGoalsReceived() + numOfGoals2);
                                    footballClub5.setNumOfDefeats(footballClub5.getNumOfDefeats() + 1);
                                    footballClub5.setMatchesPlayed(footballClub5.getMatchesPlayed() + 1);
                                    footballClub5.setGoalsDifference(footballClub5.getGoalsDifference()+(numOfGoals2-numOfGoals1));

                                }
                            }

                            footballClub2.setNumbersOfPoint(footballClub2.getNumbersOfPoint() + 3);
                            footballClub2.setNumberOfGoalsScore(footballClub2.getNumberOfGoalsScore() + numOfGoals2);
                            footballClub2.setNumberOfGoalsReceived(footballClub2.getNumberOfGoalsReceived() + numOfGoals1);
                            footballClub2.setCountOfWins(footballClub2.getCountOfWins() + 1);
                            footballClub2.setMatchesPlayed(footballClub2.getMatchesPlayed() + 1);
                            footballClub2.setGoalsDifference(footballClub2.getGoalsDifference()+(numOfGoals1-numOfGoals2));
                        }
                        b = true;
                        break;
                    }

                }
                int pointTeam1=0;
                int pointTeam2=0;
                if (b) {
                    if (numOfGoals1>numOfGoals2) {
                        pointTeam1=3;
                    }
                    else if (numOfGoals1==numOfGoals2){
                        pointTeam1=1;
                        pointTeam2=1;
                    }
                    else {
                        pointTeam2=3;
                    }
                    int team1GD=numOfGoals2-numOfGoals1;     //Count Goal Difference
                    int team2GD=numOfGoals1-numOfGoals2;
                    Match match=new Match(pointTeam1,pointTeam2,team1GD,team2GD,numOfGoals2,numOfGoals1,numOfGoals1,numOfGoals2,checkName1,checkName2,date);   //create match class object
                    AddMatch.add(match);                            //add match object in array list


                } else {
                    System.out.println("This club is not include...");     //Check club club name is include the ArrayList
                }
            }
            else{
                System.out.println("Please enter two club names...");       //include same names to add matches
            }
        }
        else {
            System.out.println("This club is not include...");  //Check club one name is include the ArrayList
        }
    }

    @Override
    public void sortGoals() {

        Collections.sort(AddClubDetails, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return Integer.valueOf(o2.getNumberOfGoalsScore()).compareTo(o1.getNumberOfGoalsScore());
            }
        });

        Stage stage1=new Stage();                   //Create stage
        stage1.setTitle("Sorting Goals");
        StackPane stackPane1=new StackPane();          //Create StackPane
        stackPane1.setStyle("-fx-background-color: DARKCYAN");
        ObservableList<FootballClub> observableList = FXCollections.observableArrayList(AddClubDetails);     //Create ObservableList and Add arrayList object
        Label label1=new Label("Sort By Goals Scored");
        stackPane1.setAlignment(label1, Pos.TOP_CENTER);            //Alignment label
        label1.setFont(Font.font("Cambria",36));        //Change font and size
        label1.setStyle("-fx-font-weight: bold;");


        TableColumn<FootballClub,String> name=new TableColumn<>("Name");       //create column
        name.setMinWidth(100);                                                              //set column width
        name.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<FootballClub,Integer> countWin=new TableColumn<>("Win");
        countWin.setMinWidth(100);
        countWin.setCellValueFactory(new PropertyValueFactory<>("countOfWins"));

        TableColumn<FootballClub,Integer> countDraws=new TableColumn<>("Draws");
        countDraws.setMinWidth(100);
        countDraws.setCellValueFactory(new PropertyValueFactory<>("countOfDraws"));

        TableColumn<FootballClub,Integer> countDefeats=new TableColumn<>("Defeats");
        countDefeats.setMinWidth(100);
        countDefeats.setCellValueFactory(new PropertyValueFactory<>("numOfDefeats"));

        TableColumn<FootballClub,Integer> countPoints=new TableColumn<>("Points");
        countPoints.setMinWidth(100);
        countPoints.setCellValueFactory(new PropertyValueFactory<>("numbersOfPoint"));

        TableColumn<FootballClub,Integer> countScore=new TableColumn<>("Score");
        countScore.setMinWidth(100);
        countScore.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScore"));

        TableColumn<FootballClub,Integer> countReceived=new TableColumn<>("Received");
        countReceived.setMinWidth(100);
        countReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));

        TableColumn<FootballClub,Integer> countMatches=new TableColumn<>("Matches");
        countMatches.setMinWidth(100);
        countMatches.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        TableColumn<FootballClub,Integer> goalsDifference=new TableColumn<>("GoalsDifference");
        goalsDifference.setMinWidth(120);
        goalsDifference.setCellValueFactory(new PropertyValueFactory<>("goalsDifference"));


        TableView tableView=new TableView();           //Create table view
        tableView.setMaxHeight(300);
        tableView.setMaxWidth(920);
        tableView.setItems(observableList);
        tableView.getColumns().addAll(name,countWin,countDraws,countDefeats,countPoints,countScore,countReceived,goalsDifference,countMatches);         //Add column to table

        stackPane1.getChildren().addAll(tableView,label1);          //Add StackPane to tableView and labels
        Scene scene=new Scene(stackPane1,1200,500);     //Create scene
        stage1.setScene(scene);
        stage1.showAndWait();                                        //Show wait

    }

    @Override
    public void sortPoint() {
        Collections.sort(AddClubDetails, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return Integer.valueOf(o2.getNumbersOfPoint()).compareTo(o1.getNumbersOfPoint());
            }
        });
        Stage stage2=new Stage();
        stage2.setTitle("Sorting Points");
        StackPane stackPane2=new StackPane();
        stackPane2.setStyle("-fx-background-color: DARKCYAN");
        Label label2=new Label("Sort By Points");
        stackPane2.setAlignment(label2, Pos.TOP_CENTER);
        label2.setFont(Font.font("Cambria",36));
        label2.setStyle("-fx-font-weight: bold;");
        ObservableList<FootballClub> observableList = FXCollections.observableArrayList(AddClubDetails);


        TableColumn<FootballClub,String> name=new TableColumn<>("Name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<FootballClub,Integer> countWin=new TableColumn<>("Win");
        countWin.setMinWidth(100);
        countWin.setCellValueFactory(new PropertyValueFactory<>("countOfWins"));

        TableColumn<FootballClub,Integer> countDraws=new TableColumn<>("Draws");
        countDraws.setMinWidth(100);
        countDraws.setCellValueFactory(new PropertyValueFactory<>("countOfDraws"));

        TableColumn<FootballClub,Integer> countDefeats=new TableColumn<>("Defeats");
        countDefeats.setMinWidth(100);
        countDefeats.setCellValueFactory(new PropertyValueFactory<>("numOfDefeats"));

        TableColumn<FootballClub,Integer> countPoints=new TableColumn<>("Points");
        countPoints.setMinWidth(100);
        countPoints.setCellValueFactory(new PropertyValueFactory<>("numbersOfPoint"));

        TableColumn<FootballClub,Integer> countScore=new TableColumn<>("Score");
        countScore.setMinWidth(100);
        countScore.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScore"));

        TableColumn<FootballClub,Integer> countReceived=new TableColumn<>("Received");
        countReceived.setMinWidth(100);
        countReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));

        TableColumn<FootballClub,Integer> countMatches=new TableColumn<>("Matches");
        countMatches.setMinWidth(100);
        countMatches.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        TableColumn<FootballClub,Integer> goalsDifference=new TableColumn<>("GoalsDifference");
        goalsDifference.setMinWidth(120);
        goalsDifference.setCellValueFactory(new PropertyValueFactory<>("goalsDifference"));


        TableView tableView=new TableView();           //Create table view
        tableView.setMaxHeight(300);
        tableView.setMaxWidth(920);
        tableView.setItems(observableList);
        tableView.getColumns().addAll(name,countWin,countDraws,countDefeats,countPoints,countScore,countReceived,goalsDifference,countMatches);         //Add column to table

        stackPane2.getChildren().addAll(tableView,label2);
        Scene scene=new Scene(stackPane2,1200,500);
        stage2.setScene(scene);
        stage2.showAndWait();

    }

    @Override
    public void sortWin() {
        Collections.sort(AddClubDetails, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return Integer.valueOf(o2.getCountOfWins()).compareTo(o1.getCountOfWins());
            }
        });
        Stage stage3=new Stage();
        stage3.setTitle("Sorting Wins");
        StackPane stackPane3=new StackPane();
        stackPane3.setStyle("-fx-background-color: DARKCYAN");
        Label label3=new Label("Sort By Wins");
        stackPane3.setAlignment(label3, Pos.TOP_CENTER);
        label3.setFont(Font.font("Cambria",36));
        label3.setStyle("-fx-font-weight: bold;");
        ObservableList<FootballClub> observableList = FXCollections.observableArrayList(AddClubDetails);

        TableColumn<FootballClub,String> name=new TableColumn<>("Name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<FootballClub,Integer> countWin=new TableColumn<>("Win");
        countWin.setMinWidth(100);
        countWin.setCellValueFactory(new PropertyValueFactory<>("countOfWins"));

        TableColumn<FootballClub,Integer> countDraws=new TableColumn<>("Draws");
        countDraws.setMinWidth(100);
        countDraws.setCellValueFactory(new PropertyValueFactory<>("countOfDraws"));

        TableColumn<FootballClub,Integer> countDefeats=new TableColumn<>("Defeats");
        countDefeats.setMinWidth(100);
        countDefeats.setCellValueFactory(new PropertyValueFactory<>("numOfDefeats"));

        TableColumn<FootballClub,Integer> countPoints=new TableColumn<>("Points");
        countPoints.setMinWidth(100);
        countPoints.setCellValueFactory(new PropertyValueFactory<>("numbersOfPoint"));

        TableColumn<FootballClub,Integer> countScore=new TableColumn<>("Score");
        countScore.setMinWidth(100);
        countScore.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScore"));

        TableColumn<FootballClub,Integer> countReceived=new TableColumn<>("Received");
        countReceived.setMinWidth(100);
        countReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));

        TableColumn<FootballClub,Integer> countMatches=new TableColumn<>("Matches");
        countMatches.setMinWidth(100);
        countMatches.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        TableColumn<FootballClub,Integer> goalsDifference=new TableColumn<>("GoalsDifference");
        goalsDifference.setMinWidth(120);
        goalsDifference.setCellValueFactory(new PropertyValueFactory<>("goalsDifference"));


        TableView tableView=new TableView();           //Create table view
        tableView.setMaxHeight(300);
        tableView.setMaxWidth(920);
        tableView.setItems(observableList);
        tableView.getColumns().addAll(name,countWin,countDraws,countDefeats,countPoints,countScore,countReceived,goalsDifference,countMatches);         //Add column to table

        stackPane3.getChildren().addAll(tableView,label3);
        Scene scene=new Scene(stackPane3,1200,500);
        stage3.setScene(scene);
        stage3.showAndWait();
    }


    @Override
    public void sortDate() {
        Collections.sort(AddMatch, new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                return Integer.valueOf(o2.getDay()).compareTo(o1.getDay());
            }
        });
        Collections.sort(AddMatch, new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                return Integer.valueOf(o2.getMonth()).compareTo(o1.getMonth());
            }
        });
        Collections.sort(AddMatch, new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                return Integer.valueOf(o2.getYear()).compareTo(o1.getYear());
            }
        });

        Stage stage1=new Stage();
        stage1.setTitle("Sorting Date");
        StackPane stackPane1=new StackPane();
        Label label=new Label("Sort By Date");
        stackPane1.setAlignment(label, Pos.TOP_CENTER);         //Alignment label
        label.setFont(Font.font("Cambria",36));
        label.setStyle("-fx-font-weight: bold;");
        stackPane1.setStyle("-fx-background-color: DARKCYAN");



        ObservableList<Match> observableList1 = FXCollections.observableArrayList(AddMatch);
        TableColumn<Match,String> date1=new TableColumn<>("Date");
        date1.setMinWidth(120);
        date1.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Match,String> team1Name=new TableColumn<>("Team 1-Name");
        team1Name.setMinWidth(120);
        team1Name.setCellValueFactory(new PropertyValueFactory<>("team1"));

        TableColumn<Match,Integer> team1Score=new TableColumn<>("Team 1-score");
        team1Score.setMinWidth(120);
        team1Score.setCellValueFactory(new PropertyValueFactory<>("team1Score"));

        TableColumn<FootballClub,Integer> team1Point=new TableColumn<>("Team 1-Point");
        team1Point.setMinWidth(120);
        team1Point.setCellValueFactory(new PropertyValueFactory<>("team1NumbersOfPoint"));

        TableColumn<Match,Integer> team1Received=new TableColumn<>("Team 1-Received");
        team1Received.setMinWidth(130);
        team1Received.setCellValueFactory(new PropertyValueFactory<>("team1NumberOfGoalsReceived"));

        TableColumn<Match,Integer> team1GoalsDifference=new TableColumn<>("Team 1-GD");
        team1GoalsDifference.setMinWidth(120);
        team1GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team1GoalsDifference"));

        TableColumn<Match,String> team2Name=new TableColumn<>("Team 2-Name");
        team2Name.setMinWidth(120);
        team2Name.setCellValueFactory(new PropertyValueFactory<>("team2"));

        TableColumn<Match,Integer> team2Score=new TableColumn<>("Team 2-Score");
        team2Score.setMinWidth(120);
        team2Score.setCellValueFactory(new PropertyValueFactory<>("team2Score"));

        TableColumn<FootballClub,Integer> team2Point=new TableColumn<>("Team 2-Point");
        team2Point.setMinWidth(120);
        team2Point.setCellValueFactory(new PropertyValueFactory<>("team2NumbersOfPoint"));

        TableColumn<Match,Integer> team2Received=new TableColumn<>("Team 2-Received");
        team2Received.setMinWidth(130);
        team2Received.setCellValueFactory(new PropertyValueFactory<>("team2NumberOfGoalsReceived"));

        TableColumn<Match,Integer> team2GoalsDifference=new TableColumn<>("Team 2-GD");
        team2GoalsDifference.setMinWidth(120);
        team2GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team2GoalsDifference"));





        TableView tableView1=new TableView();           //Create table view
        tableView1.setMaxHeight(300);
        tableView1.setMaxWidth(1350);
        tableView1.setItems(observableList1);
        tableView1.getColumns().addAll(date1,team1Name,team1Score,team1Point,team1Received,team1GoalsDifference,team2Name,team2Score,team2Point,team2Received,team2GoalsDifference);     //Add column to table

        stackPane1.getChildren().add(label);
        stackPane1.getChildren().add(tableView1);
        Scene scene=new Scene(stackPane1,1400,500);
        stage1.setScene(scene);
        stage1.showAndWait();
    }

    @Override
    public void randomMatchPlay(){

        Stage stage2=new Stage();
        stage2.setTitle("Random playing");
        StackPane stackPane2=new StackPane();
        Label label1=new Label("Random Played Match");
        stackPane2.setAlignment(label1, Pos.TOP_CENTER);         //Alignment label
        label1.setFont(Font.font("Cambria",36));
        label1.setStyle("-fx-font-weight: bold;");
        Button button=new Button("Generating Match");

        stackPane2.setStyle("-fx-background-color: DARKCYAN");

        ObservableList<Match> observableList2 = FXCollections.observableArrayList(AddMatch);
        TableColumn<Match,String> date1=new TableColumn<>("Date");
        date1.setMinWidth(120);
        date1.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Match,String> team1Name=new TableColumn<>("Team 1-Name");
        team1Name.setMinWidth(120);
        team1Name.setCellValueFactory(new PropertyValueFactory<>("team1"));

        TableColumn<Match,Integer> team1Score=new TableColumn<>("Team 1-score");
        team1Score.setMinWidth(120);
        team1Score.setCellValueFactory(new PropertyValueFactory<>("team1Score"));

        TableColumn<FootballClub,Integer> team1Point=new TableColumn<>("Team 1-Point");
        team1Point.setMinWidth(120);
        team1Point.setCellValueFactory(new PropertyValueFactory<>("team1NumbersOfPoint"));

        TableColumn<Match,Integer> team1Received=new TableColumn<>("Team 1-Received");
        team1Received.setMinWidth(130);
        team1Received.setCellValueFactory(new PropertyValueFactory<>("team1NumberOfGoalsReceived"));

        TableColumn<Match,Integer> team1GoalsDifference=new TableColumn<>("Team 1-GD");
        team1GoalsDifference.setMinWidth(120);
        team1GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team1GoalsDifference"));

        TableColumn<Match,String> team2Name=new TableColumn<>("Team 2-Name");
        team2Name.setMinWidth(120);
        team2Name.setCellValueFactory(new PropertyValueFactory<>("team2"));

        TableColumn<Match,Integer> team2Score=new TableColumn<>("Team 2-Score");
        team2Score.setMinWidth(120);
        team2Score.setCellValueFactory(new PropertyValueFactory<>("team2Score"));

        TableColumn<FootballClub,Integer> team2Point=new TableColumn<>("Team 2-Point");
        team2Point.setMinWidth(120);
        team2Point.setCellValueFactory(new PropertyValueFactory<>("team2NumbersOfPoint"));

        TableColumn<Match,Integer> team2Received=new TableColumn<>("Team 2-Received");
        team2Received.setMinWidth(130);
        team2Received.setCellValueFactory(new PropertyValueFactory<>("team2NumberOfGoalsReceived"));

        TableColumn<Match,Integer> team2GoalsDifference=new TableColumn<>("Team 2-GD");
        team2GoalsDifference.setMinWidth(120);
        team2GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team2GoalsDifference"));


        TableView tableView2=new TableView();           //Create table view
        tableView2.setMaxHeight(300);
        tableView2.setMaxWidth(1350);
        tableView2.setItems(observableList2);
        tableView2.getColumns().addAll(date1,team1Name,team1Score,team1Point,team1Received,team1GoalsDifference,team2Name,team2Score,team2Point,team2Received,team2GoalsDifference);     //Add column to table
        stackPane2.getChildren().add(tableView2);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Random random =new Random();
                int team1randScore=random.nextInt(20);
                int team2randScore=random.nextInt(20);

                int randDay=random.nextInt(30)+1;     //((max - min) + 1) + min  , nextInt as provided by Random is exclusive of the top value so you need to add 1
                int randMonth=random.nextInt(12)+1;
                int randYear=2000+random.nextInt(20)+1;

                Date date=new Date(randDay,randMonth,randYear);

                int size=AddClubDetails.size();
                int count1=random.nextInt(size);
                int count2=random.nextInt(size);

                while (count1==count2){                 //check count1 same as count2
                    count2=random.nextInt(size);
                }

                FootballClub s1=AddClubDetails.get(count1);
                FootballClub s2=AddClubDetails.get(count2);

                String randName1=s1.getClubName();
                String randName2=s2.getClubName();

                int gDTeam1=team2randScore-team1randScore;
                int gDTeam2=team1randScore-team2randScore;
                int pointTeam1=0;
                int pointTeam2=0;

                if (team1randScore>team2randScore) {                //Check the score team 1 and team 2
                    pointTeam1=3;
                    s2.setNumbersOfPoint(s1.getNumbersOfPoint()+3);
                    s1.setCountOfWins(s1.getCountOfWins()+1);
                    s2.setNumOfDefeats(s2.getNumOfDefeats()+1);
                }
                else if (team1randScore==team2randScore){           //Check the score same team 1 and team 2
                    pointTeam1=1;
                    pointTeam2=1;
                    s1.setCountOfDraws(s1.getCountOfDraws()+1);
                    s1.setNumbersOfPoint(s1.getNumbersOfPoint()+1);
                    s1.setCountOfDraws(s2.getCountOfDraws()+1);
                    s2.setNumbersOfPoint(s2.getNumbersOfPoint()+1);

                }
                else {
                    pointTeam2=3;
                    s2.setCountOfWins(s1.getCountOfWins()+1);
                    s2.setNumbersOfPoint(s2.getNumbersOfPoint()+3);
                    s1.setNumOfDefeats(s1.getNumOfDefeats()+1);
                }

                s1.setMatchesPlayed(s1.getMatchesPlayed()+1);                   //set match details
                s1.setNumberOfGoalsScore(s1.getNumberOfGoalsScore()+team1randScore);
                s1.setNumberOfGoalsReceived(s1.getNumberOfGoalsReceived()+team2randScore);
                s1.setGoalsDifference(s1.getGoalsDifference()+gDTeam1);
                s2.setMatchesPlayed(s2.getMatchesPlayed()+1);
                s2.setNumberOfGoalsScore(s2.getNumberOfGoalsScore()+team2randScore);
                s2.setNumberOfGoalsReceived(s2.getNumberOfGoalsReceived()+team1randScore);
                s2.setGoalsDifference(s2.getGoalsDifference()+gDTeam2);


                Match match1=new Match(pointTeam1,pointTeam2,gDTeam1,gDTeam2,team2randScore,team1randScore,team1randScore,team2randScore,randName1,randName2,date);
                AddMatch.add(match1);

                stackPane2.setStyle("-fx-background-color: DARKCYAN");

                ObservableList<Match> observableList2 = FXCollections.observableArrayList(AddMatch);
                TableColumn<Match,String> date1=new TableColumn<>("Date");
                date1.setMinWidth(120);
                date1.setCellValueFactory(new PropertyValueFactory<>("date"));

                TableColumn<Match,String> team1Name=new TableColumn<>("Team 1-Name");
                team1Name.setMinWidth(120);
                team1Name.setCellValueFactory(new PropertyValueFactory<>("team1"));

                TableColumn<Match,Integer> team1Score=new TableColumn<>("Team 1-score");
                team1Score.setMinWidth(120);
                team1Score.setCellValueFactory(new PropertyValueFactory<>("team1Score"));

                TableColumn<FootballClub,String> team1Point=new TableColumn<>("Team 1-Point");
                team1Point.setMinWidth(120);
                team1Point.setCellValueFactory(new PropertyValueFactory<>("team1NumbersOfPoint"));

                TableColumn<Match,Integer> team1Received=new TableColumn<>("Team 1-Received");
                team1Received.setMinWidth(130);
                team1Received.setCellValueFactory(new PropertyValueFactory<>("team1NumberOfGoalsReceived"));

                TableColumn<Match,Integer> team1GoalsDifference=new TableColumn<>("Team 1-GD");
                team1GoalsDifference.setMinWidth(120);
                team1GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team1GoalsDifference"));

                TableColumn<Match,String> team2Name=new TableColumn<>("Team 2-Name");
                team2Name.setMinWidth(120);
                team2Name.setCellValueFactory(new PropertyValueFactory<>("team2"));

                TableColumn<Match,Integer> team2Score=new TableColumn<>("Team 2-Score");
                team2Score.setMinWidth(120);
                team2Score.setCellValueFactory(new PropertyValueFactory<>("team2Score"));

                TableColumn<FootballClub,Integer> team2Point=new TableColumn<>("Team 2-Point");
                team2Point.setMinWidth(120);
                team2Point.setCellValueFactory(new PropertyValueFactory<>("team2NumbersOfPoint"));

                TableColumn<Match,Integer> team2Received=new TableColumn<>("Team 2-Received");
                team2Received.setMinWidth(130);
                team2Received.setCellValueFactory(new PropertyValueFactory<>("team2NumberOfGoalsReceived"));

                TableColumn<Match,Integer> team2GoalsDifference=new TableColumn<>("Team 2-GD");
                team2GoalsDifference.setMinWidth(120);
                team2GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team2GoalsDifference"));


                TableView tableView2=new TableView();           //Create table view
                tableView2.setMaxHeight(300);
                tableView2.setMaxWidth(1350);
                tableView2.setItems(observableList2);
                tableView2.getColumns().addAll(date1,team1Name,team1Score,team1Point,team1Received,team1GoalsDifference,team2Name,team2Score,team2Point,team2Received,team2GoalsDifference);     //Add column to table
                stackPane2.getChildren().add(tableView2);
            }
        };
        button.setOnAction(event);
        button.setMaxSize(150,20);
        stackPane2.setAlignment(button,Pos.BOTTOM_CENTER);      //Alignment Button
        button.setFont(Font.font("Cambria",25));
        button.setStyle("-fx-font-weight: bold;");



        stackPane2.getChildren().addAll(label1,button);
        Scene scene=new Scene(stackPane2,1400,500);
        stage2.setScene(scene);
        stage2.showAndWait();

    }
    @Override
    public void dateSearch() {

        Stage stage3=new Stage();
        stage3.setTitle("Date ");
        GridPane gridPane=new GridPane();
        Label label3=new Label("Search Date Match Details");

        label3.setFont(Font.font("Cambria",36));
        label3.setStyle("-fx-font-weight: bold;");

        gridPane.setStyle("-fx-background-color: DARKCYAN");


        Label label4=new Label("Date");
        Label label5=new Label("Month");
        Label label6=new Label("Year");
        label4.setFont(Font.font("Cambria",24));
        label5.setFont(Font.font("Cambria",24));
        label6.setFont(Font.font("Cambria",24));


        Button button2=new Button("Search");

        button2.setMaxSize(150,20);
        button2.setFont(Font.font("Cambria",25));
        button2.setStyle("-fx-font-weight: bold;");

        gridPane.setVgap(8);                 //Setting the vertical and horizontal gaps between the columns
        gridPane.setHgap(8);

        TextField textField=new TextField();
        TextField textField1=new TextField();
        TextField textField2=new TextField();
        gridPane.add(label4,1,2);
        gridPane.add(textField ,2,2);
        gridPane.add(label5,1,3);
        gridPane.add(textField1,2,3);
        gridPane.add(label6,1,4);
        gridPane.add(textField2,2,4);
        gridPane.add(button2,5,5);



        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {//create EventHandler
            public void handle(ActionEvent e) {
                if (!textField.getText().equals("") & !textField1.getText().equals("") & !textField2.getText().equals("")) {        //Check the date year and month text field are fill
                    int textFieldDay = Integer.parseInt(textField.getText());
                    int textFieldMonth = Integer.parseInt(textField1.getText());
                    int textFieldYear = Integer.parseInt(textField2.getText());
                    boolean b = false;
                    ArrayList<Match> DateSearch = new ArrayList<>();
                    for (Match m : AddMatch) {
                        if (m.getDay() == textFieldDay) {
                            if (m.getMonth() == textFieldMonth) {
                                if (m.getYear() == textFieldYear) {

                                    Match m2 = new Match(m.getTeam1NumbersOfPoint(), m.getTeam2NumbersOfPoint(), m.getTeam1GoalsDifference(), m.getTeam2GoalsDifference(), m.getTeam1NumberOfGoalsReceived(), m.getTeam2NumberOfGoalsReceived(), m.getTeam1Score(), m.getTeam2Score(), m.getTeam1(), m.getTeam2(), m.getDate());
                                    DateSearch.add(m2);

                                    b = true;
                                }
                            }
                        }

                    }

                    if (b) {
                        Stage stage4 = new Stage();
                        stage4.setTitle("Date ");
                        GridPane gridPane1 = new GridPane();
                        gridPane1.setStyle("-fx-background-color: DARKCYAN");


                        ObservableList<Match> observableListDS = FXCollections.observableArrayList(DateSearch);
                        TableColumn<Match, String> date1 = new TableColumn<>("Date");
                        date1.setMinWidth(120);
                        date1.setCellValueFactory(new PropertyValueFactory<>("date"));

                        TableColumn<Match, String> team1Name = new TableColumn<>("Team 1-Name");
                        team1Name.setMinWidth(120);
                        team1Name.setCellValueFactory(new PropertyValueFactory<>("team1"));

                        TableColumn<Match, Integer> team1Score = new TableColumn<>("Team 1-score");
                        team1Score.setMinWidth(120);
                        team1Score.setCellValueFactory(new PropertyValueFactory<>("team1Score"));

                        TableColumn<FootballClub, String> team1Point = new TableColumn<>("Team 1-Point");
                        team1Point.setMinWidth(120);
                        team1Point.setCellValueFactory(new PropertyValueFactory<>("team1NumbersOfPoint"));

                        TableColumn<Match, Integer> team1Received = new TableColumn<>("Team 1-Received");
                        team1Received.setMinWidth(130);
                        team1Received.setCellValueFactory(new PropertyValueFactory<>("team1NumberOfGoalsReceived"));

                        TableColumn<Match, Integer> team1GoalsDifference = new TableColumn<>("Team 1-GD");
                        team1GoalsDifference.setMinWidth(120);
                        team1GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team1GoalsDifference"));

                        TableColumn<Match, String> team2Name = new TableColumn<>("Team 2-Name");
                        team2Name.setMinWidth(120);
                        team2Name.setCellValueFactory(new PropertyValueFactory<>("team2"));

                        TableColumn<Match, Integer> team2Score = new TableColumn<>("Team 2-Score");
                        team2Score.setMinWidth(120);
                        team2Score.setCellValueFactory(new PropertyValueFactory<>("team2Score"));

                        TableColumn<FootballClub, Integer> team2Point = new TableColumn<>("Team 2-Point");
                        team2Point.setMinWidth(120);
                        team2Point.setCellValueFactory(new PropertyValueFactory<>("team2NumbersOfPoint"));

                        TableColumn<Match, Integer> team2Received = new TableColumn<>("Team 2-Received");
                        team2Received.setMinWidth(130);
                        team2Received.setCellValueFactory(new PropertyValueFactory<>("team2NumberOfGoalsReceived"));

                        TableColumn<Match, Integer> team2GoalsDifference = new TableColumn<>("Team 2-GD");
                        team2GoalsDifference.setMinWidth(120);
                        team2GoalsDifference.setCellValueFactory(new PropertyValueFactory<>("team2GoalsDifference"));


                        TableView tableView3 = new TableView();           //Create table view
                        tableView3.setMaxHeight(300);
                        tableView3.setMaxWidth(1350);
                        tableView3.setItems(observableListDS);
                        tableView3.getColumns().addAll(date1, team1Name, team1Score, team1Point, team1Received, team1GoalsDifference, team2Name, team2Score, team2Point, team2Received, team2GoalsDifference);     //Add column to table
                        gridPane1.getChildren().add(tableView3);
                        button2.setDisable(true);    //disable button

                        Scene scene2 = new Scene(gridPane1, 1340, 300);
                        stage4.setScene(scene2);
                        stage4.showAndWait();

                    } else {
                        Label label6 = new Label("No Data On That Date..");
                        button2.setDisable(true);    //disable button
                        label6.setFont(Font.font("Cambria", 26));
                        label6.setStyle("-fx-font-weight: bold;");
                        gridPane.add(label6, 5, 7);

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("No Dated Prompted!");
                    alert.show();

                }

            }
        };
        button2.setOnAction(event);


        gridPane.getChildren().add(label3);
        Scene scene1=new Scene(gridPane,1400,500);
        stage3.setScene(scene1);
        stage3.showAndWait();
    }
}






