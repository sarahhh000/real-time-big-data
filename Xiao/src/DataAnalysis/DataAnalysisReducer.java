package dataAnalysis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DataAnalysisReducer extends Reducer<Text, Text, Text, Text> {
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    int speedCount = 0;
    double speedSum = 0;
    Set<String> set = new HashSet<>();
    for (Text value : values) {
      String[] strs = value.toString().split(",");
      String id = strs[0];
      double speed = Double.parseDouble(strs[1]);
      if (!set.contains(id)) {
        set.add(id);
        if (speed != 0) {
          speedCount++;
          speedSum += speed;
        }
      }
    }
    double res = 0;
    if (speedCount != 0) {
      res = speedSum / speedCount;
    }
    context.write(key, new Text(set.size() + " " + res));
  }
}
