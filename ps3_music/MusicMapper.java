package music;

import java.io.IOException;

import javax.naming.Context;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.w3c.dom.Text;



public class MusicMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() == 0 && value.toString().contains("UserId")) return; // skip header
        String[] fields = value.toString().split(",");
        // You only require at least three fields; the extra “Radio” and “Skip” columns (indices 3 and 4) are simply ignored.
        if (fields.length < 3) return;

        String userId = fields[0].trim();
        String trackId = fields[1].trim();
        String shared = fields[2].trim();

        // Key: track ID (as Text)
        // Value: "USER:111115"
        // → downstream you can aggregate which users listened.
        context.write(new Text(trackId), new Text("USER:" + userId));

        if (shared.equals("1")) { // Only if the “Shared” column was 1 do you also emit
            context.write(new Text(trackId), new Text("SHARED"));
        }
    }
}