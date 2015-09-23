
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class AccessLog {
	int AccessId = 1;
	long totalRecords = 0;

	public AccessLog(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public void generate(){
		Random numberGenerater = new Random();

		try {
			File file = new File("AccessLog.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			while(AccessId<=totalRecords){
				
				int ByWho = numberGenerater.nextInt(50000)+1;
				
				int WhatPage = numberGenerater.nextInt(50000)+1;

				int length = numberGenerater.nextInt(30)+20;
				char[] TypeOfAccess = new char[length];
				int temp = 0;
				while(temp<length){
					TypeOfAccess[temp++] = (char) (numberGenerater.nextInt(26)+'a');
				}
				String newTypeOfAccess = String.copyValueOf(TypeOfAccess);
				
				long AccessTime = numberGenerater.nextInt(1000000)+1;

				output.write(AccessId+","+ByWho+","+WhatPage+","+newTypeOfAccess+","+AccessTime+"\n");
				AccessId++;
			}
			output.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
