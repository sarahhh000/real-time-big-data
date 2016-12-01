package profiling;

// cc MaxTemperatureMapper Mapper for maximum temperature example
// vv MaxTemperatureMapper
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProfilingMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private static final int MISSING = 9999;

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] tweet = line.split("\t");
        if (tweet.length != 4) return;     // field checking
        
        context.write(new Text("user.name"), new LongWritable(tweet[0].length()));
        context.write(new Text("user.location"), new LongWritable(tweet[1].length()));
        context.write(new Text("tweet.id"), new LongWritable(Long.parseLong(tweet[2])));
        context.write(new Text("tweet.content"), new LongWritable(tweet[3].length()));
        
    }
}
// ^^ MaxTemperatureMapper
