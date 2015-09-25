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

public class Query3 {

	public static class MapClass extends Mapper<LongWritable, Text, Text, IntWritable> {
		private static final IntWritable one = new IntWritable(1);
		
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
			String[] tokens = value.toString().split(",");
			context.write(new Text(tokens[2]), one);
		}
		
	}
	
	public static class ReduceClass extends Reducer<Text, IntWritable, Text, IntWritable> {
		private HashMap<String, Integer> map;
		
		public void setup(Context context) throws IOException, InterruptedException{
			map = new HashMap<String, Integer>();
		}
		
		public void reduce(Text key, Iterable<IntWritable> value, Context context) {
			int count = 0;
			for (IntWritable num: value) {
				count += num.get();
			}
			map.put(key.toString(), count);
		}
		
		public void cleanup(Context context) throws IOException, InterruptedException {
			ArrayList<Map.Entry<String, Integer>> list = sortValue(map);
			int i = 1;
			for (Map.Entry<String, Integer> temp: list) {
				context.write(new Text(temp.getKey()), new IntWritable(temp.getValue()));
				if (i == 10) return;
				i++;
			}
		}
		
		public ArrayList<Map.Entry<String, Integer>> sortValue(HashMap<String, Integer> map) {
			ArrayList<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){

		         public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		            return o2.getValue().compareTo(o1.getValue());
		        }});
			return list;
		}
	}
	
	public static class Driver extends Configured implements Tool {
		@Override
		public int run(String[] args) throws Exception {
			Job job = new Job(getConf());
			Configuration conf = job.getConfiguration();
			job.setJobName("Query 3");
			job.setJarByClass(Driver.class);
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			job.setMapperClass(MapClass.class);
			job.setReducerClass(ReduceClass.class);
			boolean success = job.waitForCompletion(true);
			return success? 0 : 1;
		}}
		
//		public static void main(String[] args) throws Exception{
//			int exitCode = ToolRunner.run(new Configuration(), new Driver(), args);
//			System.exit(exitCode);
//		}
}
