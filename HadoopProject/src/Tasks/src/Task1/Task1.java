package Task1;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Write a job that reports all Facebook users (name, and hobby) whose
 * Nationality is the same as your own Nationality (pick one: be it Chinese or
 * German or whatever).
 * 
 * I pick "China"
 * 
 * @author hadoop
 *
 */
public class Task1 extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {

		if (args.length < 2) {
			System.out.println("Please give input and output properly");
			return -1;
		}
		JobConf conf = new JobConf(Task1.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		conf.setMapperClass(Task1Mapper.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		JobClient.runJob(conf);

		return 0;
	}

	public static void main(String args[]) throws Exception {
		int exitCode = ToolRunner.run(new Task1(), args);
		System.exit(exitCode);
	}

}
