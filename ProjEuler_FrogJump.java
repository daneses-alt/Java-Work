package projeulr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class frogjump {
	
	private static String factsys(int number) {
		int radix = 1;
		String result="";
		while (number > 0) {
			   int div = number / radix;
			   int remainder = number % radix;
			   result = remainder + result;
			   number = div;
			   ++radix;
			}
		if(result=="")
			return "000000000";
		int r=Integer.parseInt(result);
		for(int i=0;i<8-Math.log10(r);i++)
			result="0"+result;
		return result;
	}

	
	
	public static void main(String[] args) {
		ArrayList<Long> check=new ArrayList<>(Arrays.asList(0L, 1L, 1L, 1L, 2L, 6L, 14L, 28L));
		Long count=0L;
		ArrayList<Long> fn =new ArrayList<>(Arrays.asList(0L, 1L, 1L, 1L, 2L, 6L, 14L, 28L));
		ArrayList<Long> gn =new ArrayList<>(Arrays.asList(0L, 0L, 0L, 1L, 2L, 4L, 8L, 17L));
		
		for(long i=8;i<100000000000L;i++) {
			
			fn.add((gn.get(2)+gn.get(7)                
		           +fn.get(2)+2*fn.get(3)+fn.get(4)+fn.get(5)+fn.get(7)-fn.get(0))%78125L);
			
			gn.add((gn.get(4)+gn.get(5)+gn.get(6)     
			       +fn.get(3)+fn.get(4)+fn.get(5)+fn.get(6))%78125L);
			
			fn.remove(0);
			gn.remove(0);
			if(check.equals(fn)) {
				System.out.println("YOO   "+i);
				break;
			}
			System.out.println(i);
		}
		for(Long d:fn) {
			count=(count+(((d*d)%78125L)*d)%78125L)%78125L;
		}
		System.out.println(fn);
		System.out.println(count);
	}
}
/*
ArrayList<Long> check =new ArrayList<>(Arrays.asList(0L, 1L, 1L, 1L, 2L, 6L, 14L, 28L));
Long count=0L;
ArrayList<Long> fn =new ArrayList<>(Arrays.asList(0L, 1L, 1L, 1L, 2L, 6L, 14L, 28L));
ArrayList<Long> gn =new ArrayList<>(Arrays.asList(0L, 0L, 0L, 1L, 2L, 4L, 8L, 17L));

for(int i=8;i<1000000001;i++) {
	fn.add((gn.get(2)+gn.get(7)                
           +fn.get(2)+2*fn.get(3)+fn.get(4)+fn.get(5)+fn.get(7)-fn.get(0))%1000000000L);
	
	gn.add((gn.get(4)+gn.get(5)+gn.get(6)     
	       +fn.get(3)+fn.get(4)+fn.get(5)+fn.get(6))%1000000000L);
	Long d=fn.get(0);
	count=(count+((((d*d)%1000000000L)*d)%1000000000L))%1000000000L;
	fn.remove(0);
	gn.remove(0);
	System.out.println(i+"     "+d);
	if(fn.equals(check)) {
		System.out.println("Hooray");
		break;
	}
}
for(Long d:fn) {
	count=(count+((((d*d)%1000000000L)*d)%1000000000L))%1000000000L;
}
System.out.println(fn);
System.out.println(count);
}
*/