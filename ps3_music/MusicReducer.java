package music;

import java.io.IOException;
import java.util.HashSet;

import javax.naming.Context;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;
import org.w3c.dom.Text;

public class MusicReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // A HashSet<String> to collect unique user IDs (so duplicates are ignored).
        HashSet<String> uniqueUsers = new HashSet<>();
        int shareCount = 0;
        // val is either "USER:12345" or "SHARED".
        for (Text val : values) {
            String v = val.toString();
            if (v.startsWith("USER:")) {
                uniqueUsers.add(v.substring(5));
            } else if (v.equals("SHARED")) {
                shareCount++;
            }
        }

        context.write(key, new Text("UniqueListeners: " + uniqueUsers.size() + ", SharedCount: " + shareCount));
    }
}
