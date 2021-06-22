import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String []args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage)throws IOException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in);

        PremierLeagueManager p1=new PremierLeagueManager();
        p1.lordData();

        menu:
        while (true) {
            try{
                System.out.println(" ");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^*************************************^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("*****************|P| |R| |E| |M| |I| |E| |R|   |L| |E| |A| |G| |U| |E|*******************");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^*************************************^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("                                    |M| |E| |N| |U|");
                System.out.println("");
                System.out.println("--> Enter A/a create new football club");
                System.out.println("--> Enter C/c add played Match");
                System.out.println("--> Enter W/w Display various statistics");
                System.out.println("--> Enter R/r display premier League table");
                System.out.println("--> Enter D/d delete an existing club");
                System.out.println("--> Enter U/u save the data");
                System.out.println("--> Enter F/f sort point");
                System.out.println("--> Enter Z/z sort goals scored ");
                System.out.println("--> Enter X/x sort wins");
                System.out.println("--> Enter S/s sort Data");
                System.out.println("--> Enter E/e Generate random Match");
                System.out.println("--> Enter T/t Search the date");
                System.out.println("--> Enter Q/q exit the programme");
                System.out.println("");


                System.out.println("--->Enter your Choice:");
                String letter = sc.next();

                switch (letter) {
                    case "A":
                    case "a":
                        p1.createClub();
                        break;
                    case "C":
                    case "c":
                        p1.addVariousStatistics();
                        break;
                    case "D":
                    case "d":
                        p1.deleteClub();
                        break;
                    case "R":
                    case "r":
                        p1.displayPremierLeagueTable();
                        break;
                    case"W":
                    case"w":
                        p1.displayClubStatistics();
                        break;
                    case"U":
                    case"u":
                        p1.saveData();
                        break;
                    case"F":
                    case"f":
                        p1.sortPoint();
                        break ;
                    case"Z":
                    case"z":
                        p1.sortGoals();
                        break;
                    case"X":
                    case"x":
                        p1.sortWin();
                        break;
                    case "S":
                    case "s":
                        p1.sortDate();
                        break;
                    case"E":
                    case"e":
                        p1.randomMatchPlay();
                        break ;
                    case"T":
                    case"t":
                        p1.dateSearch();
                        break ;
                    case"Q":
                    case"q":
                        System.out.println("Exit the programme.....!!!");
                        break menu;
                    default:
                        System.out.println("Please refer instruction");
                }
            }

            catch (Exception e){
                System.out.println("Invalid input");
            }

        }
    }
}

