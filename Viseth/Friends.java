
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Friends {
	int FriendRel = 1;
	long totalRecords = 0;

	public Friends(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public void generate(){
		Random numberGenerater = new Random();

		try {
			File file = new File("Friends.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			while(FriendRel<=totalRecords){
				
				int PersonID = numberGenerater.nextInt(50000)+1;
				
				int MyFriend = 0;
				do{
					MyFriend = numberGenerater.nextInt(50000)+1;
				}while(PersonID == MyFriend);
				
				long DateofFriendship = numberGenerater.nextInt(1000000)+1;

				int length = numberGenerater.nextInt(30)+20;
				char[] Desc = new char[length];
				int temp = 0;
				while(temp<length){
					Desc[temp++] = (char) (numberGenerater.nextInt(26)+'a');
				}
				String newDesc = String.copyValueOf(Desc);
				//System.out.println(String.copyValueOf(Desc));

				output.write(FriendRel+","+PersonID+","+MyFriend+","+DateofFriendship+","+newDesc+"\n");
				FriendRel++;
			}
			output.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
