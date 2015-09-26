package Task6;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Task6Reducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, NullWritable> {
	public final static int Initial_Time_Unit = 864000;
    public final static int Current_Time_Unit=10000000;
	@Override
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, NullWritable> output,
			Reporter report) throws IOException {

//		int count = 0;
//		while (values.hasNext()) {
//			IntWritable record = values.next();
//			if (record.get() == 1) {
//				count++;
//			}
//		}
//		if (count == 0) {
//			output.collect(key, NullWritable.get());
//		}
		int lastAccess=0;
		while(values.hasNext()){
			IntWritable record = values.next();
			if(record.get()>lastAccess){
				lastAccess=record.get();
			}
		}
		boolean neverAccess=Current_Time_Unit-lastAccess>Initial_Time_Unit;
		if(neverAccess){
			output.collect(key, NullWritable.get());
		}
		
	}

}
