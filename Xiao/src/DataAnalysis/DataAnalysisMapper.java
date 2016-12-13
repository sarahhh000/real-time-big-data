package dataAnalysis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DataAnalysisMapper extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString().toLowerCase();
    String[] fields = line.split(",");
    double latitude = Double.parseDouble(fields[0]);
    double longitude = Double.parseDouble(fields[1]);
    double speed = Double.parseDouble(fields[2]);
    long timestamp = Long.parseLong(fields[3]);
    Date d = new Date(timestamp * 1000);
    String date = new SimpleDateFormat("yyyyMMdd").format(d);
    String hour = new SimpleDateFormat("HH").format(d);
    String id = fields[4];
    int block = findBlock(latitude, longitude);
    String keys = block + " " + hour;
    if (block > 0 && block <= 700)
      context.write(new Text(keys), new Text(date + id + "," + speed));
  }

  private static int findBlock(double latitude, double longitude) {
    final double NORTH = 33.920215;
    final double SOUTH = 33.290604;
    final double EAST = -111.960540;
    final double WEST = -112.323217;
    if (latitude < SOUTH || latitude > NORTH || longitude < WEST || longitude > EAST)
      return -1;
    int row = (int) (35 * (NORTH - latitude) / (NORTH - SOUTH)); // 0 to 34
    int col = (int) (20 * (longitude - WEST) / (EAST - WEST)); // 0 to 19
    if (row == 35)
      row = 34;
    if (col == 20)
      col = 19;
    int block = row * 20 + col + 1;
    return block;
  }
}
