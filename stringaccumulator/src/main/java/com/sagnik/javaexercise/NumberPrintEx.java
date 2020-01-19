package com.sagnik.javaexercise;
 import org.apache.commons.io.IOUtils;
 import org.json.JSONArray;
 import org.json.JSONObject;

 import java.io.IOException;
 import java.nio.charset.StandardCharsets;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.PriorityQueue;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.stream.Collectors;

public class NumberPrintEx {


	public static void main(String[] args) {
		try {
			System.out.println("The numbers printed are:"+printNumbers(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
			System.out.println("**************************************************");

			PriorityQueue<String> pq  = new PriorityQueue<>();
			pq.add("dishes");
			pq.add("laundry");
			pq.add("bills");
			pq.offer("bills");
			System.out.print(pq.size() + " " +pq.poll());
			System.out.print(" "+pq.peek() + " " +pq.poll());
			System.out.println(" "+pq.poll() + " " +pq.poll());
			System.out.println("*******************************************************");

			getDataFromRestAPI();
			System.out.println("**************************************************");
			System.out.println("Permutations for 1 letter words made up of vowels:"+countVowelPermutation(1));
			System.out.println("Permutations for 2 letter words made up of vowels:"+countVowelPermutation(2));
			System.out.println("Permutations for 3 letter words made up of vowels:"+countVowelPermutation(3));
			System.out.println("Permutations for 4 letter words made up of vowels:"+countVowelPermutation(4));
		}catch(Exception ex) {
			System.err.println("Exception while printing numbers:"+ex.getMessage());
		}

	}

	private static void getDataFromRestAPI() throws IOException {
		URL url = new URL("https://jsonmock.hackerrank.com/api/article_users/search/page=1");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		System.out.println("Output from Server .... \n");
		/*BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;

		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}*/
		String outJson = IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8.name());
		System.out.println(outJson);
		JSONObject jo = new JSONObject(outJson);
		JSONArray ja=jo.getJSONArray("data");
		List<JSONObject> list = new ArrayList();
		for (int i = 0; i < ja.length();list.add(ja.getJSONObject(i++)));
		List<JSONObject> listFiltered=
				list.stream().filter(i->Integer.parseInt(String.valueOf(i.get("submission_count")))>100).collect(Collectors.toList());
		listFiltered.forEach(i->System.out.println("Name:"+i.getString("username")+" "+"SubmissionCount:"+i.get("submission_count")));
		conn.disconnect();
	}

	static String printNumbers(int init, int endNo) throws Exception {
		StringBuilder stb = new StringBuilder();
		if(init<endNo){
			for(int i=init;i<=endNo;i++){
				stb.append(checkMultiples(i)).append(",");
			}
			return stb.toString().substring(0,(stb.toString().length()-1));
		}else {
			throw new Exception("Start number is not less than end no");
		}
	}

	public static String  checkMultiples(int no){
		if(findMultiplesOfThreeAndFive(no))
			return "FizzBuzz";
		else if(findMultiplesOfThreeOrFive(no,3))
			return "Fizz";
		else if(findMultiplesOfThreeOrFive(no,5))
			return "Buzz";
		else
			return Integer.toString(no);
	}

	public static boolean findMultiplesOfThreeOrFive(int no, int divisor){

			return no%divisor==0;

	}

	public static boolean findMultiplesOfThreeAndFive(int no){
		return (no%3==0 && no%5==0);
	}

	public static int countVowelPermutation(int n)
	{
		if (n == 1) return 5;

		long mod = 1000000007L;

		//aeiou corresponds to 01234
		long[] previous = new long[5];
		long[] current = new long[5];

		long sumPrevious = 4;
		for (int j = 0; j < 5; j++) previous[j] = 1;

		for (int i = 2; i <= n; i++)
		{
			//a
			current[0] = previous[1];
			//e
			current[1] = (previous[0] + previous[2]) % mod;
			//i
			current[2] = sumPrevious;
			//o
			current[3] = (previous[2] + previous[4]) % mod;
			//u
			current[4] = previous[0];

			sumPrevious = 0;
			for (int j = 0; j < 5; j++)
			{
				previous[j] = current[j];
				if (j != 2) sumPrevious += previous[j];
			}
			sumPrevious %= mod;
		}

		long result = (current[0] + current[1] + current[2] + current[3] + current[4]) % mod;

		return (int)result;
	}

}
