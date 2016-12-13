package pac;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DataFormatReducer extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    String[] res = new String[48];
    Arrays.fill(res, "0");
    int total = 0;
    for (Text value : values) {
      String[] fields = value.toString().split(",");
      int hour = Integer.parseInt(fields[0]);
      res[hour * 2] = fields[1];
      res[hour * 2 + 1] = fields[2];
      total += Integer.parseInt(fields[1]);
    }
    StringBuilder sb = new StringBuilder();
    sb.append("," + total);
    for (int i = 0; i < res.length; i++) {
      sb.append("," + res[i]);
    }
    context.write(key, new Text(sb.toString()));
  }
}
