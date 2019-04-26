import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.HashMap;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
  throws IOException, InterruptedException {
    StringBuilder stb = new StringBuilder();
		HashMap<String, Integer> fileFreq = new HashMap<String, Integer>();

		for (Text val : values) {
			Integer count = fileFreq.get(val.toString());
			if (count == null) {
				count = 0;
			}
			fileFreq.put(val.toString(), count + 1);
		}
		context.write(key, new Text(fileFreq.toString()));
	}
}
