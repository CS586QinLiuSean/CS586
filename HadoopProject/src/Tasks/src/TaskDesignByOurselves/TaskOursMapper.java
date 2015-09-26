package TaskDesignByOurselves;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class TaskOursMapper  extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	private static int oneUnit = 1;

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter report)
			throws IOException {
		
		String[] line = value.toString().split(",");
		try {		
			int myID=Integer.parseInt(line[1]);			
			//Friend
			output.collect(new Text(line[1]), new Text(line[4]+",friend"));	
			
		} catch (NumberFormatException e) {
			//Mypage class			
			output.collect(new Text(line[0]), new Text(line[1]+",mypage"));		
		} 
		
	}
}
