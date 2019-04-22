import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class InvertedIndexMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    public void map(LongWritable key, Text value, Context context)
      throws IOException,InterruptedException {
        /*Get the name of the file using context.getInputSplit()method*/
        String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
        String line=value.toString();
        //Split the line in words
        String words[]=line.split(" ");
        for(String s:words) {
          //for each word emit word as key and file name as value
          context.write(new Text(s), new Text(fileName));
        }
    }
}
