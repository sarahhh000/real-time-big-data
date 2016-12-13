package pac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FormatData {
  public static void main(String[] args) throws IOException {
//    String file_name = "phoenix_complete_feature_clean.csv";
    String file_name = "phoenix_complete_feature_clean.csv";
    PrintWriter writer = new PrintWriter("phoenix_complete_feature_clean", "UTF-8");
    BufferedReader br = new BufferedReader(new FileReader(file_name));
    br.readLine();
    while (true) {
      String line = br.readLine();
      if (line == null)
        break;
      String[] strs = line.split(",");
      writer.print(strs[0] + " ");
      for (int i = 1; i < strs.length; i++) {
        if (i == strs.length - 1) {
          writer.print(i + ":" + strs[i]);
        } else {
          writer.print(i + ":" + strs[i] + " ");
        }
      }
      writer.println();
    }
    br.close();
    writer.close();
  }
}
