// http://www.codechef.com/problems/INTEST
/*
problem:
read input data is sufficiently fast to handle problems branded with the 
enormous Input/Output warning. 
You are expected to be able to process at least 2.5MB of input data per second

solution:
read in bigger chunks of 65536 intsead of usual delimiter '\n'.
*/

#include<stdio.h>
#define MAX 65536
char buf[MAX];
int main()
{
    unsigned n, k, t=0,count=0;
    int c,i;
    scanf("%u %u\n",&n,&k);
    while((c = fread(buf,1,MAX,stdin)) > 0)
    {
       for(i=0;i<c;i++)
       {
           if(buf[i] == '\n')
           {
               if(t%k==0)
                   count++;
               t = 0;
           }
           else
               t = t*10+buf[i]-'0';
       }
    }
    printf("%u",count);
    return 0;
}
