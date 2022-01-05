/*
http://www.codechef.com/problems/HOLES
*/

#include<iostream>
using namespace std;

int main()
{
    int tests,total,test;
    char a[100];
    cin>>tests;    
    
    for(test=1; test<=tests; test++)
    {
    
    total = 0;
    cin>>a;
    
    int i=0;
    while(a[i]!='\0')
    {
                     if(a[i]=='A' || a[i]=='D' || a[i]=='O' ||
                      a[i]=='P' || a[i]=='Q' || a[i]=='R')
                         total++;
                     else if(a[i]=='B')
                         total+=2;
                     
                     i++;
    }
    
    cout<<total<<endl;
    }
}
