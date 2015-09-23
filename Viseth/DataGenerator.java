public class DataGenerator {

	public static void main(String[] args) {
		MyPage CDG = new MyPage(50000);
		CDG.generate();
		
		Friends FDG = new Friends(5000000);
		FDG.generate();
		
		AccessLog ADG = new AccessLog(10000000);
		ADG.generate();
	}

}
