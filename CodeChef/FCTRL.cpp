/*************************************************************
*link:http://www.codechef.com/problems/FCTRL
*problem:
*find the number of zeros in a factorial
*solution:
*the number of 0s in any factorial is the highest power of 5 in that factorial.
***********************************************************/

#include<iostream>
using namespace std;

int main(){
    int noOfTests, n, zeros, quotient;
    cin>>noOfTests;
    
    while(noOfTests-- > 0){
        cin>>n;
        zeros = 0;
        while(n>0){
            quotient = n / 5;
            n = quotient;
            zeros += quotient;    
        }
        cout<<zeros<<endl;
    }
}
