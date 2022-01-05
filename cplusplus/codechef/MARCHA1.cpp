/*
http://www.codechef.com/problems/MARCHA1
*/

#include<iostream>
using namespace std;

int main()
{
    int test, tests, numberOfNotes, printedValueOfNote, amountForGangster, maxPossibleAmountTillNow;
    bool possibleAmounts[21001];
    cin>>tests;    
    
    for(test=1; test<=tests; test++)
    {
        maxPossibleAmountTillNow = 0;
        possibleAmounts[0] = true;
        for(int i=1; i<=20000; i++)
            possibleAmounts[i] = false;
        
        
        cin>>numberOfNotes>>amountForGangster;
        for(int i=0; i<numberOfNotes; i++){
            cin>>printedValueOfNote;
            maxPossibleAmountTillNow += printedValueOfNote;
             
            for(int j=maxPossibleAmountTillNow; j>=0 ; j--)
                if (possibleAmounts[j] == true) {
                    possibleAmounts[j+printedValueOfNote] = true;
                }
        }
        
        if(possibleAmounts[amountForGangster]) {
            cout<<"Yes\n";
        }
        else {
            cout<<"No\n";
        }
    }
}

