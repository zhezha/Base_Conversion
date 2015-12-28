package baseconversion;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class BaseConversionTest {

	@Test
	public void test1() {
		int value = 15;
		int[] result = BaseConversion.intToArray(value, 2, 5, 0);
		int[] expected = new int[] {1, 1, 1, 1, 0};
		for (int i = 0; i < result.length; i++)
			Assert.assertEquals(expected[i], result[i]);
	}
	
	@Test
	public void test2() {
		int[] array1 = new int[] {1, 1, 1, 1, 0};
		int[] array2 = new int[] {1, 0, 0, 0, 0};
		int[] result = BaseConversion.addArrays(array1, array2, 2);
		int[] expected = new int[] {0, 0, 0, 0, 1};
		for (int i = 0; i < result.length; i++)
			Assert.assertEquals(expected[i], result[i]);
	}
	
	@Test
	public void test3() {
		int[] array = new int[] {6, 18, 0};
		int[] result = BaseConversion.arrayMulInt(array, 16, 62);
		int[] expected = new int[] {34, 41, 4};
		for (int i = 0; i < result.length; i++)
			Assert.assertEquals(expected[i], result[i]);
	}

}
