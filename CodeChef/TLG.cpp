/*
http://www.codechef.com/problems/TLG
*/

#include <iostream>
using namespace std;

int main(){
    int n, s, t, s_score=0, s_max = 0, t_score=0, t_max = 0;
    cin>>n;
    
    for (int i=0; i<n; i++) {
        cin>>s>>t;
        s_score += s;
        t_score += t;
        
        if(s_max < s_score - t_score)
            s_max = s_score - t_score;
        else if(t_max < t_score - s_score)
            t_max = t_score - s_score;
        
        //to stop overflow    
        if(s_score > 10000 && t_score > 10000) {
            s_score -= 10000;
            t_score -= 10000;
        }
    }
    
    if(s_max > t_max) {
        cout<<"1 "<<s_max<<endl;
    } else {
        cout<<"2 "<<t_max<<endl;
    }
}
