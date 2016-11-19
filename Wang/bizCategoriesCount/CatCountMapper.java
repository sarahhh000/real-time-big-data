import java.io.IOException;
import com.google.gson.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.HashMap;

public class CatCountMapper
    extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException{
            Gson gson = new Gson();
            HashMap<String, Object> bizmap;
            bizmap = JsonParsing.createHashMapFromJsonString(value.toString());
            JsonArray arr;
            String str;
            arr = (JsonArray) bizmap.get("categories");
            for(int i = 0; i < arr.size(); i++) {
                str = arr.get(i).getAsString();
                context.write(new Text("\"" + str + "\""), new IntWritable(1));
            }
    }
}