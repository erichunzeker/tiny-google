import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


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
        Set<String> stopwords = new HashSet<String>();
	       Text word = new Text();
        String fileName = ((FileSplit) context.getInputSplit()).getPath()
				.getName();

    		String line = value
    				.toString()
    				.replaceAll("[^\\w\\s]|('s|ly|ed|ing|ness|.|,|\\?|'|:|;) ", " ")
    				.toLowerCase();

    		StringTokenizer tokenizer = new StringTokenizer(line);
    		while (tokenizer.hasMoreTokens()) {
    			String wordText = tokenizer.nextToken();
    			if (stopwords.contains(wordText))
    				continue;
    			word.set(wordText);
    			context.write(word, new Text(fileName));
    		}
    }
}
