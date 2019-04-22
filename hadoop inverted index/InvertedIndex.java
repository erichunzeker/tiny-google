import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InvertedIndex {
    public static void main(String[] args) throws Exception {
      Configuration conf = new Configuration();
      Job job = Job.getInstance(conf, "Inverted Index");
      job.setJarByClass(InvertedIndex.class);
      job.setMapperClass(InvertedIndexMapper.class);
      job.setCombinerClass(InvertedIndexReducer.class);
      job.setReducerClass(InvertedIndexReducer.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      FileInputFormat.addInputPath(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));
      System.exit(job.waitForCompletion(true) ? 0 : 1);

    // Configuration conf= new Configuration();
    // Job job = new Job(conf,"UseCase1");
    // //Defining the output key and value class for the mapper
    // job.setMapOutputKeyClass(Text.class);
    // job.setMapOutputValueClass(Text.class);
    // job.setJarByClass(InvertedIndex.class);
    // job.setMapperClass(InvertedIndexMapper.class);
    // job.setReducerClass(InvertedIndexReducer.class);
    // //Defining the output value class for the mapper
    // job.setOutputKeyClass(Text.class);
    // job.setOutputValueClass(Text.class);
    // job.setInputFormatClass(TextInputFormat.class);
    // job.setOutputFormatClass(TextOutputFormat.class);
    // Path outputPath = new Path(args[1]);
    // FileInputFormat.addInputPath(job, new Path(args[0]));
    // FileOutputFormat.setOutputPath(job, outputPath);
    // //deleting the output path automatically from hdfs so that we don't have delete it explicitly
    // outputPath.getFileSystem(conf).delete(outputPath);
    // //exiting the job only if the flag value becomes false
    // System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
