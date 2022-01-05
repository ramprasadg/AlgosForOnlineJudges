/*
http://www.codechef.com/problems/PERMUT2
*/

#include <iostream>
using namespace std;

int main(){
    int n, arr[100000];
    cin>>n;
    while(n!=0){
        for(int i=0;i<n;i++)
            cin>>arr[i];
        
        bool ambiguous = true;
        for(int i=0;i<n && ambiguous;i++){
            if(arr[arr[i-1]-1] != i)
                 ambiguous = false;
        }
        
        if(ambiguous)
            cout<<"ambiguous\n";
        else
            cout<<"not ambiguous\n";
            
        cin>>n;
    }
}
