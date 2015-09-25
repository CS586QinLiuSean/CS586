package Task6;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MultiFileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Task6 extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Please give input and output properly");
			return -1;
		}

		JobConf conf = new JobConf(Task6.class);
		MultiFileInputFormat.addInputPath(conf, new Path(args[0]));
		MultiFileInputFormat.addInputPath(conf, new Path(args[1]));
		FileOutputFormat.setOutputPath(conf, new Path(args[2]));
		conf.setMapperClass(Task6Mapper.class);
		conf.setReducerClass(Task6Reducer.class);

		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass((IntWritable.class));
		

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(NullWritable.class);

		JobClient.runJob(conf);

		return 0;

	}

	public static void main(String args[]) throws Exception {
		int exitCode = ToolRunner.run(new Task6(), args);
		System.exit(exitCode);
	}
}
