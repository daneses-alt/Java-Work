import java.util.Arrays;
import java.util.Scanner;
 
public class greed {
	
	static long mod=998244353L;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner inp=new Scanner(System.in); 
        int numm=inp.nextInt();
        for(int ko=0;ko<numm;ko++){
        	
        	int n=inp.nextInt(); int k=inp.nextInt();
        	long[][] arr=new long[2][n];
        	for(int i=0;i<n;i++) {
        		arr[0][i]=inp.nextInt();
        	}
        	for(int i=0;i<n;i++) {
        		arr[1][i]=inp.nextInt();
        	}
 
        	long[] dp=new long[k+1]; // 0 is 0, k is k and up diff
        	long[] dp2=new long[k+1]; //
        	dp[0]=1;
        	for(int i=n-1;i>0;i--) {
        		long[] dpS=new long[k+2];
        		for(int j=0;j<dp.length;j++)
        			dpS[j+1]=(dpS[j]+dp[j])%mod;
        		
        		if(arr[0][i]==-1 && arr[1][i-1]==-1) {
        			long c=0;
        			for(int ind=0;ind<=k-1;ind++) {
        				c+=((k)*(dp[ind]))%mod;
        				c=((c-(dpS[ind]))%mod + mod)%mod;
        				dp2[ind]+=c;
        				dp2[ind]%=mod;
        				
        				dp2[k]+=(((long)(k-ind))*(mod+dpS[k+1]-dpS[k-ind]))%mod;
        				dp2[k]%=mod;
        			}
        			
        			for(int D=-1;D>=1-k;D--) {
        				dp2[0]+=((k+D)*dpS[-D+1])%mod;
        				dp2[0]%=mod;
        			}
        		}
        		else if (arr[0][i]==-1) {
        			for(int ind=0;ind<=k-1;ind++) {
        				dp2[ind]=dpS[ind+1]-dpS[(int) Math.max(0, ind-k+arr[1][i-1] )];
        				dp2[ind]=(dp2[ind]+mod)%mod;
        				if(ind<=k-arr[1][i-1]) {
        					dp2[k]+=(long)(mod+dpS[k+1]-dpS[k-ind]);
        				}
        				dp2[k]%=mod;
        				
        			}
        			
        			
        			for(int D=-1;D>=1-arr[1][i-1];D--) {
        				dp2[0]+=dpS[-D+1];
        				dp2[0]%=mod;
        			}
        		}
        		else if (arr[1][i-1]==-1) {
        			for(int ind=0;ind<=k-1;ind++) {
        				dp2[ind]+=mod+dpS[ind+1]-dpS[(int) Math.max(0, ind-arr[0][i]+1 )];
        				dp2[ind]%=mod;
        				if(ind<arr[0][i]) {
        					dp2[k]+=(long)(mod+dpS[k+1]-dpS[k-ind]);
        				}
        				dp2[k]%=mod;
        			}
        			
        			for(int D=-1;D>=arr[0][i]-k;D--) {
        				dp2[0]+=dpS[-D+1];
        				dp2[0]%=mod;
        			}
        		}
        		else {
        			long a=arr[0][i];
        			long b=arr[1][i-1];
        			if(a<b) {
        				for(int ind=0;ind<=b-a;ind++) {
        					dp2[0]+=dp[ind];
        					dp2[0]%=mod;
        				}
        			}
        			else if(a>b) {
        				for(int ind=(int) (a-b);ind<k;ind++) {
        					dp2[ind]+=dp[(int) (ind-(a-b))];
        					//if(n==4 && k==3) {System.out.println(dp2[ind]+" la  "+ind);}
        					dp2[ind]%=mod;
        				}
        				dp2[k]+=mod+dpS[k+1]-dpS[(int) (k-(a-b))];
        				dp2[k]%=mod;
        			}
        			else {
        				for(int x=0;x<=k;x++) dp2[x]=dp[x];
        			}
        		}
        		//for(long x:dp2) { System.out.print(x+", ");}
        		//System.out.println("");
        		dp=dp2;
        		dp2=new long[k+1];
        	}
        	long res=0;
        	for(long x:dp) {res+=x; res%=mod;}
        	if(arr[0][0]==-1) {
        		res=(res*k)%mod;
        	}
        	if(arr[1][n-1]==-1) {
        		res=(res*k)%mod;
        	}
        	
        	System.out.println(res);
        	//System.out.println(" \n \n\n");
        	
        }
	
 
	}
}