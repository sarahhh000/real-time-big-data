package profiling;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ProfilingReducer extends Reducer<Text, LongWritable, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {

        long maxValue = Long.MIN_VALUE;
        long minValue = Long.MAX_VALUE;
        for (LongWritable value : values) {
            maxValue = Math.max(maxValue, value.get());
            minValue = Math.min(minValue, value.get());
        }

        context.write(key, new Text("[" + minValue + ", " + maxValue + "]"));
    }
}
