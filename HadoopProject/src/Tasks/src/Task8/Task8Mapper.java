package Task8;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class Task8Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

private static final IntWritable oneUnit = new IntWritable(1);
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		String[] line = value.toString().split(",");
		context.write(new Text(line[4]), oneUnit);
	}
}
