package dataGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * dataset for MyPage
 * @author zishanqin
 *
 */
public class MyPage {
	/*
	 * 
	ID: unique sequential number (integer) from 1 to 50,000 indicating
	the owner of the page
	Name: random sequence of characters of length between 10 and 20
	Nationality: random sequence of characters of length between 10 and 20
	CountryCode: random number (integer) between 1 and 10
	Hobby: random sequence of characters of length between 10 and 20 
	 */
	static String[] NationArray={"China","Germany","America","India","Korea","Japan"
			,"Australia","Brazil","Canada","Egypt"};
	static String[] Hobby={"Singing","Hocky","Coding","Cooking","Dancing","Hiking","Music"};
	public static ArrayList<String> FirstNames=new ArrayList<String>();
	public static ArrayList<String> LastNames=new ArrayList<String>();
	public static void loadData(){
		String splitBy = ",";
        try {
			BufferedReader br = new BufferedReader(new FileReader("firstnames.csv"));
			
			String line = br.readLine();
			while(line!=null){
			     String[] b = line.split(splitBy);
			     FirstNames.add(b[0]);
			     line = br.readLine();
			     
			}
			
			br = new BufferedReader(new FileReader("lastnames.csv"));
			 
			 line = br.readLine();
			while(line!=null){
			     String[] b = line.split(splitBy);
			     LastNames.add(b[0]);	
			     line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void dataGenerate() {
        try {

        	
            String ID;
            String name;
            String nationality;
            int CountryCode;
            String record;
            String hobby;

            try (
            	FileWriter FW = new FileWriter("MyPage.txt")) {
            	Random IDrandom=new Random();
            	Random firstNamerandom=new Random();
            	Random lastRandom=new Random();
            	Random nationRandom=new Random();
            	Random countryCodeRandom=new Random();
            	Random hobbyRandom=new Random();
                for (int i = 1; i <= 50000; i++) {
                    //ID = String.valueOf(IDrandom.nextInt(50000));
                	ID=String.valueOf(i);
                    name=FirstNames.get(firstNamerandom.nextInt(7000))+" "+LastNames.get(lastRandom.nextInt(80000));
                    nationality=NationArray[nationRandom.nextInt(10)];
                    CountryCode = countryCodeRandom.nextInt(10) + 1;
                    hobby=Hobby[hobbyRandom.nextInt(6)];
                    record = ID + "," + name + "," + nationality + "," + CountryCode + "," + hobby + "\r\n";
                   FW.write(record);
                }
            }
        } catch (Exception e) {

        }
    }

	public static void main(String[] args){
		loadData();
		dataGenerate();
	}
}
