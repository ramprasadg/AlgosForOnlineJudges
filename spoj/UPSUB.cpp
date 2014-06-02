#include<iostream>
#include<algorithm>
#include<list>

using namespace std;

string x;

list<string>* ans;

class Result {
    int id;
    char ch;
    int maxLen;
    list<int> possibleParents;
    
    public:
    Result() {
        maxLen = 0;
    }
    
    void setIdCh(int id, char ch){
        this->id = id;
        this->ch = ch;
    }
    
    int getMaxLen() {
        return maxLen;
    }
    
    list<int> getParents() {
        return possibleParents;
    }
    
    bool updateParent(Result possibleParent);
};

Result* results;


bool Result::updateParent(Result possibleParent) {
    if(possibleParent.maxLen + 1 > maxLen) {
        maxLen = possibleParent.maxLen + 1;
        possibleParents.clear();
    }
    
    if (possibleParent.maxLen + 1 >= maxLen) {
        possibleParents.push_back(possibleParent.id);
        return true;
    }
    else {
        return false;
    }
}

void printAllRoutesTo(Result results[], string x, int index, string suffix) {
    list<int> parents = results[index].getParents();
    for(list<int>::reverse_iterator itr = parents.rbegin(); itr != parents.rend(); itr++) {
        printAllRoutesTo(results, x, *itr, x.at(index) + suffix);
    }
    
    if(parents.size() == 0) {
        ans->push_back(x.at(index) + suffix);
    }
}

void printMaxUpSubSequencesOf(string x) {
    Result possibleEndings;
    
    for(int i = 0; i < x.length(); i++) {
        //cout<<"i="<<i<<endl;
        results[i].setIdCh(i, x.at(i));
        for(int j = 0; j < i; j++) {
            if(x.at(j) <= x.at(i))
                results[i].updateParent(results[j]);
        }
        
        possibleEndings.updateParent(results[i]);
    }
    
    //cout<<possibleEndings.getMaxLen()<<endl;
    
    list<int> endings = possibleEndings.getParents();
    for(list<int>::reverse_iterator itr = endings.rbegin(); itr != endings.rend(); itr++) {
        printAllRoutesTo(results, x, *itr, "");
    }
}

int main() {
    int tests;
    cin>>tests;
    for(int test = 0; test < tests; test++) {
        cin>>x;
        results = new Result[x.length()];
        ans = new list<string>();
        printMaxUpSubSequencesOf(x);
        ans->sort();
        for(list<string>::iterator itr = ans->begin(); itr != ans->end(); itr++) {
            cout<<*itr<<endl;
        }
        cout<<endl;
        
        delete ans;
        delete[] results;
    }
    //printMaxUpSubSequencesOf("abcbcbcd");
}
