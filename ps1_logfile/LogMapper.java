package logfile;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/* 
IntWritable - Hadoop's default writable type for integer values
Haddop needs all data to be serialized(able to sent over a network) - so they have their own type
*/ 

// LongWritable - Hadoop's default writable type for long values

// Text - Hadoop's default writable type for string values

// Mapper - abstract class that defines the map method - processes input data and gives key-value pairs for further processing

// <LongWritable, Text, Text, IntWritable> - input key(offset of the input line), input value(the log line), output key(username), output value(time)

public class LogMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	// M/d/yyy  h:mm:ss a
	public SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy H:mm");
	
	@Override
	// map method is called for each line of the input file
	// value: a line of CSV-formatted log data - the actual content of the line being porcessed - data you split, parse and extract fields from
	// key: the offset of the line in the file - mostly ignored
	// context: the context of the map task bridge between the mapper and reducer
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		// splits csv into array at commas
		String[] fields = value.toString().split(",");
		try{	
			if(fields.length == 8){
				String user = fields[1].toString(); // user ID
				Date login = format.parse(fields[5]); // login time
				Date logout = format.parse(fields[7]); // logout time
				
				long time = (logout.getTime()/*return milliseconds */ - login.getTime())/(60*1000); // converts milliseconds to minutes
				
				if(time >= 0){				
					// emits the key-value output pairs from mapper
					context.write(new Text(user), new IntWritable((int)time));
				}
			}
		} catch (Exception e){
			// if anything fails - record is silently ignored
			// skip row
		}
	}
}
