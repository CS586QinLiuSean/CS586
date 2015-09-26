package TaskDesignByOurselves;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class TaskOursReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	private  HashMap<String,Integer> map=new  HashMap<String,Integer>();
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output,
			Reporter report) throws IOException {
	map.put("NoFriendYet", 0);
    String name=new String();
   
	while(values.hasNext()){
		String value=values.next().toString();
		String[] split=value.split(",");
		if(split[1].equals("mypage")){
			name=split[0];
		}
		else{
			String desc=split[0];
			Integer temp =map.get(split[0]);
			int count;
			if(temp==null||temp.equals(0)){
				count=0;
			}
			else{
				count=temp;
			}
			
			map.put(desc, new Integer(count+1));
		}
		
	}
		//itereate the values, find the one with highest number
	int maxCount=0;
	Iterator it=map.keySet().iterator();
	String descriptionOfMax = "NoFriendYet";
	
	while(it.hasNext()){
		String thisDesc=(String) it.next();
		if(map.get(thisDesc)>maxCount){
			descriptionOfMax=thisDesc;
			maxCount=map.get(thisDesc);
		}
	}
	output.collect(key,new Text(name+","+descriptionOfMax+","+String.valueOf(maxCount)));
	map.clear();
	}

}
