package Task3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public  class Task3Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	/*private TreeMap<Integer,String> map;
	
	public void setup(Context context) throws IOException, InterruptedException{
		map = new TreeMap<Integer,String>();
	}
	
	public void reduce(Text key, Iterable<IntWritable> value, Context context) {
		int count = 0;
		for (IntWritable num: value) {
			count += num.get();
		}
		map.put(count,key.toString());
		if(map.size()>10){
			map.remove(map.firstKey());
		}
	}
	
	public void cleanup(Context context) throws IOException, InterruptedException {
		
		Iterator itrator=map.keySet().iterator();
		while(itrator.hasNext()){
			Integer number=(Integer) itrator.next();
			String value=map.get(number);
			context.write(new Text(value),new IntWritable(number));
		}
		
	}
}*/
	private HashMap<String,Integer> map;

public void setup(Context context) throws IOException, InterruptedException{
	map = new HashMap<String,Integer>();
}

public void reduce(Text key, Iterable<IntWritable> value, Context context) {
	int count = 0;
	for (IntWritable num: value) {
		count += num.get();
	}
	map.put(key.toString(),count);
}

public void cleanup(Context context) throws IOException, InterruptedException {
	ArrayList<Map.Entry<String, Integer>> list = sortValue(map);
	int i = 1;
	for (Map.Entry<String, Integer> temp: list) {
		context.write(new Text(temp.getKey()), new IntWritable(temp.getValue()));
		if(i>=10){
			return;
		}
		i++;
	}
}

public ArrayList<Map.Entry<String, Integer>> sortValue(HashMap<String, Integer> map) {
	ArrayList<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
	Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){

         public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }});
	return list;
}
}