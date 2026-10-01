import java.io.*;
import java.util.*;

public class MyFunctions {
    
    //PRE: TVList is an initialized HashMap
    //POST: Adds a new show and duration to the TVList map
    // Note: Wrote this function to be similar to the loadData function in Functions.Java.  
    public static void addShow(Map<Integer, ArrayList<String>> TVList, int duration, String showName) {
        if (TVList.containsKey(duration)) {
            TVList.get(duration).add(showName);
        } else {
            TVList.put(duration, new ArrayList<>(Arrays.asList(showName)));
        }
        System.out.println("This new show was added to the map: " + showName + " with duration: " + duration + " years.");
    }


    //PRE: TVList is an initialized HashMap
    //POST: Deletes a show from the TVList map
    // Note: I originally had this formatted like the function above. It was checking for the Key instead of 
    // iterating through for the name of the show. I prompted Gemini to help solve this problem, this was the result.
    public static void deleteShow(Map<Integer, ArrayList<String>> TVList, String showName) {
        boolean found = false;

        Iterator<Map.Entry<Integer, ArrayList<String>>> iterator = TVList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, ArrayList<String>> entry = iterator.next();
            ArrayList<String> shows = entry.getValue();
            if (shows.remove(showName)) {
                found = true;
                System.out.println("Deleted " + showName + " from the map.");
                if (shows.isEmpty()) {
                    iterator.remove(); // Remove the key if no shows are left
                }
                break; // Exit the loop after finding the duration
            }
        }
        if (!found) {
        System.out.println("Unable to delete " + showName + ". Item is not in the map.");
        }
    }

    //PRE: TVList is an initialized Map
    //POST: Prints all keys (durations) in the TVList map to the report file
    public static void printKeys(Map<Integer, ArrayList<String>> TVList, PrintWriter out) {
        out.println("Durations in the TV List:");
        for (Integer duration : TVList.keySet()) {
            out.println(duration);
        }
        out.flush(); // Ensure all data is written to the file
        //  Note: The flush() method can be seen in a few spots in this program. When running the script, I wanted to see 
        //  the output in the report.txt file immediately after the function is called. This was a Gemini suggestion.
        //  I'm not quite sure if this works completely, but it seems to be working as intended.
        System.out.println("All keys (durations) have been printed to report.txt.");
    }

    //PRE: TVList is an initialized HashMap
    //POST: Print map listing to the report file
    //Note: This would originally print the ArrayList with the Brackets. I prompted Gemini to help format this
    // to make it look cleaner. This was the result.
    public static void printMap(Map<Integer, ArrayList<String>> TVList, PrintWriter out) {
        for (Map.Entry<Integer, ArrayList<String>> entry : TVList.entrySet()) {
            String showsFormatted = String.join(", ", entry.getValue());
            out.println("Duration: " + entry.getKey() + ", Shows: " + showsFormatted);
        }
        out.flush(); // Ensure all data is written to the file
        System.out.println("Map Listing has been printed to report.txt.");
    }

    //PRE: TVList is an initialized HashMap
    //POST: Print all values for a specific key (duration) in the TVList map to the report file
    public static void printValuesForKey(Map<Integer, ArrayList<String>> TVList, int duration, PrintWriter out) {
        if (TVList.containsKey(duration)) {
            out.println("Shows for duration " + duration + ": " + TVList.get(duration));
        } else {
            System.out.println("Duration not found in the list.");
        }
        out.flush(); // Ensure all data is written to the file
        System.out.println("Values for the specified key (duration) have been printed to report.txt.");
    }

    //   Note: The two functions below were added to validate user input for the duration of a show.
    //PRE: none
    //POST: Verifies that the user input is numerical value.
    public static boolean isDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    //PRE: accepts a Scanner object
    //POST: returns a valid duration as an integer, or prompts the user to enter a valid duration if the input is not valid.
    public static int getValidDuration(Scanner scanner) {
    String input = scanner.nextLine();
    while (!isDigits(input)) {
        System.out.print("Invalid input. Please enter a valid duration (digits only): ");
        input = scanner.nextLine();
    }
    return Integer.parseInt(input);
}

}
