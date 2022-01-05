/*
http://www.codechef.com/problems/RESN04
Solution:
The players cannot change the outcome of the game.
    the number of steps using each pile is floor(stonesInPile / PileNo)
    overall winner is simply the %2 of the sum of the number of steps 
*/

#include <iostream>
using namespace std;

int main(){
    int test, tests, n, pile, noOfSteps;
     
     cin>>tests;
     for(test = 0; test < tests; test++){
         cin>>n;
         noOfSteps = 0;
         for(int i=1; i<=n; i++) {
             cin>>stonesInPile;
             noOfSteps += pile/i;
         }
         
         if (noOfSteps % 2 == 1)
             cout<<"ALICE"<<endl;
         else 
             cout<<"BOB"<<endl;
     }
}
