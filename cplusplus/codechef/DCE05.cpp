/********************************************************
*http://www.codechef.com/problems/DCE05
*Problem:
*this is Josephus_problem with k=2.
*Wiki Link:
*http://en.wikipedia.org/wiki/Josephus_problem#k.3D2
*Solution:
*the answer is the maximum power of 2 that is less than the given number
*NOTE: the input using cin times out. 
*so we will have to use fast input like the http://www.codechef.com/problems/INTEST
*/

#include <cstdio>
#include <cstdlib>
#include <iostream>
using namespace std;

#define MAX 65536
char buf[MAX];

long int maxPowOf2LessThanNumber( long int number ){
    long int powOf2 = 1;
    while(number >= powOf2) {
        powOf2 *= 2;
    }
    powOf2 /= 2;
    
    return powOf2;
}

int main(){
    int tests, c, i;
    long int n = 0;
    
    /*
    slow input output for manual testing
    cin >> tests;
    while(tests -- ){
        cin>>n;
        printf("%ld\n",maxPowOf2LessThanNumber(n));
    }
    */
    fgets(buf,MAX,stdin);
    sscanf(buf,"%d",&tests);
    while(tests > 0){
       c = fread(buf,1,MAX,stdin);
       for(i=0; i<c; i++)
       {
           if(buf[i] == '\n')
           {
               tests--;
               printf("%ld\n",maxPowOf2LessThanNumber(n));
               n = 0;
           }
           else if(buf[i]<='9' && buf[i]>='0'){
               n = n*10+buf[i]-'0';
           }
       }
    }
}
