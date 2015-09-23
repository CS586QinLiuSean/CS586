package dataGenerator;

import java.io.FileWriter;
import java.util.Random;
import java.util.UUID;

/**
 * 
FriendRel: unique sequential number (integer) from 1 to 5,000,000

PersonID: ID of a person who has a Facebook page, i.e., from 1 to 50,000

MyFriend: References ID of person that you are friend with, i.e., from 1 to 
50,000

DateofFriendship: random number (integer) between 1 and 1,000,000 (or some
other sequential data type) to indicate when the friendship started

Desc: text of characters of length between 20 and 50 explaining what
kind of friendship. This is, for instance, college friends, unknown,
family, etc. 

 * @author zishanqin
 *
 */
public class Friends {
	static String Desc[]={"college","friends","unknown","family"};
	public static void dataGenerate() {
        try {

        	
            String FriendRel;
            String PersonID;
            String MyFriend;
            String DateofFriendship;
            String Description;
            int CountryCode;
            String record;
            String hobby;

            try (
            	FileWriter FW = new FileWriter("Friends.txt")) {
            	Random IDrandom=new Random();
            	
            	Random PersonIDRandom=new Random();
            	Random DateofFriendshipRandom=new Random();
            	Random DescriptionRandom=new Random();
                for (int i = 1; i <= 5000000; i++) {
                    //ID = String.valueOf(IDrandom.nextInt(50000));
                	FriendRel=String.valueOf(i);
                    //name=FirstNames.get(firstNamerandom.nextInt(7000))+" "+LastNames.get(lastRandom.nextInt(80000));
                    PersonID=String.valueOf(PersonIDRandom.nextInt(50000));
                    MyFriend=String.valueOf(PersonIDRandom.nextInt(50000));
                    DateofFriendship=String.valueOf(DateofFriendshipRandom.nextInt(1000000));
                    Description=Desc[DescriptionRandom.nextInt(4)];
                    
                    record = FriendRel + "," + PersonID + "," + MyFriend + "," + DateofFriendship + "," + Description + "\r\n";
                   FW.write(record);
                }
            }
        } catch (Exception e) {

        }
    }
	public static void main(String[] args){
		dataGenerate();
	}
}
