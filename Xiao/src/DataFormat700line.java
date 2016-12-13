package pac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DataFormat700line {

  public static void main(String[] args) throws IOException {
    String file_name = "gtfs_sorted.dat";
    BufferedReader br = new BufferedReader(new FileReader(file_name));
    StringBuilder sb = new StringBuilder();
    String s0s = "";
    for (int i = 0 ; i < 48; i++) {
      sb.append("0,");
    }
    sb.append("0");
    s0s = sb.toString();
    PrintWriter writer = new PrintWriter("gtfs_formatted.csv", "UTF-8");
    try {
      String line = br.readLine();
      for (int i = 1; i <= 700; i++) {
        String[] strs = line.split(",");
        if (Integer.parseInt(strs[0]) == i) {
          writer.println(line);
          line = br.readLine();
        } else {
          writer.println(i + "," + s0s);
        }
      }
    } finally {
      br.close();
      writer.close();
    }
  }
}
