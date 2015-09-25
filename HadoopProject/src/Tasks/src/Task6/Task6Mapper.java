package Task6;

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Task6Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	private static int oneUnit = 1;

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter report)
			throws IOException {
		
		String[] line = value.toString().split(",");
		String pair=line[1]+","+line[2];
		IntWritable access=new IntWritable();
		try {
			int accessTime=Integer.parseInt(line[4]);			
			access.set(oneUnit);
			output.collect(new Text(pair), access);		
			
		} catch (NumberFormatException e) {
			access.set(0);
			output.collect(new Text(pair), access);		
		}catch(ArrayIndexOutOfBoundsException ae){
			return;
		}
		
	}
}
