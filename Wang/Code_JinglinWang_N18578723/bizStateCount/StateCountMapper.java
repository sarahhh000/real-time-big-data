import java.io.IOException;
import com.google.gson.Gson;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.HashMap;

public class StateCountMapper
    extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException{
            Gson gson = new Gson();
            HashMap<String, Object> bizmap;
            bizmap = JsonParsing.createHashMapFromJsonString(value.toString());

            context.write(new Text((String)bizmap.get("state")), new IntWritable(1));
    }
}