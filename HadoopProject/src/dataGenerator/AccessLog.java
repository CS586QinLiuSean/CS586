package dataGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * AccessId: unique sequential number (integer) from 1 to 10,000,000 ByWho:
 * References the Id of the person who has accessed the Facebook page
 * 
 *  WhatPage:
 * References the Id of the page that was accessed 
 * 
 * TypeOfAccess: random text of
 * characters of length between 20 and 50 explaining if just viewed, left a
 * note, added a friendship, etc. 
 * 
 * AccessTime: random number between 1 and
 * 1,000,000 (or other data type of your choosing)
 * 
 * @author zishanqin
 *
 */
public class AccessLog {
	static String[] typeOfAccess = { "just viewed", "left a note", "added a friendship" };

	public static void dataGenerate() {
		
			try {
				String AccessId;
				String ByWho;
				String WhatPage;
				String TypeOfAccess;
				String AccessTime;
				String record;

				FileWriter FW = new FileWriter("AccessLog.txt");
					
					Random PersonIDRandom = new Random();
					Random AccessTimeRandom = new Random();
					Random TypeOfAccessRandom = new Random();
					for (int i = 1; i <= 10000000; i++) {
						AccessId = String.valueOf(i);	
						ByWho = String.valueOf(PersonIDRandom.nextInt(50000));
						WhatPage = String.valueOf(PersonIDRandom.nextInt(50000));
						TypeOfAccess = typeOfAccess[TypeOfAccessRandom.nextInt(3)];
						AccessTime = String.valueOf(AccessTimeRandom.nextInt(1000000));
						record = AccessId + "," + ByWho + "," + WhatPage + "," + TypeOfAccess + "," + AccessTime + "\r\n";
						FW.write(record);
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
		
	}

	public static void main(String[] args) {
		dataGenerate();
	}
}
