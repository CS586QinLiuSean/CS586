package Task4;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Task4Reducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter report)
			throws IOException {
		int count = 0;
		String name = new String();
		while (values.hasNext()) {
			String[] record = values.next().toString().split(",");
			try {

				if (Integer.parseInt(record[0]) == 1) {
					count++;
				}
			} catch (NumberFormatException e) {
				
				name = record[0];
			}
		}

		output.collect(key, new Text(name + "," + String.valueOf(count)));
	}

}
