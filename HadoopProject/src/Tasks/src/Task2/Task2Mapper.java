package Task2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Task2Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	private static int oneUnit = 1;

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter report)
			throws IOException {
		String line = value.toString();
		String[] user = line.split(",");
		String country = user[2];
		output.collect(new Text(country), new IntWritable(oneUnit));
	}
}
