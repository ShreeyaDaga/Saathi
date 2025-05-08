package logfile;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// input key(user name), input value(list of time spent by the user), output key(user name), output value(total time spent by the user)
public class LogReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	@Override
	// key - the user
	// values - list of session durations for that user
	// context.wrte() - emits the final results
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
		int totalTime = 0;
		// iterates over all the values for a given key
		for(IntWritable value : values){
			totalTime += value.get();
		}
		context.write(key, new IntWritable(totalTime));
	}
}
