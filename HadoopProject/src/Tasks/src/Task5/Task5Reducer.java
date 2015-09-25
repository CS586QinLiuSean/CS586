package Task5;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Task5Reducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter report)
			throws IOException {
		int count = 0;		
		Set<String> distinctHashSet=new HashSet<String>();
		
		while (values.hasNext()) {
			String[] record = values.next().toString().split(",");
			count++;
		    distinctHashSet.add(record[0]);		
		}
		output.collect(key, new Text(String.valueOf(count) + "," + String.valueOf(distinctHashSet.size())));
	}

}
