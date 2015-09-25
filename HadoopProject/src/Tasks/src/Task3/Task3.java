package Task3;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Task3 {

		
	
	public static class Driver extends Configured implements Tool {
		@Override
		public int run(String[] args) throws Exception {
			Job job = new Job(getConf());
			Configuration conf = job.getConfiguration();
			job.setJobName("Task3");
			job.setJarByClass(Task3.class);
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			job.setMapperClass(Task3Mapper.class);
			job.setReducerClass(Task3Reducer.class);
			boolean success = job.waitForCompletion(true);
			return success? 0 : 1;
		}}
		
		public static void main(String[] args) throws Exception{
			int exitCode = ToolRunner.run(new Configuration(), new Driver(), args);
			System.exit(exitCode);
		}
}
