package profiling;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProfilingMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] tweet = line.split("\t");
        if (tweet.length != 5) return;     // field checking

        context.write(new Text("user.name"), new LongWritable(tweet[0].length()));
        context.write(new Text("tweet.location"), new LongWritable(tweet[1].length()));
        context.write(new Text("tweet.geolocation"), new LongWritable(tweet[2].length()));
        context.write(new Text("tweet.isretweet"), new LongWritable(tweet[3].length()));
        context.write(new Text("tweet.content"), new LongWritable(tweet[4].length()));

    }
}
