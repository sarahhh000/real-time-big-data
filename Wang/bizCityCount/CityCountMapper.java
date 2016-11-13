import java.io.IOException;
import com.google.gson.Gson;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.HashMap;

public class CityCountMapper
    extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException{
            Gson gson = new Gson();
            HashMap<String, Object> bizmap;
            bizmap = JsonParsing.createHashMapFromJsonString(value.toString());
            String cityinfo = (String)bizmap.get("state") + ", " + (String)bizmap.get("city");

            context.write(new Text(cityinfo), new IntWritable(1));
    }
}