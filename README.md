Solution to https://projecteuler.net/problem=490 on Project Euler, ranked the 45th hardest problem on the site. I derived a recursive equation for f(n) involving an auxillary function g(n). 
I of course derived a recusrive formula for g(n) as well. Then I searched for a simple pattern in the value of f(n) mod 2^9 and mod 5^9. NOTE: One could also use matrix exponentiation on the recursive equation 
for f, after you algebraically remove g(n) from the equation. 

Solution to https://projecteuler.net/problem=774 on Project Euler, ranked the 51st hardest problem on the site. The difficulty of the problem was efficiently querying my array to give the sum of all entries with an index k such that the bits of k are a subset of the bits of some fixed x. I needed to query this sum for every single value of x from 0 to (2^27)-1. I found an nlog(n) algortihm for this, where we iterate over each bit from 0 to 26 and create partial results for each query as we go along, using these partial results to aid our computation. It takes about 20 minutes to run. 
Solution to https://codeforces.com/contest/2122/problem/E, ranked as a 2600 difficulty problem. 

Solution to https://codeforces.com/contest/2128/problem/E2, ranked as a 2600 difficulty problem. 

Solution to https://codeforces.com/contest/2081/problem/D, ranked as a 2600 difficulty problem. 
