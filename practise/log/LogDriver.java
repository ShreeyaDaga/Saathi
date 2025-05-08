package practise.log;

public class LogDriver {
    public static void main(String[] args){
        Path input = new Path(args[0]);
        Path output = new Path(args[1]);
        
        Job job = new Job();

        job.setJarByClass(LogDriver.class);
        job.setJarName("Log Time");

        FileInputFormat.setInputPath(job, input);
        FileOutputFormat.setOutputPath(job, output);

        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);
    }
}
