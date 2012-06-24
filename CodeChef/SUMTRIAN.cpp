/*
http://www.codechef.com/problems/SUMTRIAN
*/

#include <iostream>
using namespace std;

int input[101];
int arr[101];

int main(){
    int tests,n;
    cin>>tests;
    for(int test=0; test < tests; test++){
        cin>>n;
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                cin>>input[j];
            }
            
            arr[i] = input[i];
            if(i!=0)
                arr[i] += arr[i-1];
            
            for(int j=i-1; j>=0; j--) {
                if(arr[j-1]>arr[j])
                    arr[j] = arr[j-1];
                arr[j] += input[j];
            }
        }
        int maxOfArr = arr[0];
        for(int i=1; i<n; i++)
            if(maxOfArr < arr[i])
                maxOfArr = arr[i];
        cout<<maxOfArr<<endl;
    }
}
