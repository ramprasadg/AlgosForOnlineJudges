/********************************************************
*http://www.codechef.com/problems/TSORT
*Problem:
*sort the given integers
*Solution:
*count the frequency and then use the frequency array to print the answer
*NOTE: the input using cin times out. 
*so we will have to use fast input like the http://www.codechef.com/problems/INTEST
*/

#include<stdio.h>
#include<malloc.h>
int frequency[1000001];
int main()
{
    int count,i=0,j=0,n=0;
    char buffer[5000];
    fscanf(stdin,"%d\n",&count);
    while(count)
    {
         i=fread(buffer,sizeof(char),5000,stdin);
        for(j=0;j<i;j++)
        {
            if(buffer[j]=='\n')
            {
                count--;
                frequency[n]++;
                n=0;
            }
            else
            {
                n*=10;
                n+=buffer[j]-'0';
            }
        }
    }
    for(i=0;i<=1000000;i++)
    {
        while(frequency[i]>0){
            printf("%d\n",i);
            frequency[i]--;
        }
    }
    
    return 0;    
}
