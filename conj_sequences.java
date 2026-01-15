package sortlab;

public class conj_sequences {
	public static int TOP=123456789;
	public static int mod=998244353;
	public static int twopow=(int)Math.pow(2,27);
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		int[] arr= new int[twopow];
		for(int i=1;i<=TOP;i++) {
			arr[i]=1;
		}
		int[] arr2= new int[twopow];
		for(int runs=0;runs<122;runs++) {
			System.out.println(runs);
			int sum=0;
			for(int x:arr)
				sum=(sum+x)%mod;
			
			for(int bit=0;bit<27;bit++) {                 //
				int b=(int)Math.pow(2, bit);
				for(int S=1;S<=twopow-1;S++) {
					if(0!=(S & b)) {
						arr[S]= (arr[S] + arr[S^b])%mod;
				}
			}
		}
		
			for(int i=1;i<twopow;i++) {
				if((i ^ (twopow-1)) <= TOP)
					arr2[i ^ (twopow-1) ]=(sum-arr[i]+mod)%mod;
				else
					arr2[i ^ (twopow-1)]=0;
					//System.out.println(i+" m "+(i^(twopow-1))+ "  "+twopow);
		}
		arr=arr2.clone();
		//System.out.println("ja "+runs);
		//for(int x:arr2) {
			//System.out.print(x+ " ");
		//}
		//System.out.println();
		}
		int s=0;
		for(int x:arr2) {
			s=(s+x)%mod;
		}
		System.out.println(s);
	}

}
