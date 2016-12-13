import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BizstarReducer
    extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {
        double starsum = 0;
        int count = 0;
        for (Text value : values) {
            String[] sumcount = value.toString().split("\t");
            starsum += Double.parseDouble(sumcount[0]);
            count += Integer.parseInt(sumcount[1]);
        }
        double normalizedstar = starsum/count;
        context.write(key, new Text(normalizedstar + ""));
    }
}