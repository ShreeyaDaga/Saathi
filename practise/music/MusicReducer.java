package practise.music;

import java.util.HashSet;

import org.w3c.dom.Text;

public class MusicReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Text values, Conext context){
        HashSet<String> uniqueUsers = new HashSet<>;
        int ShareCount = 0;

        for(Text val:values){
           String v = val.toString();
           if(v.startsWith("USER:")){
            uniqueUsers.add(v.substring(5))
           }
           else if(v.equals("SHARED")){
            shareCount++;
           }
        }
        context.write(key, new Text("Unque number of Users: " + uniqueUsers + ", Share Count : " + shareCount);
    }
    
}
