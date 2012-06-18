//http://www.codechef.com/problems/HS08TEST

#include<iostream>
using namespace std;

int main(){
    float fWithdrawal, fAccountBalance;
    int iWithdrawal, iAccountBalance;
    
    cin>>fWithdrawal >> fAccountBalance;
    //comparision of float values is a problem in C++. 
    //So i am converting this to integer before comparing.
    iWithdrawal = int (100*fWithdrawal);
    iAccountBalance = int(100*fAccountBalance);
    
    if(iWithdrawal % 500 == 0){
        if(iWithdrawal + 50 <= iAccountBalance)
            iAccountBalance -= (iWithdrawal + 50);
    }
    
    fAccountBalance = float(iAccountBalance)/100;
    cout.precision(2);
    cout << fixed << showpoint <<fAccountBalance;
}
