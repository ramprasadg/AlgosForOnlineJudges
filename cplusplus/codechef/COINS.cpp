/*
http://www.codechef.com/status/COINS/
*/

#include<stdio.h>
#define L 10000007

long long max[L] = {0,1,2,3,4,5,6,7,8,9,10,11};

long long cnt(long long a)
{
    if(a>=L)
        return (cnt(a/2)+cnt(a/3)+cnt(a/4));
    else if( max[a]==0 && 
            a!=0) // this was a nice catch. can cause stack-overflow
        max[a] = cnt(a/2)+cnt(a/3)+cnt(a/4);
    
    return max[a];
}
 
int main()
{
   long long n;
   while(scanf("%lld",&n)!=EOF){
       printf("%lld\n",cnt(n));
   }
   return 0;
}
