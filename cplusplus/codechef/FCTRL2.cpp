//http://www.codechef.com/problems/FCTRL2
/*
*Problem
*handling bigIntegers and their multiplication
*/

#include<iostream>
#define DIGITS 200

using namespace std;


class BigInteger {
      private:
      int digits[DIGITS];
           
      public:
      BigInteger(){
          resetToOne();
      }
           
      void resetToOne(){
             digits[0]=1;
             for(int i=1;i<DIGITS;i++){
                   digits[i]=0;  
             }
      }
      
      void multiplyWith(int a){
             int product,carryForward,index;
             
             for (index = 0,carryForward = 0; index < DIGITS; index++){
                 product = digits[index]*a + carryForward;
                 carryForward = product/10;
                 digits[index] = product%10;
             }
      }
      
      void print(){
           bool allZerosFlag = true;
           for(int i=DIGITS-1; i>=1; i--){
               if(digits[i] != 0)
                        allZerosFlag = false;
               if(! allZerosFlag){
                   cout<<digits[i];
               } 
           }
           cout<<digits[0]<<endl;
      }
};

int main(){
    int tests, n;
   
    BigInteger factorials[101];
    
    for (int i=2;i<101;i++){
        factorials[i] = factorials[i-1];
        factorials[i].multiplyWith(i);
    }

    cin >> tests;
    while ( tests-- > 0 ) {
          cin>>n;
          factorials[n].print();
    }
}
