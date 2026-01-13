import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
 
public class template3 {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
	    //int testCases = Integer.parseInt(stuff.get(0));
	   // HashMap<Integer, int[]> map=new HashMap<Integer, int[]>();
	    int testCases=in.nextInt();
	    //int indi=1;
	    for (int vv = 0; vv < testCases; vv++) {
	    	int n=in.nextInt();
	    	HashMap<Integer, Integer> m=new HashMap<Integer, Integer>();
	    	int x;
	    	for(int i=0;i<n;i++) {
	    		x=in.nextInt();
	    		m.put(x, 1);
	    	}
	    	ArrayList<Integer> ls=new ArrayList<Integer>();
	    	for(Entry<Integer, Integer> e:m.entrySet()) {
	    		ls.add(e.getKey());
	    	}
	    	n=m.size();
	    	int[] ths=new int[n]; for(int i=0;i<n;i++) ths[i]=ls.get(i);
	    	long res=0;
	    	Arrays.sort(ths);
	    	List<List<Integer>> edges = new ArrayList<List<Integer>>(n);
	    	for(int i=0;i<n;i++) edges.add(new ArrayList<Integer>());
	    	PriorityQueue<int[]> pq=new PriorityQueue<int[]>(Comparator.comparingInt(a -> 
	    	(Math.max(ths[a[0]],ths[a[1]])%Math.min(ths[a[0]],ths[a[1]] ) ) ) );
	    	int max=ths[n-1];
	    	for(int i=0;i<n;i++) {
	    		x=ths[i];
	    		for(int j=0;j<=max/x;j++) {
	    			int ind=Arrays.binarySearch(ths, j*x);
	    			if(ind<0) ind=-ind-1;
	    			//System.out.println(ind+"    "+ths[i]+"    "+ths[ind]+"     jx"+j*x);
	    			if(ind==i) ind++;
	    			if(ind<n && ths[ind]-j*x<x) {
	    				if(i==0) { 
	    					pq.add(new int[] {i, ind});
	    				}
	    				edges.get(i).add(ind); edges.get(ind).add(i);
	    			}
	    		}
	    	}
	    	boolean[] got=new boolean[n]; got[0]=true;
	    	int count=0;
	    	
	    	while(count<n-1) {
	    		int[] edg=pq.poll();
	    		//System.out.println(edg[0]+" "+edg[1]);
	    		if(!got[edg[0]] || !got[edg[1]]) {
	    			if (!got[edg[0]]) {
	    				got[edg[0]]=true; count++; 
	    				res+=Math.max(ths[edg[0]],ths[edg[1]])%Math.min(ths[edg[0]],ths[edg[1]]);
	    				for(int ed:edges.get(edg[0])) {
	    					pq.add(new int[] {ed, edg[0]});
	    				}
	    			}
	    			else {
	    				got[edg[1]]=true; count++; 
	    				res+=Math.max(ths[edg[0]],ths[edg[1]])%Math.min(ths[edg[0]],ths[edg[1]]);
	    				for(int ed:edges.get(edg[1])) {
	    					pq.add(new int[] {ed, edg[1]});
	    				}
	    			}
	    		}
	    	}
	    	System.out.println(res);
	    }
	}
}