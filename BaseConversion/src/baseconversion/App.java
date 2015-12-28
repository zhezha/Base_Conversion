package baseconversion;


public class App {

	public static void main(String[] args) {
		
		// Caution! The character in stringFrom is case sensitive.
		// 'A' = 10, and 'a' = 36.
		String stringFrom = "4624D";
		int baseFrom = 16;
		int baseTo = 10;
		String result = null;
		try {
			result = BaseConversion.convertBase(baseFrom, baseTo, stringFrom);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
	}

}
