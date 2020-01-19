package com.sagnik.javaexercise;

import org.junit.Assert;
import org.junit.Test;

public class NumberPrintExTest {
	
	@Test
	public void printOneToTwenty() {

		try {
			System.out.println("Printed nos:"+NumberPrintEx.printNumbers(1,20));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test(expected = Exception.class)
	public void printNumberException() throws Exception {

			NumberPrintEx.printNumbers(20,10);

	}

	@Test
	public void testMultipleThreeOrFive() {
		System.out.println("6 is a multiple of 3:"+NumberPrintEx.findMultiplesOfThreeOrFive(6,3));
		System.out.println("7 is a multiple of 3:"+NumberPrintEx.findMultiplesOfThreeOrFive(7,3));
		System.out.println("10 is a multiple of 5:"+NumberPrintEx.findMultiplesOfThreeOrFive(10,5));
		System.out.println("9 is a multiple of 5:"+NumberPrintEx.findMultiplesOfThreeOrFive(9,5));
	}

	@Test
	public void testMultipleThreeAndFive() {
		System.out.println("15 is a multiple of both 3 and 5:"+NumberPrintEx.findMultiplesOfThreeOrFive(10,5));
		System.out.println("19 is a multiple of both 3 and 5:"+NumberPrintEx.findMultiplesOfThreeOrFive(9,5));
	}

	@Test
	public void testCheckMultiples() {
		System.out.println("No to be printed:"+NumberPrintEx.checkMultiples(10));
		Assert.assertEquals("Buzz",NumberPrintEx.checkMultiples(10));
		System.out.println("No to be printed:"+NumberPrintEx.checkMultiples(9));
		Assert.assertEquals("Fizz",NumberPrintEx.checkMultiples(9));
		System.out.println("No to be printed:"+NumberPrintEx.checkMultiples(15));
		Assert.assertEquals("FizzBuzz",NumberPrintEx.checkMultiples(15));
		System.out.println("No to be printed:"+NumberPrintEx.checkMultiples(17));
		Assert.assertEquals("17",NumberPrintEx.checkMultiples(17));
	}

}
