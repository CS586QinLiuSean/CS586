package Task4;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Task4Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	private static int oneUnit = 1;

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter report)
			throws IOException {
		String[] line = value.toString().split(",");
		
		try {
			//Friends table
			int personID=Integer.parseInt(line[1]);						
			output.collect(new Text(line[2]), new Text(String.valueOf(1)));		
			
		} catch (NumberFormatException e) {
			//Mypage table		
			output.collect(new Text(line[0]), new Text(line[1]));	
		}
		
	}
}
