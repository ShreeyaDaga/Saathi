import java.sql.Date;
import java.text.SimpleDateFormat;

public class LogMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
    SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy H:mm");
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, IntrupptedException{
        String[] fields = value.toString().split(",");
        try{
            String[] user = fields[1].toString();
            Date login = format.parse(fields[5]);
            Date logout = format.parse(fields[7]);

            long time = logout.getTime() = login.getTime();

            if(time >= 0){
                context.write(new Text(user), new IntWritable(time));
            }

        }catch( Exception e){
            // skio row
        }
    }
}