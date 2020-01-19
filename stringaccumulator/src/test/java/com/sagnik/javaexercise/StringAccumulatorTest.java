package com.sagnik.javaexercise;

import org.junit.Test;

public class StringAccumulatorTest {
	
	@Test
	public void testAddIntsCommaSeparated() {
		String emptyargs[] = {""};
		StringAccumulator.main(emptyargs);
		
		String singlearg[] = {"4"};
		StringAccumulator.main(singlearg);
		
		String args[] = {"2,5,7,12"};
		StringAccumulator.main(args);
		
		String args1[] = {"2,,5,,7,,3,1001"};
		StringAccumulator.main(args1);		
		
	}

	@Test
	public void testAddIntsNewLineSeparated() {
		String args[] = {"2\n5,7\n12"};
		StringAccumulator.main(args);
	}
	
	@Test
	public void testAddIntsMultiDelims() {
		String args[] = {"\\*|%\n1*2%3"};
		StringAccumulator.main(args);
		
		String args1[] = {"\\%|\\*\n1****2%%%%3"};
		StringAccumulator.main(args1);
		
		String args2[] = {"\\%%%%|\\********\n1****2%%%%3"};
		StringAccumulator.main(args2);
	}
	
	@Test
	public void testAddIntsMultiDelims2() {
		String args[] = {";\n1;2;23"};
		StringAccumulator.main(args);
		
		String args1[] = {":::::::\n::1:::4:::23"};
		StringAccumulator.main(args1);	
		
	}
	
	@Test
	public void testAddIntsNegative() {
		String negativeArgs[] = {"2,5,-7,3,-15"};
		StringAccumulator.main(negativeArgs);
	}
}
