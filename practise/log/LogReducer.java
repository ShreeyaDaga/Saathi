public class LogReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    @Override
    public class reduce(Text key, IntWritable values, Context context) throws IOException, InterruptedExeption{
        int totalTime = 0;
        for(IntWritable value: values){
            totalTime += value.get();
        }
    }
}