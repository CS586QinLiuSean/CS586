package Task7;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Task7Reducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	public final static int Initial_Time = 864000;

	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter report)
			throws IOException {

		int count = 0;
		String profile = new String();
		while (values.hasNext()) {
			String record = values.next().toString();
			try {
				int accessTime = Integer.parseInt(record);
				if (accessTime > Initial_Time) {
					count++;
				}
			} catch (NumberFormatException e) {

				profile = record;
			}
		}

		if (count == 0) {
			output.collect(key, new Text(profile));
		}
	}

}
