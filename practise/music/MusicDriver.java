package practise.music;

import java.io.FileOutputStream;

public class MusicDriver {
    public static void main(String[] args){
        Configuration conf = new Configuration();
        Job job = job.getInstance(conf, "Music Analysis");

        job.setJarByClass(MusicDriver.class);
        job.setJarName("Music Jar");

        job.setMapperClass(MusicMapper.class);
        job.setReducerClass(MusicReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(waitForCompletion(true) ? 0 : 1);


    }
    
}
