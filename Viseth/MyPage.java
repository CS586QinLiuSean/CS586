
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class MyPage {
	int ID = 1;
	int totalRecords = 0;

	public MyPage(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public void generate(){
		Random numberGenerater = new Random();

		try {
			File file = new File("MyPage.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			while(ID<=totalRecords){
				int length = numberGenerater.nextInt(10)+10;
				char[] Name = new char[length];
				int temp = 0;
				while(temp<length){
					Name[temp++] = (char) (numberGenerater.nextInt(26)+'a');
				}
				String newName = String.copyValueOf(Name);
				//System.out.println(String.copyValueOf(Name));

				char[] Nationality = new char[length];
				temp = 0;
				while(temp<length){
					Nationality[temp++] = (char) (numberGenerater.nextInt(26)+'a');
				}
				String newNationality = String.copyValueOf(Nationality);
				//System.out.println(String.copyValueOf(Nationality));

				int CountryCode = numberGenerater.nextInt(10)+1;

				char[] Hobby = new char[length];
				temp = 0;
				while(temp<length){
					Hobby[temp++] = (char) (numberGenerater.nextInt(26)+'a');
				}
				String newHobby = String.copyValueOf(Hobby);
				//System.out.println(String.copyValueOf(Hobby));

				output.write(ID+","+newName+","+newNationality+","+CountryCode+","+newHobby+"\n");
				ID++;
			}
			output.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
