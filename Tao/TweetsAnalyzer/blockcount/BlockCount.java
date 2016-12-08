import java.util.Scanner;
import java.lang.Math;

public class BlockCount{

    private static double[] rangeLati = {33.290604, 33.920215};
    private static double[] rangeLongi = {-112.323217, -111.960540};
    private static int[] splitNum = {35, 20};
    private static double splitLati = (rangeLati[1] - rangeLati[0]) / splitNum[0];
    private static double splitLongi = (rangeLongi[1] - rangeLongi[0]) / splitNum[1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] blockCount = new int[splitNum[0] * splitNum[1] + 1];
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split("\t");
            if (split[2].equals("null")) { continue; }
            String[] geo = split[2].split(",");
            double geoLongi = Double.parseDouble(geo[0]);
            double geoLati = Double.parseDouble(geo[1]);
            if (geoLongi >= rangeLongi[0] && geoLongi <= rangeLongi[1]
                && geoLati >= rangeLati[0] && geoLati <= rangeLati[1]) {
                // find a valid result, decide its block number
                int row = (int) Math.ceil((geoLati - rangeLati[0]) / splitLati);
                int col = (int) Math.ceil((geoLongi - rangeLongi[0]) / splitLongi);
                int blockNum = (row - 1)* splitNum[1] + col;
                blockCount[blockNum]++;
                System.out.println(line + "\t" + blockNum);
            }
        }
        for (int i = 1; i < blockCount.length; i++) {
            System.err.print(blockCount[i] + "\t");
            if (i % splitNum[1] == 0) {
                System.err.println();
            }
        }
    }
}