package pac;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DataFormatMapper extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString().toLowerCase();
    String[] fields = line.split("\\s+");
    String block = fields[0];
    String hour = fields[1];
    String carNum = fields[2];
    String aveSpeed = fields[3];
    context.write(new Text(block), new Text(hour + "," + carNum + "," + aveSpeed));
  }
}
