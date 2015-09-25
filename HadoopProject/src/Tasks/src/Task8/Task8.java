package Task8;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/***
 * find the popularity of "interesting group", based on the hobbies
 * @author hadoop
 *
 */
public class Task8 {

	
	public static class Driver extends Configured implements Tool {
		@Override
		public int run(String[] args) throws Exception {
			Job job = new Job(getConf());
			Configuration conf = job.getConfiguration();
			job.setJobName("Task8");
			job.setJarByClass(Task8.class);
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			job.setMapperClass(Task8Mapper.class);
			job.setReducerClass(Task8Reducer.class);
			boolean success = job.waitForCompletion(true);
			return success? 0 : 1;
		}
		}
		
		public static void main(String[] args) throws Exception{
			int exitCode = ToolRunner.run(new Configuration(), new Driver(), args);
			System.exit(exitCode);
		}
}
