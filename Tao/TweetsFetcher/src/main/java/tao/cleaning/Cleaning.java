package cleaning;

import java.util.Scanner;

public class Cleaning {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // remove non latin-basic characters
            String clean = line.replaceAll("[^\\x20-\\x7E\t]", "").trim();
            System.out.println(clean);
        }
    }
}