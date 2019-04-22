import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.HashMap;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
  throws IOException, InterruptedException {
    /*Declare the Hash Map to store File name as key to compute and store number of times the filename is occurred for as value*/
    HashMap m=new HashMap();
    int count=0;
    for(Text t:values){
      String str=t.toString();
      /*Check if file name is present in the HashMap ,if File name is not present then add the Filename to the HashMap and increment the counter by one , This condition will be satisfied on first occurrence of that word*/
      if(m!=null &&m.get(str)!=null) {
        count=(int)m.get(str);
        m.put(str, ++count);
      } else {
        /*Else part will execute if file name is already added then just increase the count for that file name which is stored as key in the hash map*/
        m.put(str, 1);
      }
    }
    /* Emit word and [file1→count of the word1 in file1 , file2→count of the word1 in file2 ………] as output*/
    context.write(key, new Text(m.toString()));
  }
}
