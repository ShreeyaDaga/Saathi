package logfile;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// driveer class - MapReduce job is created and configured here
public class LogTime {
	public static void main(String[] args) throws Exception{
		// HDFS input directory - /inputF1 - that we put in command line
		Path input = new Path(args[0]);
		// HDFS output directory - /outputF1 - that we put in command line
		Path output = new Path(args[1]);
		
		// Job - core configuration object for MapReduce task
		Job job = new Job();
		
		// tells hadoop where to find the jar file containing your conmplied classes
		job.setJarByClass(LogTime.class);
		// name of the job
		job.setJobName("Log Time");
		
		// tells hadoop where to read input data from and write output data to
		FileInputFormat.setInputPaths(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		// tells hadoop which classes to use for the map and reduce tasks
		job.setMapperClass(LogMapper.class);
		job.setReducerClass(LogReducer.class);
		
		// types of the output key and value from the mapper - Key:Text , Value: IntWritable
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		// final output key and value from the reducer - Key:Text , Value: IntWritable
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// waits for the job to complete and returns true if successful
		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}
