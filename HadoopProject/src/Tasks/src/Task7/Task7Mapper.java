package Task7;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Task7Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	private static int oneUnit = 1;

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter report)
			throws IOException {
		String[] line = value.toString().split(",");
		
		try {
			int accessTime=Integer.parseInt(line[4]);			
			output.collect(new Text(line[1]), new Text(line[4]));		
			
		} catch (NumberFormatException e) {
			output.collect(new Text(line[0]), new Text(line[1]+","+line[2]+","+line[3]+","+line[4]));
		}
		
	}
}
