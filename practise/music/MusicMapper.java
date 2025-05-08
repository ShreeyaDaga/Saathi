package practise.music;

public class MusicMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    public void main(LongWritable key, Text values, Context contexts){
        if(key.get() == 0 && values.toString().contains("UserID")) return;

        String[] fields = values.toString().split(",");
        if(fields.length < 3) return;

        String userid = fields[0].trim();
        String trackId = fields[1].trim();
        String shared = fields[2].trim();

        context.write(new Text(trackid), new Text("USER:"));

        if(shared.equals("1")){
            context.write(new Text(trackid), new Text("SHARED"));
        }
    }
    
}
