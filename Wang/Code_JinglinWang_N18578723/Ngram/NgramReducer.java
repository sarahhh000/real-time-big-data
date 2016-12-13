import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Arrays;
import java.lang.Math;
import java.util.Map.Entry;

public class NgramReducer
    extends Reducer<Text, Text, Text, Text> {

    class StringNode {
        String word;
        int count;

        public StringNode(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {

        HashMap<String, Integer> ngram = new HashMap<>();
        int wordNum = 0;
        for (Text value : values) {
            String val = value.toString().trim();
            if (val.length() == 0) { continue; }
            if(ngram.containsKey(val)){
                ngram.put(val, ngram.get(val) + 1);
            }
            else {
                ngram.put(val, 1);
                wordNum++;
            }
        }

        if (wordNum == 0) { return; }

        StringNode[] wordArray = new StringNode[wordNum];
        int i = 0;
        for (Entry<String, Integer> pair : ngram.entrySet()) {
            wordArray[i] = new StringNode(pair.getKey(), pair.getValue());
            i++;
        }

        Arrays.sort(wordArray, new Comparator<StringNode>() {
            @Override
            public int compare(StringNode n1, StringNode n2) {
                return n2.count - n1.count;
            }
        });

        String rlt = "";
        int length = Math.min(wordNum, 20);

        for(int j = 0; j < length; j++) {
            rlt = rlt + wordArray[j].word + ";";
        }

        context.write(key, new Text(rlt));
    }
}






