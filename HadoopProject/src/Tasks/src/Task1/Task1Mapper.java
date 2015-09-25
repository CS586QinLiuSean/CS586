package Task1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Task1Mapper extends MapReduceBase 
implements Mapper<LongWritable,Text,Text,Text> {
private final static String nationality="China";
private Text nationText=new Text();
private Text record=new Text();
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		String line= value.toString();
		String[] user=line.split(",");
		String nation=user[2];
		if(nation.equals(String.valueOf(this.nationality))){
			nationText.set(nationality);
			record.set(user[1]+","+user[4]);
			output.collect(nationText, record);
		}
	}

}
