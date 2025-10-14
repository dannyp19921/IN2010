//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;

import java.io.*;

// STDIN: Bruk av krokodilletegn osv. 
// Uten STDIN, da bruker vi argumenter til programmet, altså bruk av args[0] osv. 

class Main {
    public static void main(String[] args) {
        try {
            String filename = args[0];
            File file = new File(filename);
            BufferedReader in = new BufferedReader(new FileReader(file));
            int[] A = in.lines().mapToInt(i -> Integer.parseInt(i)).toArray();
            in.close();
    
            SortRunner.runAlgsPart1(A, filename);
            SortRunner.runAlgsPart2(A, filename);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}
