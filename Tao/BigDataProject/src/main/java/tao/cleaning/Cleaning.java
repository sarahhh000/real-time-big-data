package cleaning;

import java.util.Scanner;

public class Cleaning {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // remove non latin-basic characters
            String clean = line.replaceAll("[^\\x00-\\x7F]", "").trim();
            // remove records with \n or \r inside the tweets
            String[] tweet = clean.split("\t");
            if (tweet.length != 5) continue;

            System.out.println(clean);
        }
    }
}