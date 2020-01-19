package com.sagnik.javaexercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;



public class StringAccumulator {

	public static void main(String[] args) {
		try {
			if(args.length>0) {
				System.out.println("Input String:"+args[0]);
				System.out.println("Sum of inputted numbers:"+add(args[0]));
			}else {
				System.out.println("No arguments passed, so sum is 0");
			}
		}catch(Exception ex) {
			System.err.println("Exception while adding numbers:"+ex.getMessage());
		}
		System.out.println("**************************************************");
	}

	private static Integer add(String str) throws Exception{
		if(StringUtils.isEmpty(str)) {
			return 0;//return 0 sum in case of null or empty string
		}
		String delim=",";
		str=str.replaceAll("\\\\n", "\n");//replace by actual new line when entered from command line
		if(StringUtils.contains(str, "\n")) {
			String firstLine=str.split("\n")[0];
			if(StringUtils.isNumeric(firstLine)) {//check if first line is delimeter or actual number
				str=str.replaceAll("\\n", ",");//replace all new line delimeters with comma			
			}else {//first line is the optional delimeter or list of delimeters
				str=str.substring(str.indexOf("\n")+1);//separate out 1st line 
																	   //and get actual number list	
				StringBuilder delimSb = formatMultiDelimeters(firstLine);
				//place the entire delimeter list in the delim variable separated by |to be passed on
				//to summation method
				delim = delimSb.substring(0,delimSb.length()-1);				
			}
		}
		return sumOfIntegersFromString(str, delim);
	}

	private static StringBuilder formatMultiDelimeters(String firstLine) {
		StringBuilder delimSb = new StringBuilder();
		List<String> delimeterList=Arrays.asList(firstLine.split("\\|"));
		for(String delims:delimeterList) {
			StringBuilder sb = new StringBuilder();
			delims.chars().distinct().forEach(c -> sb.append((char) c));//remove duplicates from delimeters
			delimSb.append(sb);
			delimSb.append("|");
		}
		return delimSb;
	}

	private static Integer sumOfIntegersFromString(String str, String delim) throws Exception {
		Integer sum=0;
		List<Integer> convertedNoList = Stream.of(str.split(delim)).filter(StringUtils::isNotBlank)
				  .map(String::trim)
				  .map(Integer::parseInt)
				  .collect(Collectors.toList());
		//throw exception in case of negative integers
		List<Integer> negativeList = convertedNoList.stream().filter(i->i<0).collect(Collectors.toList());
		if(!negativeList.isEmpty()) {
			String negativeNos=String.join(", ",negativeList.stream().map(i->i.toString()).
					collect(Collectors.toList()));
			throw new Exception("Negative numbers not allowed:"+negativeNos);
                    
		}
		//do actual summation
		sum=convertedNoList.stream().filter(i->i<=1000) // filter out all numbers only less than 1000
				.collect(Collectors.summingInt(Integer::intValue));
		return sum;
	}

}
