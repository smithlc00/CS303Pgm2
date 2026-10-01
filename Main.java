import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        System.out.println("Welcome to Program 2: Maps");
        Map<Integer, ArrayList<String>> TVList = new HashMap<>();

        Scanner input = new Scanner(System.in);

        //open output file
        try{
            PrintWriter out = new PrintWriter("report.txt");
            
            //load data into map
            Functions.loadData(TVList);

            //sort the map
            Map<Integer, ArrayList<String>> sortedTVList = new TreeMap<>(TVList);

            String menuItem = Functions.getMenuItem(input);

            while (!menuItem.equals("Q")){
                //test for valid menu options & call appropriate functions
                // Note: I began this section similar to other Menus we've written. The functions may have slightly 
                // different parameters, but the structure is the same.
                
                if (menuItem.equals("A")) {
                    System.out.print("Enter the duration of the show: ");
                    int duration = MyFunctions.getValidDuration(input);
                    System.out.print("Enter the name of the show: ");
                    String showName = input.nextLine();
                    MyFunctions.addShow(sortedTVList, duration, showName);
                } else if (menuItem.equals("D")) {
                    System.out.print("Enter the name of the show to delete: ");
                    String showName = input.nextLine();
                    MyFunctions.deleteShow(sortedTVList, showName);
                } else if (menuItem.equals("K")) {
                    MyFunctions.printKeys(sortedTVList, out);
                } else if (menuItem.equals("P")) {
                    MyFunctions.printMap(sortedTVList, out);
                } else if (menuItem.equals("S")) {
                    System.out.print("Enter the duration to print shows for: ");
                    int duration = MyFunctions.getValidDuration(input);
                    MyFunctions.printValuesForKey(sortedTVList, duration, out);
                }

                menuItem = Functions.getMenuItem(input);
            }

            //close files
            input.close();
            out.close();

        }
        catch (Exception e){
            System.out.println("Error in input record");
            return;
        }
    }
}


