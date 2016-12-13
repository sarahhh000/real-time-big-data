import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.Set;
import java.util.HashSet;

public class NgramMapper
    extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException{
            String[] bizinfo = value.toString().trim().split("\t");
            String[] words = bizinfo[7].replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
            String[] meaningless = {"I", "i", "am", "the", "on", "at", "a", "of",
                                    "will","to", "this", "you", "was", "were", "would", "they",
                                    "and", "an", "if", "had", "with","there", "in", "they", "their",
                                    "my", "is", "we", "been", "but", "her", "has", "as", "our","here",
                                    "that", "it", "than", "dose", "do", "did", "be", "are", "its","me",
                                    "him", "her", "for", "because", "have", "dont", "or", "when", "she",
                                    "but", "however", "whthin", "though", "after", "im"};
            Set<String> mlSet = new HashSet<>();
            for (String ml : meaningless) {
                mlSet.add(ml);
            }

            int pos;
            boolean flag;
            int len = words.length;

            for(int N = 2; N < 6; N++) {
                pos = 0;
                while(pos <= len - N) {
                    String nwords = "";
                    flag = true;
                    for(int i = pos; i < pos + N; i++) {
                        if (mlSet.contains(words[i])) {
                            flag = false;
                            pos = i;
                            break;
                        }
                        nwords += words[i] + " ";
                    }
                    String nwordstrim = nwords.trim();
                    if(flag == true && nwordstrim.length() > 0){
                        context.write(new Text(bizinfo[2] + ""), new Text(nwordstrim));
                    }
                    pos = pos + 1;
                }
            }
    }
}