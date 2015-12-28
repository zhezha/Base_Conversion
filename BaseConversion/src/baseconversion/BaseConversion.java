package baseconversion;
import java.util.HashMap;


public class BaseConversion {
	
	// Digit to charactor: 
	// '0':0, '1':1, ..., '9':9, 
	// 'A':10, 'B':11, ..., 'Z':35,
	// 'a':36, 'b':37, ..., 'z':61.
	// maximum base is 62 (0 - 9, A - Z, a - z).
	private static char[] digitToChar = new char[62];
	private static HashMap<Character, Integer> charToDigit = new HashMap<Character, Integer>();
	
	static {
		for (int i = 0; i < 10; i++) {
			digitToChar[i] = (char) (48 + i);
			charToDigit.put((char) (48 + i), i);
		}
		for (int i = 0; i < 26; i++) {
			digitToChar[10 + i] = (char) (65 + i);
			charToDigit.put((char) (65 + i), 10 + i);
			digitToChar[36 + i] = (char) (97 + i);
			charToDigit.put((char) (97 + i), 36 + i);
		}
	}
	

	// Convert fromString in baseFrom to baseTo.
	// Caution! The character in stringFrom is case sensitive.
	// 'A' = 10, and 'a' = 36.
	public static String convertBase(int baseFrom, int baseTo, String stringFrom) throws Exception {
		
		if (baseFrom < 2 || baseFrom > 62 || baseTo < 2 || baseTo > 62) {
			throw new Exception("Base out of border.");
		}
		
		int lengthFrom = stringFrom.length();
		// Calculate the length of string in baseTo
		int lengthTo = (int) Math.ceil(lengthFrom * Math.log((double) baseFrom) / Math.log((double) baseTo));
		// from high digit to low digit
		// for example: 17956 = 0x4624 = (4, 6, 2, 4) in arrayFrom
		int[] arrayFrom = new int[lengthFrom];
		// from low digit to digit order
		// for example: 17956 = (4, 41, 38)base62 = (38, 41, 4) in arrayTo
		int[] arrayTo = new int[lengthTo];
		
		// from high order to low order
		for (int i = 0; i < lengthFrom; i++) {
			arrayFrom[i] = charToDigit.get(stringFrom.charAt(i));
		}
		
		// Result is initialized to 0.
		for (int i = 0; i < lengthFrom - 1; i++) {
			int tmp = arrayFrom[i];
			int[] tmpArray = intToArray(tmp, baseTo, lengthTo, 0);
			// result = result + arrayFrom[i]
			arrayTo = addArrays(arrayTo, tmpArray, baseTo);
			// result = result * baseFrom
			arrayTo = arrayMulInt(arrayTo, baseFrom, baseTo);
		}
		int tmp = arrayFrom[lengthFrom - 1];
		int[] tmpArray = intToArray(tmp, baseTo, lengthTo, 0);
		// result = result + arrayFrom[0]
		arrayTo = addArrays(arrayTo, tmpArray, baseTo);
		
		int start = 0;
		for (int i = lengthTo - 1; i >= 0; i--) {
			if (arrayTo[i] > 0) {
				start = i;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = start; i >= 0; i--) {
			char ch = digitToChar[arrayTo[i]];
			sb.append(ch);
		}
		
		return sb.toString();
	}
	
	
	public static int[] arrayMulInt(int[] array, int baseFrom, int baseTo) {
		
		int length = array.length;
		int[] result = new int[length];
		for (int i = length - 1; i >= 0; i--) {
			if (array[i] > 0) {
				int tmp = array[i] * baseFrom;
				int[] tmpArray = intToArray(tmp, baseTo, length, i);
				result = addArrays(result, tmpArray, baseTo);
			}
		}
		
		return result;
	}
	
	
	public static int[] addArrays(int[] array1, int[] array2, int base) {
		
		int[] result = new int[array1.length];
		
		int carry = 0;
		for (int i = 0; i < array1.length; i++) {
			result[i] = array1[i] + array2[i] + carry;
			if (result[i] >= base) {
				result[i] = result[i] % base;
				carry = 1;
			}
			else {
				carry = 0;
			}
		}
		
		return result;
	}
	
	
	// Convert an integer to an array in baseTo starting from the position with value of offset.
	// For example, if the converted array is (1, 2, 3), lengthTo = 5, and offset = 1,
	// then the final converted array is (0, 1, 2, 3, 0).
	public static int[] intToArray(int value, int baseTo, int lengthTo, int offset) {
		
		int[] array = new int[lengthTo];
		int pointer = offset;
		while (value > 0) {
			int remainder = value % baseTo;
			int quotient = value / baseTo;
			array[pointer] = remainder;			
			pointer++;
			value = quotient;
		}
		
		return array;
	}
}
