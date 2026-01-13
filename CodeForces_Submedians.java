import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
 
public class median2 {
	static int BOTTOM;
	static int TOPP;
	
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int xx=1;
		
		
		for(int i=0;i<10000;i++) {
			//System.out.println(4+" "+1);
			//System.out.println((int)(Math.random()*100000)+" "+(int)(Math.random()*100000)+" "
			//+(int)(Math.random()*100000)+" "+(int)(Math.random()*100000));
		}
		 	Scanner inp=new Scanner(System.in); 
	        int numm=inp.nextInt();
	        for(int ko=0;ko<numm;ko++){
	        	  int n=inp.nextInt(); 
	        	  int k=inp.nextInt(); 
	              int[] arr=new int[n];
	              int lb=Integer.MAX_VALUE;
	              int ub=0;
	              int top;
	              int bot;
	              int minL=0;
	              int minR=0;
	              int maxL=0;
	              int maxR=0;
	              for(int i=0;i<n;i++) {
	              	arr[i]=inp.nextInt();
	              	lb=Math.min(lb,  arr[i]);
	              	ub=Math.max(ub,  arr[i]);
	              }
	              top=ub; bot=lb;
	              while(lb<ub) {
	            	  int x=(lb+ub+1)/2;
	            	  int[] rs=func1(arr, x, k);
	            	  if(rs[0]==1) {
	            		  lb=x;
	            	  }
	            	  else ub=x-1;
	            	  if(ub==lb) {
	            		  maxL=rs[1]; maxR=rs[2];
	            	  }
	              }
	              int t=ub; ub=top; lb=bot; top=t;
	              while(lb<ub) {
	            	  int x=(lb+ub-1)/2;
	            	  int[] rs=func2(arr, x, k);
	            	  if(rs[0]==1) {
	            		  ub=x;
	            	  }
	            	  else lb=x+1;
	            	  if(ub==lb) {
	            		  minL=rs[1]; minR=rs[2];
	            	  }
	              }
	              bot=lb;
	              PriorityQueue<Integer> both = new PriorityQueue<>(Collections.reverseOrder());
	              PriorityQueue<Integer> toph = new PriorityQueue<>();
	              int[][] res=new int[top-bot+1][2];
	              int[] r1=func2(arr, bot, k);
	              minL=r1[1]; minR=r1[2];  
	              r1=func1(arr, top, k);
	              maxL=r1[1]; maxR=r1[2];
	              boolean lL=(minL<maxL);
	              //System.out.println(minL+" "+minR+" "+maxL+"  "+maxR+"  "+bot+"  "+top);
	              boolean rR=(minR<maxR);
	              boolean rL=(minR<maxL);
	              BOTTOM=bot; TOPP=top;
	              int[][] res2=new int[top-bot+1][2];
	            
	              if(lL) {
	            	  if(rR) {
	            		  slide1(arr, res, minL, minR, maxR, both, toph);
	            		  both = new PriorityQueue<>(Collections.reverseOrder()); toph = new PriorityQueue<>();
	            		  slide2(arr, res2, maxL, maxR, minL, both, toph);
	            	  }
	            	  else {
	            		  slide1(arr, res2, maxL, maxR, minR, both, toph);
	            		  both = new PriorityQueue<>(Collections.reverseOrder()); toph = new PriorityQueue<>();
	            		  slide2(arr, res, maxL, minR, minL, both, toph);
	            	  }
	              }
	              else {
	            	  if(rR) {
	            		  slide1(arr, res, minL, minR, maxR, both, toph);
	            		  both = new PriorityQueue<>(Collections.reverseOrder()); toph = new PriorityQueue<>();
	            		  slide2(arr, res2, minL, maxR, maxL, both, toph);
	            	  }
	            	  else {
	            		  slide1(arr, res2, maxL, maxR, minR, both, toph);
	            		  both = new PriorityQueue<>(Collections.reverseOrder()); toph = new PriorityQueue<>();
	            		  slide2(arr, res, minL, minR, maxL, both, toph);
	            	  }
	              }
	              
	              System.out.println(top-bot+1);
	              res[0][0]=minL+1; res[0][1]=minR+1;
		          res2[res.length-1][0]=maxL+1;   res2[res.length-1][1]=maxR+1;
	              for(int i=0;i<res.length;i++ ) {
	            	  if(res[i][0]!=0) {System.out.println((i+lb)+" "+res[i][0]+" "+res[i][1]);
	            	  }
	            	  else 			   {
	            	      if(res2[i][0]!=0)
	            		  	System.out.println((i+lb)+" "+res2[i][0]+" "+res2[i][1]);
	            	  else {
	            		 for(int x:arr) {
	            			 System.out.print(x+"|");
	            		 }
	            	  }
	            	  
	            	  }
	            	  
	              }
	              
	            //  System.out.println(xx);
	        }
	       // System.out.println(xx);
		}
	public static boolean check(int[] arr, int l, int r, int med) {
		l--; r--;
		int c1=0;
		int c2=0;
		for(int i=l;i<=r;i++) {
			if(arr[i]<=med) c1++;
			if(arr[i]>=med) c2++;
		}
		return (((r-l+2))/2<=c1 && ((r-l+2))/2<=c2);
	}
 
	public static int[] func1(int[] ls, int bound, int k) {
		int[] pre = new int[ls.length];
		if (ls[0] < bound)
			pre[0]--;
		else
			pre[0]++;
		for (int i = 1; i < ls.length; i++) {
			pre[i] = pre[i - 1];
			if (ls[i] < bound)
				pre[i]--;
			else
				pre[i]++;
		}
		int[] mxs = new int[ls.length];
		mxs[ls.length - 1] = ls.length - 1;
		for (int i = ls.length - 2; i >= 0; i--) {
			if (pre[mxs[i + 1]] < pre[i])
				mxs[i] = i;
			else
				mxs[i] = mxs[i + 1];
		}
 
		if (pre[mxs[k - 1]] >= 0)
			return new int[] { 1, 0, mxs[k - 1] };
		for (int i = 1; i < ls.length - k + 1; i++) {
			if (pre[mxs[i + k - 1]] - pre[i - 1] >= 0)
				return new int[] { 1, i, mxs[i + k - 1] };
		}
		return new int[] { 0, 0, 0 }; // false, emp emp
	}
 
	public static int[] func2(int[] ls, int bound, int k) {
		int[] pre = new int[ls.length];
		if (ls[0] > bound)
			pre[0]--;
		else
			pre[0]++;
		for (int i = 1; i < ls.length; i++) {
			pre[i] = pre[i - 1];
			if (ls[i] > bound)
				pre[i]--;
			else
				pre[i]++;
		}
		int[] mxs = new int[ls.length];
		mxs[ls.length - 1] = ls.length - 1;
		for (int i = ls.length - 2; i >= 0; i--) {
			if (pre[mxs[i + 1]] < pre[i])
				mxs[i] = i;
			else
				mxs[i] = mxs[i + 1];
		}
 
		if (pre[mxs[k - 1]] >= 0)
			return new int[] { 1, 0, mxs[k - 1] };
		for (int i = 1; i < ls.length - k + 1; i++) {
			if (pre[mxs[i + k - 1]] - pre[i - 1] >= 0)
				return new int[] { 1, i, mxs[i + k - 1] };
		}
		return new int[] { 0, 0, 0 }; // false, emp emp
	}
 
	public static void ADD(PriorityQueue<Integer> b, PriorityQueue<Integer> t, int a, int[][] res, int L, int R) {
		//System.out.println((t.size()+b.size())+"  bab     "+(R-L+1)+"    "+a);
		if (a >= t.peek()) {
			t.add(a);
		} 
		else if (b.size()==0 || a <= b.peek()) {
			b.add(a);
		} 
		else {
			t.add(a);
		}
		if (b.size() > t.size()) {
			t.add(b.poll());
		} else while (t.size() - 1 > b.size()) {
			b.add(t.poll());
		}
		//System.out.println(BOTTOM+" bottom "+b.peek()+"  "+L+"   "+R+"   "+t.peek()+"  "+b.size()+" "+t.size());
		if(b.size()==t.size()) {
			for(int i= b.peek();i<=t.peek();i++) {
				if(res[i-BOTTOM][0]!=0) { //System.out.println("BREAKING "+(i-BOTTOM)+" "  );  
					{break;}
				}
				res[i-BOTTOM][0]=L+1; res[i-BOTTOM][1]=R+1;
			}
			for(int i= t.peek();i>=b.peek();i--) {
				if(res[i-BOTTOM][0]!=0) { //System.out.println("BREAKING "+(i-BOTTOM)+" " +res[2][0] ); 
					{ break;}
				}
				res[i-BOTTOM][0]=L+1; res[i-BOTTOM][1]=R+1;
			}
			//System.out.println(b.peek()+" "+t.peek()+"     "+L+" "+R);
		}
		else {
			res[t.peek()-BOTTOM][0]=L+1; res[t.peek()-BOTTOM][1]=R+1;
			//System.out.println(t.peek()+"        "+L+" "+R);
		}
		
		
	}
 
	public static int selectKth(int[] a, int k) {
		k++;
        if (a == null || a.length == 0) throw new IllegalArgumentException("Array empty");
        if (k < 1 || k > a.length) throw new IllegalArgumentException("k out of range");
        return deterministicSelect(a, 0, a.length - 1, k - 1);
    }
 
    private static int deterministicSelect(int[] a, int left, int right, int kIndex) {
        if (left == right) return a[left];
        int len = right - left + 1;
        if (len <= 5) {
            Arrays.sort(a, left, right + 1);
            return a[left + kIndex];
        }
 
        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(a, i, subRight + 1);
            int medianIndex = i + (subRight - i) / 2;
            swap(a, left + numMedians, medianIndex);
            numMedians++;
        }
 
        int pivot = deterministicSelect(a, left, left + numMedians - 1, numMedians / 2);
 
        int lt = left, i = left, gt = right;
        while (i <= gt) {
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                swap(a, i, gt--);
            } else {
                i++;
            }
        }
 
        int numLess = lt - left;
        int numEqual = gt - lt + 1;
 
        if (kIndex < numLess) {
            return deterministicSelect(a, left, lt - 1, kIndex);
        } else if (kIndex < numLess + numEqual) {
            return pivot;
        } else {
            return deterministicSelect(a, gt + 1, right, kIndex - numLess - numEqual);
        }
    }
 
    private static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
 
    public static int median(int[] a) {
        int k = (a.length) / 2;
        return selectKth(a, k);
    }
	public static void slide1(int[] array, int[][] res, int L, int R, int RTARG, PriorityQueue<Integer> both,
			PriorityQueue<Integer> toph) {
		
		int[] mmm = new int[R - L + 1];
		for (int i = 0; i < R - L + 1; i++) {
			mmm[i] = array[i + L];
		}
		
		if ((R - L) % 2 == 0) {
			int med = median(mmm);
			
			for (int i = 0; i < mmm.length; i++) {
				if (mmm[i] < med)
					both.add(mmm[i]);
				else if (mmm[i] > med)
					toph.add(mmm[i]);
			}
			while(mmm.length-toph.size()-both.size()>0){
				if (toph.size()<=mmm.length/2)
					toph.add(med);
				else
					both.add(med);
			}
		} else {
			int med1 = selectKth(mmm, mmm.length / 2 - 1);
			int med2 = selectKth(mmm, mmm.length / 2);
			
			if (med1 != med2) {
				for (int i = 0; i < mmm.length; i++) {
					if (mmm[i] <= med1)
						both.add(mmm[i]);
					else if (mmm[i] >= med2)
						toph.add(mmm[i]);
				}
			} else {
				for (int i = 0; i < mmm.length; i++) {
					if (mmm[i] < med1)
						both.add(mmm[i]);
					else if (mmm[i] > med2)
						toph.add(mmm[i]);
				}
				int i=0;
				while(mmm.length-toph.size()-both.size()>0){{
					if (i % 2 == 0)
						toph.add(med2);
					else
						both.add(med1);
					i++; i%=2;
				}
			}
		}
		}
		//System.out.println(BOTTOM+"  "+both.peek()+"  "+L+"   "+R+"   "+toph.peek()+" 66 ");
		if(both.size()>0 && both.size()==toph.size()) {
		for(int i= both.peek();i<=toph.peek();i++) {
			
			res[i-BOTTOM][0]=L+1; res[i-BOTTOM][1]=R+1;
		}
		}
		else if(both.size()>0) {
			res[toph.peek()-BOTTOM][0]=L+1; res[toph.peek()-BOTTOM][1]=R+1;
		}
		while (R < RTARG) {
			R++;
			ADD(both, toph, array[R], res,L,R);
		}
		
 
	}
 
	public static void slide2(int[] array, int[][] res, int L, int R, int LTARG, PriorityQueue<Integer> both,
			PriorityQueue<Integer> toph) {
		//System.out.println("SLD2");
		int[] mmm = new int[R - L + 1];
		for (int i = 0; i < R - L + 1; i++) {
			mmm[i] = array[i + L];
		}
		if ((R - L) % 2 == 0) {
			int med = median(mmm);
			//System.out.println("SLD21");
			for (int i = 0; i < mmm.length; i++) {
				if (mmm[i] < med)
					both.add(mmm[i]);
				else if (mmm[i] > med)
					toph.add(mmm[i]);
			}
			while(mmm.length-toph.size()-both.size()>0) {
				if (toph.size()<=mmm.length/2)
					toph.add(med);
				else
					both.add(med);
			}
		} else {
			int med1 = selectKth(mmm, mmm.length / 2 - 1);
			int med2 = selectKth(mmm, mmm.length / 2);
			//System.out.println("SLD21");
			if (med1 != med2) {
				for (int i = 0; i < mmm.length; i++) {
					if (mmm[i] <= med1)
						both.add(mmm[i]);
					else if (mmm[i] >= med2)
						toph.add(mmm[i]);
				}
			} else {
				for (int i = 0; i < mmm.length; i++) {
					if (mmm[i] < med1)
						both.add(mmm[i]);
					else if (mmm[i] > med2)
						toph.add(mmm[i]);
				}
				int i=0;
				while(mmm.length-toph.size()-both.size()>0){{
					if (i % 2 == 0)
						toph.add(med2);
					else
						both.add(med1);
					i++; i%=2;
				}
			}
		}
		}
		if(both.size()>0 && both.size()==toph.size()) {
			for(int i= both.peek();i<=toph.peek();i++) {
				
				res[i-BOTTOM][0]=L+1; res[i-BOTTOM][1]=R+1;
			}
			}
			else if(both.size()>0) {
				res[toph.peek()-BOTTOM][0]=L+1; res[toph.peek()-BOTTOM][1]=R+1;
			}
		while (L > LTARG) {
			L--;
			ADD(both, toph, array[L], res, L,R);
		}
	}
	
 
}